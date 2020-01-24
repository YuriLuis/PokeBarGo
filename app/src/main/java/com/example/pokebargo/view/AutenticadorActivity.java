package com.example.pokebargo.view;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.chaos.view.PinView;
import com.example.pokebargo.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.shuhart.stepview.StepView;

import java.util.concurrent.TimeUnit;


public class AutenticadorActivity extends AppCompatActivity {
    private static final String TAG = "FirebasePhoneNumAuth";
    LinearLayout layout1, layout2, layout3;
    StepView stepView;
    AlertDialog dialogo_verificador, dialogo_perfil;
    private int etapaAtual = 0;
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks;
    private FirebaseAuth firebaseAuth;
    private PhoneAuthProvider.ForceResendingToken mResendToken;
    private String mVerificationId;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_autenticador);
        carregarView();
        layout1.setVisibility(View.VISIBLE);
    }

    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "Sucesso ao logar com suas credenciais!");
                            dialogo_verificador.dismiss();
                            if (etapaAtual < stepView.getStepCount() - 1) {
                                etapaAtual++;
                                stepView.go(etapaAtual, true);
                            } else {
                                stepView.done(true);
                            }
                            layout1.setVisibility(View.GONE);
                            layout2.setVisibility(View.GONE);
                            layout3.setVisibility(View.VISIBLE);
                        } else {
                            dialogo_verificador.dismiss();
                            String msgErro = "Falha ao tentar logar com suas credenciais!";
                            Toast.makeText(AutenticadorActivity.this, msgErro, Toast.LENGTH_SHORT).show();
                            Log.w(TAG, msgErro, task.getException());
                            if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                            }
                        }
                    }
                });
    }

    public void carregarView() {
        mAuth = FirebaseAuth.getInstance();
        firebaseAuth = FirebaseAuth.getInstance();
        layout1 = findViewById(R.id.ll_enviarCodigoSMS);
        layout2 = findViewById(R.id.ll_autenticarCodigoSMS);
        layout3 = findViewById(R.id.ll_criarPerfilUsuario);
        stepView = findViewById(R.id.step_view);
        stepView.setStepsNumber(3);
        stepView.go(0, true);
    }

    public void enviarCodigoSMS(View view) {
        TextView tv_numCelular;
        EditText et_numCelular;
        String numCelular;

        et_numCelular = findViewById(R.id.et_numCelular);

        numCelular = et_numCelular.getText().toString();
        Toast.makeText(this, "Código enviado para o número" + numCelular, Toast.LENGTH_LONG).show();

        if (TextUtils.isEmpty(numCelular) || (numCelular.length() < 10)) {
            et_numCelular.setError("Número de telefone inválido!");
            et_numCelular.requestFocus();
        } else {

            if (etapaAtual < stepView.getStepCount() - 1) {
                etapaAtual++;
                stepView.go(etapaAtual, true);
            } else {
                stepView.done(true);
            }
            layout1.setVisibility(View.GONE);
            layout2.setVisibility(View.VISIBLE);
            PhoneAuthProvider.getInstance().verifyPhoneNumber(
                    numCelular,        // Phone number to verify
                    60,                 // Timeout duration
                    TimeUnit.SECONDS,   // Unit of timeout
                    AutenticadorActivity.this,               // Activity (for callback binding)
                    mCallBacks());        // OnVerificationStateChangedCallbacks
        }
    }

    public void autenticarCodigoSMS(View view) {
        PinView verifyCodeET;

        verifyCodeET = findViewById(R.id.pinView);

        String verificationCode = verifyCodeET.getText().toString();
        if (verificationCode.isEmpty()) {
            Toast.makeText(AutenticadorActivity.this, "Entre com o código de autenticação!", Toast.LENGTH_SHORT).show();
        } else {

            LayoutInflater inflater = getLayoutInflater();
            View alertLayout = inflater.inflate(R.layout.dialogo_processamento, null);
            AlertDialog.Builder show = new AlertDialog.Builder(AutenticadorActivity.this);

            show.setView(alertLayout);
            show.setCancelable(false);
            dialogo_verificador = show.create();
            dialogo_verificador.show();

            PhoneAuthCredential credential = PhoneAuthProvider.getCredential(mVerificationId, verificationCode);
            signInWithPhoneAuthCredential(credential);
        }
    }

    public void criarPerfilUsuario(View view) {
        if (etapaAtual < stepView.getStepCount() - 1) {
            etapaAtual++;
            stepView.go(etapaAtual, true);
        } else {
            stepView.done(true);
        }
        LayoutInflater inflater = getLayoutInflater();
        View alertLayout = inflater.inflate(R.layout.dialogo_criar_perfil, null);
        AlertDialog.Builder show = new AlertDialog.Builder(AutenticadorActivity.this);
        show.setView(alertLayout);
        show.setCancelable(false);
        dialogo_perfil = show.create();
        dialogo_perfil.show();
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                dialogo_perfil.dismiss();
                startActivity(new Intent(AutenticadorActivity.this, MainActivity.class));
                finish();
            }
        }, 3000);
    }

    public PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallBacks() {
        mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            @Override
            public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {
                LayoutInflater inflater = getLayoutInflater();
                View alertLayout = inflater.inflate(R.layout.dialogo_processamento, null);
                AlertDialog.Builder show = new AlertDialog.Builder(AutenticadorActivity.this);

                show.setView(alertLayout);
                show.setCancelable(false);
                dialogo_verificador = show.create();
                dialogo_verificador.show();
                signInWithPhoneAuthCredential(phoneAuthCredential);
            }

            @Override
            public void onVerificationFailed(FirebaseException e) {
            }

            @Override
            public void onCodeSent(String verificationId, PhoneAuthProvider.ForceResendingToken token) {
                // Salve o ID de verificação e reenvie o token para que possamos usá-los mais tarde
                mVerificationId = verificationId;
                mResendToken = token;
            }
        };
        return mCallbacks;
    }

    public void voltarClick(View view) {
        startActivity(new Intent(AutenticadorActivity.this, LoginActivity.class));
        finish();
    }
}
