package br.com.mezzanotte.sicknessmanager

import android.support.multidex.MultiDexApplication
import android.util.Log

class SicknessManagerApplication : MultiDexApplication() {
    private val TAG = "SMAPP"

    // Chamado quando o Android criar o processo da aplicação
    override fun onCreate() {
        super.onCreate()
        // Salva a instância para termos acesso como Singleton
        appInstance = this
    }

    companion object {
        // Singleton da classe Application
        private var appInstance: SicknessManagerApplication? = null

        fun getInstance(): SicknessManagerApplication {
            if (appInstance == null) {
                throw IllegalStateException("Configure a classe de Application no AndroidManifest.xml")
            }
            return appInstance!!
        }
    }

    // Chamado quando o Android finalizar o processo da aplicação
    override fun onTerminate() {
        super.onTerminate()
        Log.d(TAG, "CarrosApplication.onTerminate()")
    }
}