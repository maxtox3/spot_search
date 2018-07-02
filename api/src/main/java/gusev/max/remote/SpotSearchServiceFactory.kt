package gusev.max.remote

import gusev.max.data.source.auth.AuthCache
import gusev.max.remote.api.*
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Created by v on 11/06/2018.
 */
object SpotSearchServiceFactory {

    private const val AUTH_HEADER_KEY = "Authorization"

    fun makeAuthService(isDebug: Boolean, baseUrl: String, cache: AuthCache): AuthApi {
        return makeDefaultRetrofit(isDebug, baseUrl, cache).create(AuthApi::class.java)
    }

    fun makeUserService(isDebug: Boolean, baseUrl: String, cache: AuthCache): UserApi {
        return makeDefaultRetrofit(isDebug, baseUrl, cache).create(UserApi::class.java)
    }

    fun makeActionService(isDebug: Boolean, baseUrl: String, cache: AuthCache): ActionApi {
        return makeDefaultRetrofit(isDebug, baseUrl, cache).create(ActionApi::class.java)
    }

    fun makeEventService(isDebug: Boolean, baseUrl: String, cache: AuthCache): EventApi {
        return makeDefaultRetrofit(isDebug, baseUrl, cache).create(EventApi::class.java)
    }

    fun makeCommentService(isDebug: Boolean, baseUrl: String, cache: AuthCache): CommentApi {
        return makeDefaultRetrofit(isDebug, baseUrl, cache).create(CommentApi::class.java)
    }

    private fun makeDefaultRetrofit(
        isDebug: Boolean,
        baseUrl: String,
        cache: AuthCache
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(makeHttpClient(makeLoggingInterceptor(isDebug), cache))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private fun makeHttpClient(
        loggingInterceptor: HttpLoggingInterceptor,
        cache: AuthCache
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .addInterceptor(getAuthInterceptor(cache))
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .build()
    }

    private fun makeLoggingInterceptor(isDebug: Boolean): HttpLoggingInterceptor {
        val logging = HttpLoggingInterceptor()
        logging.level = if (isDebug) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
        return logging
    }

    private fun getAuthInterceptor(cache: AuthCache): Interceptor {
        return Interceptor { chain ->
            var request = chain.request()
            val url = request.url().toString()
            if (!url.contains("auth")) {
                request = request
                    .newBuilder()
                    .header(AUTH_HEADER_KEY, "Bearer " + cache.getToken())
                    .build()
            }
            chain.proceed(request)
        }

    }

}