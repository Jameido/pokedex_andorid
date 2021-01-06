package dev.jameido.pokedex.framework.datasource.network

import android.content.Context
import android.content.res.Resources.NotFoundException
import android.util.Base64
import android.util.Log
import androidx.annotation.RawRes
import dev.jameido.pokedex.BuildConfig
import java.io.*
import java.security.MessageDigest
import java.security.cert.Certificate
import java.security.cert.CertificateException
import java.security.cert.CertificateFactory
import java.security.cert.X509Certificate

/**
 * Created by Jameido on 06/01/2021.
 */
class CertKeyReader {

    fun extractKeyFromRaw(context: Context, @RawRes rawCertificate: Int): String {
        try {
            return extractPublicKey(context.resources.openRawResource(rawCertificate))
        } catch (ex: NotFoundException) {
            if(BuildConfig.DEBUG) {
                Log.e("CertKeyReader", "extractKeyFromRaw: ", ex)
            }
        }
        return ""
    }

    private fun extractPublicKey(certificateStream: InputStream?): String {
        try {
            val x509Certificate = CertificateFactory.getInstance("X509")
                    .generateCertificate(certificateStream) as X509Certificate
            val publicKeyEncoded = x509Certificate.publicKey.encoded
            val messageDigest = MessageDigest.getInstance("SHA-256")
            val publicKeySha256 = messageDigest.digest(publicKeyEncoded)
            val publicKeyShaBase64 = Base64.encode(publicKeySha256, Base64.NO_WRAP)
            return "sha256/" + String(publicKeyShaBase64)
        } catch (ex: Exception) {
            if(BuildConfig.DEBUG) {
                Log.e("CertKeyReader", "extractPublicKey: ", ex)
            }
        } finally {
            try {
                certificateStream?.close()
            } catch (ignored: IOException) {
            }
        }
        return ""
    }
}