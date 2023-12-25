package com.thiago.fitness.screens.login

import android.util.Patterns
import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseUser
import com.thiago.fitness.domain.model.Response
import com.thiago.fitness.domain.use_cases.auth.AuthUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val authUseCases: AuthUseCases) : ViewModel() {

    // STATE FORM
    var state by mutableStateOf(LoginState())
        private set

    // EMAIL VALIDATIONS
    var isEmailValid by mutableStateOf(false)
        private set

    var emailErrMsg by mutableStateOf("")
        private set

    // PASSWORD VALIDATIONS
    var isPasswordValid by mutableStateOf(false)
        private set
    var passwordErrMsg by mutableStateOf("")
        private set

    // ENABLE BUTTON
    var isEnabledLoginButton = false

    // LOGIN RESPONSE
    var loginResponse by mutableStateOf<Response<FirebaseUser>?>(null)
        private set

    val currentUser = authUseCases.getCurrentUser()

    init {
        if (currentUser != null) { // SESION INICIADA
            loginResponse = Response.Success(currentUser)
        }
    }

    fun onEmailInput(email: String) {
        state = state.copy(email = email)
    }

    fun onPasswordInput(password: String) {
        state = state.copy(password = password)
    }

    fun login() = viewModelScope.launch {
        loginResponse = Response.Loading
        val result = authUseCases.login(state.email, state.password)
        loginResponse = result
    }

    fun enabledLoginButton() {
        isEnabledLoginButton = isEmailValid && isPasswordValid
    }

    fun validateEmail() {
        // ES UN EMAIL VALIDO
        if (Patterns.EMAIL_ADDRESS.matcher(state.email).matches()) {
            isEmailValid = true
            emailErrMsg = ""
        }
        else {
            isEmailValid = false
            emailErrMsg = "O email não é valido"
        }

        enabledLoginButton()
    }

    fun validatePassword() {
        if (state.password.length >= 6) {
            isPasswordValid = true
            passwordErrMsg = ""
        }
        else {
            isPasswordValid = false
            passwordErrMsg = "Ao  menos 6 caracteres"
        }

        enabledLoginButton()
    }


}