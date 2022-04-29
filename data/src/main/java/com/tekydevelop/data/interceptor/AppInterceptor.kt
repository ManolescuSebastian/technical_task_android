package com.tekydevelop.data.interceptor

import com.tekydevelop.data.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

/**
 * Use this interceptor to add headers to all requests
 */
class AppInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
        request.addHeader("Content-Type", "application/json")
        request.addHeader("Authorization", "Bearer ${BuildConfig.ACCESS_TOKEN}")

        return chain.proceed(request.build())
    }
}