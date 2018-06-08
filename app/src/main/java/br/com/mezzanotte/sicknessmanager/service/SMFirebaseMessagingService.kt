package br.com.mezzanotte.sicknessmanager.service

import android.util.Log
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

/**
 * Serviço responsável por receber push notification do Firebase
 */
class SMFirebaseMessagingService: FirebaseMessagingService() {

    val TAG = "SM_FIREBASE"

    override fun onMessageReceived(remoteMessage: RemoteMessage?) {
        // O firebase já apresenta uma notificação no app com a possibilidade de abrir o app
        // ao tocar na notificação. Aqui é gerado um log somente para saber se recebemos
        // corretamente o que deveria ter sido recebido
        Log.d(TAG, "From: " + remoteMessage!!.from)
        Log.d(TAG, "Notification Message Body: " + remoteMessage.notification?.body!!)
    }

}