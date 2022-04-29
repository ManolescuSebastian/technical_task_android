package com.tekydevelop.data.repository

import com.tekydevelop.data.api.UsersApi
import com.tekydevelop.domain.model.User
import com.tekydevelop.domain.repository.UserRepository
import com.tekydevelop.domain.state.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class UserRepositoryImpl(private val usersApi: UsersApi) : UserRepository {

    override suspend fun getUsers(): Flow<Resource<List<User>?>> {
        return flow {
            val userResponse = usersApi.getUsers()
            if (userResponse.isSuccessful) {
                val userList = userResponse.body()?.map { it.asDomain() }
                emit(Resource.success(userList))
            } else {
                emit(Resource.error(userResponse.message()))
            }
        }
    }

    override suspend fun addUser(user: User): Flow<Resource<User?>> {
        return flow {
            val userResponse = usersApi.addUser(user)
            if (userResponse.isSuccessful) {
                emit(Resource.success(userResponse.body()?.asDomain()))
            } else {
                emit(Resource.error(userResponse.message()))
            }
        }
    }

    override suspend fun deleteUser(user: User): Flow<Resource<User?>> {
        return flow {
            val userResponse = usersApi.deleteUser(user.id!!)
            if (userResponse.isSuccessful) {
                emit(Resource.success(user))
            } else {
                emit(Resource.error(userResponse.message()))
            }
        }
    }
}