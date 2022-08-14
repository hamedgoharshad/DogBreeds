package com.near.common.data.interceptor

import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AuthInterceptor @Inject constructor() : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val requestBuilder = chain.request().newBuilder().apply {
            addHeader("Accept", "application/json")
            //addHeader("Authorization", BuildConfig.API_KEY)
        }
        return chain.proceed(requestBuilder.build())
    }
}