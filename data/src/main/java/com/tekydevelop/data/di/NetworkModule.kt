package com.tekydevelop.data.di

import com.tekydevelop.data.BuildConfig
import com.tekydevelop.data.api.UsersApi
import com.tekydevelop.data.interceptor.AppInterceptor
import com.tekydevelop.data.repository.UserRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun providesHttpLoggingInterceptor() = AppInterceptor()

    @Singleton
    @Provides
    fun providesOkHttpClient(appInterceptor: AppInterceptor): OkHttpClient =
        OkHttpClient
            .Builder()
            .addInterceptor(appInterceptor)
            .build()

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BuildConfig.API_BASE_URL)
        .client(okHttpClient)
        .build()


    @Singleton
    @Provides
    fun provideApiService(retrofit: Retrofit): UsersApi = retrofit.create(UsersApi::class.java)


    @Singleton
    @Provides
    fun providesRepository(usersApi: UsersApi) = UserRepositoryImpl(usersApi)

}