package dev.jameido.pokedex.di

import com.squareup.moshi.Moshi
import dev.jameido.pokedex.BuildConfig
import dev.jameido.pokedex.data.datasource.PkmnDataSource
import dev.jameido.pokedex.framework.RetrofitPkmnDataSource
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

/**
 * Created by Jameido on 18/12/2020.
 */
val retrofitModule = module {
    single { buildOkHttpClient() }
    factory { buildMoshi() }
    single<PkmnDataSource> { createApiImpl(RetrofitPkmnDataSource::class.java, get(), "https://pokeapi.co/api/v2/") }
}

private fun buildMoshi(): Moshi {
    return Moshi.Builder()
            .build()
}

private fun buildOkHttpClient(): OkHttpClient {
    return OkHttpClient.Builder().apply {
        if (BuildConfig.DEBUG) {
            val baseLogger = HttpLoggingInterceptor()
            baseLogger.level = HttpLoggingInterceptor.Level.BODY
            addInterceptor(baseLogger)
        }
    }.build()

    //TODO: Security - SLL Certificate pinning
}

private fun <S> createApiImpl(apiClass: Class<S>, client: OkHttpClient, baseUrl: String): S {
    val vRetrofit = Retrofit.Builder()
            .client(client)
            .baseUrl(baseUrl)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
    return vRetrofit.create(apiClass)
}