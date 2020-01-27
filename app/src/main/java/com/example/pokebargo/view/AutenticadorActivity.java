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

    /**
     * Autenticador de numero de telefone
     * <p>
     * O autenticador faz que o numero informado seje autenticado, ao ser autenticado ele cria um UID
     * do usuário, podemos utilizar esse UID para realizar CRUD aonde serão passados informações diretas
     * para o usuário especificado, são verificado todas as etapas que deverão ser concluídas pelo usuário.
     *
     * @since 1.0
     * @author Sandro Diego Adão
     */


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

    /**
     * Método de verificação do numero informado.
     * <p>
     * Neste método é passado como parametro a credencial do numero do autenticador aonde é possível
     * estar veriricando o retorno do resultado da operação pelo método onComplete, caso a task seja
     * efetuada com sucesso ele avança para a próxima etapa, caso contrário retorna um erro, onde pode
     * ser tratado com um try/catch.
     *
     * @param credential
     * @author Sandro Diego Adão
     * @since 1.0
     */

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

    /**
     * Parametro de inicialização da tela
     *<p>
     * Método utilizado no onCreate da view aonde serão carregada as informações ao criar a view, foi
     * separado para poder deixar o onCreate mais clean.
     *
     * @since 1.0
     * @author Sandro Diego Adão
     */

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

    /**
     * Método de enviar código via SMS
     * <p>
     * Utiliza-se esse método para enviar código de SMS contendo o número que será usado para autenticar
     * o telefone do usuário essa é a segunda etapa da criação do usuário.
     *
     * @param view
     * @since 1.0
     * @author Sandro Diego Adão
     */
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

    /**
     * Método para autenticar o código enviado por SMS
     * <p>
     * Neste método é verificado o número enviado via SMS, é realizado uma verificação de caracteres
     * se o campo está vazio, caso o código informado esteja correto ele finaliza a task caso contrário
     * retorna um erro para o usuário.
     *
     * @param view
     * @since 1.0
     * @author Sandro Diego Adão
     */

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

    /**
     * Etapa de criação do perfil do usuário
     * <p>
     * Esta etapa consiste em criar um perfil para o usuário credenciado, aonde ele poderá alterar suas informações
     * após concluído ele será redirecionado para a tela principal, podendo alterar essas informações a qualquer hora.
     * O método onVerificationComplete tem como objeto verificado o estado do usuário, caso ele complete todas as etapas
     * anteriores ele entra no nesse metodo, o método onCodeSend tem como objeto verificar o estado do envio da mensagem
     * tendo co verificationID como atributo, verifica o usuário caso ele já seja autenticado.
     *
     * @param view
     * @since 1.0
     * @author Sandro Diego Adão
     */
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

    /**
     * Método de retorno para a tela de login
     * <p>
     * Retorna o usuário para tela de login
     *
     * @param view
     * @since 1.0
     * @author Sandro Diego Adão
     */

    public void voltarClick(View view) {
        startActivity(new Intent(AutenticadorActivity.this, LoginActivity.class));
        finish();
    }
}
