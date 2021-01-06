package dev.jameido.pokedex.di

import android.content.Context
import com.squareup.moshi.Moshi
import dev.jameido.pokedex.BuildConfig
import dev.jameido.pokedex.R
import dev.jameido.pokedex.framework.datasource.network.CertKeyReader
import dev.jameido.pokedex.framework.datasource.network.PkmnApi
import okhttp3.CertificatePinner
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

/**
 * Created by Jameido on 18/12/2020.
 */
val retrofitModule = module {
    single { CertKeyReader() }
    single { buildCertificatePinner(get(), get()) }
    single { buildOkHttpClient(get()) }
    factory { buildMoshi() }
    single { createApiImpl(PkmnApi::class.java, client = get(), "https://pokeapi.co/api/v2/") }
}

private fun buildMoshi(): Moshi {
    return Moshi.Builder()
            .build()
}

private fun buildOkHttpClient(pinner: CertificatePinner): OkHttpClient {
    return OkHttpClient.Builder()
            .certificatePinner(pinner)
            .apply {
                if (BuildConfig.DEBUG) {
                    val baseLogger = HttpLoggingInterceptor()
                    baseLogger.level = HttpLoggingInterceptor.Level.BODY
                    addInterceptor(baseLogger)
                }
            }.build()
}

private fun buildCertificatePinner(context: Context, certKeyReader: CertKeyReader): CertificatePinner {
    val pinnerBuilder = CertificatePinner.Builder()
    try {
        val publicKey = certKeyReader.extractKeyFromRaw(context, R.raw.sslcert)
        pinnerBuilder.add("https://pokeapi.co", publicKey)
    } catch (ignored: Exception) {
    }
    return pinnerBuilder.build()
}

private fun <S> createApiImpl(apiClass: Class<S>, client: OkHttpClient, baseUrl: String): S {
    val vRetrofit = Retrofit.Builder()
            .client(client)
            .baseUrl(baseUrl)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
    return vRetrofit.create(apiClass)
}