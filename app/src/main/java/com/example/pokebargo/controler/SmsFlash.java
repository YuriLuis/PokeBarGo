package com.example.pokebargo.controler;

import android.telephony.SmsManager;

public class SmsFlash {

    /**
     * Example:
     *
     * if (ContextCompat.checkSelfPermission(this,
     *                 Manifest.permission.SEND_SMS)
     *                 != PackageManager.PERMISSION_GRANTED) {
     *
     *             // Permission is not granted
     *             // Should we show an explanation?
     *             if (ActivityCompat.shouldShowRequestPermissionRationale(this,
     *                     Manifest.permission.SEND_SMS)) {
     *                 // Show an explanation to the user *asynchronously* -- don't block
     *                 // this thread waiting for the user's response! After the user
     *                 // sees the explanation, try again to request the permission.
     *             } else {
     *                 // No explanation needed; request the permission
     *                 ActivityCompat.requestPermissions(this,
     *                         new String[]{Manifest.permission.SEND_SMS},
     *                         6847);
     *
     *                 // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
     *                 // app-defined int constant. The callback method gets the
     *                 // result of the request.
     *             }
     *         } else {
     *             // Permission has already been granted
     *         }
     *
     *         SmsFlash smsFlash = new SmsFlash();
     *         smsFlash.mandarBesterinha("+5547996195338", "Eaí maninho");
     *
     * @param numero
     * @param mensagem
     */
    public void mandarBesterinha(String numero, String mensagem) {

        SmsManager smsManager = SmsManager.getDefault();

        smsManager.sendTextMessage(numero, null, mensagem, null, null);
    }



}
