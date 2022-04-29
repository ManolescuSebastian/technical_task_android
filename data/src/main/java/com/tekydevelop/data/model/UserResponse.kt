package com.tekydevelop.data.model

import com.tekydevelop.data.mapper.DomainMapper
import com.tekydevelop.domain.model.User

data class UserResponse(
    val id: Int,
    val name: String?,
    val email: String?,
    val gender: String?,
    val status: String?
): DomainMapper<User>{
    override fun asDomain(): User {
        return User(id, name, email, gender, status)
    }
}
