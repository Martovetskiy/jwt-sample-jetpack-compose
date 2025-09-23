package me.bodnarsg.jwtsample.presentation.auth.registration

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import me.bodnarsg.jwtsample.domain.usecase.RegistrationUseCase
import javax.inject.Inject

@HiltViewModel
class RegistrationViewModel @Inject constructor(
    private val registrationUseCase: RegistrationUseCase
): ViewModel()
{
    private val _username: MutableState<String> = mutableStateOf("")
    private val _password: MutableState<String> = mutableStateOf("")
    private val _secondaryPassword: MutableState<String> = mutableStateOf("")
    private val _name: MutableState<String> = mutableStateOf("")

    val username: State<String> = _username
    val password: State<String> = _password
    val secondaryPassword: State<String> = _secondaryPassword
    val name: State<String> = _name

    fun setUsername(value: String) {
        _username.value = value
    }

    fun setPassword(value: String) {
        _password.value = value
    }

    fun setSecondaryPassword(value: String) {
        _secondaryPassword.value = value
    }

    fun setName(value: String) {
        _name.value = value
    }

    fun passwordsMatch(): Boolean {
        return _password.value == _secondaryPassword.value
    }

    fun registration(){
        viewModelScope.launch {
            registrationUseCase(_username.value, _password.value, _name.value).fold(
                onSuccess = {
                    //TODO: Navigate to the next screen
                    Log.d("RegistrationViewModel", "registration: success")
                },
                onFailure = {
                    //TODO: Show error message
                    Log.e("RegistrationViewModel", "registration: failure", it)
                }
            )
        }
    }

}