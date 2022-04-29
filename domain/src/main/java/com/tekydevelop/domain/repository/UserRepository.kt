package com.tekydevelop.domain.repository

import com.tekydevelop.domain.model.User
import com.tekydevelop.domain.state.Resource
import kotlinx.coroutines.flow.Flow

interface UserRepository {

    suspend fun getUsers(): Flow<Resource<List<User>?>>
    suspend fun addUser(user: User) : Flow<Resource<User?>>
    suspend fun deleteUser(user: User) : Flow<Resource<User?>>

}