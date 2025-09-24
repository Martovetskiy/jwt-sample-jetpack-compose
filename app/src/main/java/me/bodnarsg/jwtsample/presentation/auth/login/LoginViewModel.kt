package me.bodnarsg.jwtsample.presentation.auth.login

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import me.bodnarsg.jwtsample.domain.usecase.LoginUseCase
import me.bodnarsg.jwtsample.navigation.Routes
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase
): ViewModel() {
    private val _email: MutableState<String> = mutableStateOf("")
    private val _password: MutableState<String> = mutableStateOf("")
    private val _errorMessage: MutableState<String> = mutableStateOf("")

    val email: State<String> = _email
    val password: State<String> = _password
    val errorMessage: State<String> = _errorMessage

    fun setEmail(value: String) {
        _email.value = value
    }

    fun setPassword(value: String) {
        _password.value = value
    }


    fun login(navController: NavController){
        viewModelScope.launch {
            _errorMessage.value = ""
            loginUseCase(_email.value, _password.value).fold(
                onSuccess = {
                    navController.navigate(Routes.MAIN_GRAPH) {
                        popUpTo(Routes.AUTH_GRAPH) { inclusive = true }
                        launchSingleTop = true
                    }
                    Log.d("LoginViewModel", "login: success")
                },
                onFailure = {
                    _errorMessage.value = it.message ?: "Unknown error"
                    Log.e("LoginViewModel", "login: failure", it)
                }
            )
        }
    }
}