package com.thiago.fitness.screens.signup

import android.util.Patterns
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.thiago.fitness.domain.model.Response
import com.thiago.fitness.domain.model.User
import com.thiago.fitness.domain.use_cases.auth.AuthUseCases
import com.thiago.fitness.domain.use_cases.users.UsersUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignupViewModel @Inject constructor(private val authUseCases: AuthUseCases, private val usersUseCases: UsersUseCases): ViewModel() {

    // STATE FORM
    var state by mutableStateOf(SignupState())
        private set

    // USERNAME
    var isUsernameValid by mutableStateOf(false)
        private set
    var usernameErrMsg by mutableStateOf("")
        private set

    // EMAIL
    var isEmailValid by mutableStateOf(false)
        private set
    var emailErrMsg by mutableStateOf("")
        private set

    // PASSWORD
    var isPasswordValid by mutableStateOf(false)
        private set
    var passwordErrMsg by mutableStateOf("")
        private set

    // CONFIRMAR CONTRASENA
    var isconfirmPassword by mutableStateOf(false)
        private set
    var confirmPasswordErrMsg by mutableStateOf("")
        private set

    // ENABLE BUTTON
    var isEnabledLoginButton = false

    var signupResponse by mutableStateOf<Response<FirebaseUser>?>(null)
        private set

    var user = User()

    fun onEmailInput(email: String) {
        state = state.copy(email = email)
    }

    fun onUsernameInput(username: String) {
        state = state.copy(username = username)
    }

    fun onPasswordInput(password: String) {
        state = state.copy(password = password)
    }

    fun onConfirmPasswordInput(confirmPassword: String) {
        state = state.copy(confirmPassword = confirmPassword)
    }

    fun onSignup() {
        user.username = state.username
        user.email = state.email
        user.password = state.password
        signup(user)
    }

    fun createUser() = viewModelScope.launch {
        user.id = authUseCases.getCurrentUser()!!.uid
        usersUseCases.create(user)
    }

    fun signup(user: User) = viewModelScope.launch {
        signupResponse = Response.Loading
        val result = authUseCases.signup(user)
        signupResponse = result
    }

    fun enabledLoginButton() {
        isEnabledLoginButton =
            isEmailValid &&
                    isPasswordValid &&
                    isUsernameValid &&
                    isconfirmPassword
    }

    fun validateConfirmPassword() {
        if (state.password == state.confirmPassword) {
            isconfirmPassword = true
            confirmPasswordErrMsg = ""
        }
        else {
            isconfirmPassword = false
            confirmPasswordErrMsg = "As senhas não coincidem"
        }
        enabledLoginButton()
    }

    fun validateUsername() {
        if (state.username.length >= 5) {
            isUsernameValid = true
            usernameErrMsg = ""
        }
        else {
            isUsernameValid = false
            usernameErrMsg = "Al menos 5 caracteres"
        }

        enabledLoginButton()
    }

    fun validateEmail() {
        // ES UN EMAIL VALIDO
        if (Patterns.EMAIL_ADDRESS.matcher(state.email).matches()) {
            isEmailValid = true
            emailErrMsg = ""
        }
        else {
            isEmailValid = false
            emailErrMsg = "O email não é válido"
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
            passwordErrMsg = "Ao menos 6 caracteres"
        }

        enabledLoginButton()
    }

}