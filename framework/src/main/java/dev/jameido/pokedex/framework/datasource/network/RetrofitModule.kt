package dev.jameido.pokedex.framework.datasource.network

import android.content.Context
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.Reusable
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dev.jameido.pokedex.framework.BuildConfig
import dev.jameido.pokedex.framework.R
import okhttp3.CertificatePinner
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RetrofitModule {

    @Provides
    @Singleton
    fun provideApi(client: OkHttpClient) : PkmnApi {
        return createApiImpl(PkmnApi::class.java, client, "https://pokeapi.co/api/v2/")
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(pinner: CertificatePinner): OkHttpClient {
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

    @Provides
    @Reusable
    fun provideCertificatePinner(@ApplicationContext appContext: Context, certKeyReader: CertKeyReader): CertificatePinner {
        val pinnerBuilder = CertificatePinner.Builder()
        try {
            val publicKey = certKeyReader.extractKeyFromRaw(appContext, R.raw.sslcert)
            pinnerBuilder.add("https://pokeapi.co", publicKey)
        } catch (ignored: Exception) {
        }
        return pinnerBuilder.build()
    }

    @Provides
    @Reusable
    fun provideCertKeyReader(): CertKeyReader {
        return CertKeyReader()
    }

    @Provides
    @Reusable
    fun provideMoshi(): Moshi {
        return Moshi.Builder()
                .build()
    }

    private fun <S> createApiImpl(apiClass: Class<S>, client: OkHttpClient, baseUrl: String): S {
        val vRetrofit = Retrofit.Builder()
                .client(client)
                .baseUrl(baseUrl)
                .addConverterFactory(MoshiConverterFactory.create())
                .build()
        return vRetrofit.create(apiClass)
    }
}