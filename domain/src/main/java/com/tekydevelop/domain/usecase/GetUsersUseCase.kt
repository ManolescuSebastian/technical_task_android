package com.tekydevelop.domain.usecase

import com.tekydevelop.domain.model.User
import com.tekydevelop.domain.repository.UserRepository
import com.tekydevelop.domain.state.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetUsersUseCase @Inject constructor(private val userRepository: UserRepository) {

    suspend fun executeCase(): Flow<Resource<List<User>?>> {
        return userRepository.getUsers()
    }
}