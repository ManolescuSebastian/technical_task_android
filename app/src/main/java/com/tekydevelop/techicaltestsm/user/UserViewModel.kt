package com.tekydevelop.techicaltestsm.user

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tekydevelop.domain.model.User
import com.tekydevelop.domain.state.Resource
import com.tekydevelop.domain.state.Status
import com.tekydevelop.domain.usecase.DeleteUserUseCase
import com.tekydevelop.domain.usecase.GetUsersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val userUseCase: GetUsersUseCase,
    private val deleteUserUseCase: DeleteUserUseCase
) : ViewModel() {

    private val _userData = MutableStateFlow<Resource<List<User>?>>(Resource(Status.LOADING))
    val userData = _userData.asStateFlow()

    private val _isUserDeleted = MutableStateFlow<Resource<User?>>(Resource(Status.LOADING))
    val isUserDeleted = _isUserDeleted.asSharedFlow()

    init {
        getUsers()
    }

    private fun getUsers() {
        _userData.value = Resource.loading()
        viewModelScope.launch {
            userUseCase.executeCase().catch {
                _userData.value = Resource.error(it.message.toString())
            }.collectLatest {
                _userData.value = Resource.success(it.data)
            }
        }
    }

    fun deleteUser(user: User) {
        _isUserDeleted.value = Resource.loading()
        viewModelScope.launch {
            deleteUserUseCase.executeCase(user).catch {
                _isUserDeleted.value = Resource.error(it.message.toString())
            }.collectLatest {
                _isUserDeleted.emit(Resource.success(it.data))
            }
        }
    }
}