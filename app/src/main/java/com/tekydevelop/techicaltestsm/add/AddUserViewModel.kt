package com.tekydevelop.techicaltestsm.add

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tekydevelop.domain.model.User
import com.tekydevelop.domain.state.Resource
import com.tekydevelop.domain.state.Status
import com.tekydevelop.domain.usecase.AddUserUseCase
import com.tekydevelop.techicaltestsm.utils.UserStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddUserViewModel @Inject constructor(private val addUserUseCase: AddUserUseCase) : ViewModel() {

    private val _addedUser = MutableStateFlow<Resource<User?>>(Resource(Status.LOADING))
    val addedUser = _addedUser.asSharedFlow()

    private val _isUserAdded = MutableSharedFlow<UserStatus>()
    val isUserAdded = _isUserAdded.asSharedFlow()

    fun addUser(user: User) {
        _addedUser.value = Resource.loading()
        viewModelScope.launch {
            addUserUseCase.executeCase(user).catch {
                _addedUser.value = Resource.error(it.message.toString())
                _isUserAdded.emit(UserStatus.FAILED)
            }.collectLatest {
                _addedUser.emit(Resource.success(it.data))
                _isUserAdded.emit(UserStatus.ADDED)
            }
        }
    }
}