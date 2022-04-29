package com.tekydevelop.data.api

import com.tekydevelop.data.model.UserResponse
import com.tekydevelop.domain.model.User
import retrofit2.Response
import retrofit2.http.*

interface UsersApi {

    @GET("public/v2/users?page=1")
    suspend fun getUsers(): Response<List<UserResponse>>

    @POST("public/v2/users")
    suspend fun addUser(@Body user: User): Response<UserResponse>

    @DELETE("/public/v2/users/{id}")
    suspend fun deleteUser(@Path("id") id: Int) : Response<Any>
}