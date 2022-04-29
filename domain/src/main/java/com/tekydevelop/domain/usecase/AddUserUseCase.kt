package com.tekydevelop.domain.usecase

import com.tekydevelop.domain.model.User
import com.tekydevelop.domain.repository.UserRepository
import com.tekydevelop.domain.state.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AddUserUseCase @Inject constructor(private val userRepository: UserRepository) {

    suspend fun executeCase(user: User): Flow<Resource<User?>> {
        return userRepository.addUser(user)
    }
}