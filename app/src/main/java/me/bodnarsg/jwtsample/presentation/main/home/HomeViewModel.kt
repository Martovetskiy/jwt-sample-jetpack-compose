package me.bodnarsg.jwtsample.presentation.main.home

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import me.bodnarsg.jwtsample.domain.model.User
import me.bodnarsg.jwtsample.domain.usecase.GetMeUseCase
import me.bodnarsg.jwtsample.domain.usecase.LogoutUseCase
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getMeUseCase: GetMeUseCase,
    private val logoutUseCase: LogoutUseCase
): ViewModel() {
    private val _user: MutableStateFlow<User?> = MutableStateFlow(null)
    private val _isRefresh = mutableStateOf(false)

    private val _error: MutableState<String> = mutableStateOf("")

    val user: StateFlow<User?> = _user
    val isRefresh: State<Boolean> = _isRefresh
    val error: State<String> = _error

    init{
        fetch()
    }

    fun logout(){
        logoutUseCase()
    }

    fun fetch(){
        _error.value = ""
        _isRefresh.value = true
        viewModelScope.launch {
            getMeUseCase.invoke().fold(
                onSuccess = {
                    _user.value = it
                    _isRefresh.value = false
                },
                onFailure = {
                    _error.value = it.message ?: "Undef Error"
                    _isRefresh.value = false
                }
            )
        }

    }
}