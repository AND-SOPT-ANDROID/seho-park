package org.sopt.and.presentation.util

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import android.content.Context
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.compose.material3.SnackbarHostState
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.sopt.and.R


// Utility object for common functions
object Utils {
    const val MIN_PASSWORD_LENGTH = 8
    const val MAX_PASSWORD_LENGTH = 20

    const val MYINFO_SCREEN_INDEX = 2
    const val SEARCH_SCREEN_INDEX = 1
    const val HOME_SCREEN_INDEX = 0

    const val GREETING_FIRST_LINE_FOCUS_START_INDEX = 0
    const val GREETING_FIRST_LINE_FOCUS_END_INDEX = 9
    const val GREETING_FIRST_LINE_END_INDEX = 12
    const val GREETING_SECOND_LINE_FOCUS_START_INDEX = 13
    const val GREETING_SECOND_LINE_FOCUS_END_INDEX = 24
    const val GREETING_SECOND_LINE_END_INDEX = 29

    val linkableSNS = listOf<Pair<Int, Int>>(
        Pair(R.drawable.kakao_talk_icon, R.string.link_kakao_icon_description),
        Pair(R.drawable.t_world_icon, R.string.link_tworld_icon_description),
        Pair(R.drawable.naver_icon, R.string.link_naver_icon_description),
        Pair(R.drawable.facebook_icon, R.string.link_facebook_icon_description),
        Pair(R.drawable.apple_icon, R.string.link_apple_icon_description),
    )

    // Visual transformation for password visibility
    fun transformationPasswordVisual(isVisible: Boolean): VisualTransformation =
        if (isVisible) VisualTransformation.None else PasswordVisualTransformation()

    // Show a Toast message
    fun Context.showToast(
        @StringRes message: Int
    ) = Toast.makeText(
        this,
        this.getString(message),
        Toast.LENGTH_SHORT
    ).show()

    // Show a Snackbar message
    fun Context.showSnackbar(
        scope: CoroutineScope,
        snackbarHostState: SnackbarHostState,
        @StringRes message: Int
    ) = scope.launch {
        snackbarHostState.showSnackbar(message = getString(message))
    }
}

// UiState, UiEvent, and UiEffect interfaces
interface UiState
interface UiEvent
interface UiEffect

// Base ViewModel class with generic state, event, and effect
abstract class BaseViewModel<State: UiState, Event: UiEvent, Effect: UiEffect>(
    initialState: State
) : ViewModel() {

    // UI State management
    private val _uiState: MutableStateFlow<State> = MutableStateFlow(initialState)
    val currentState: State
        get() = _uiState.value

    val uiState = _uiState.asStateFlow()

    // Event handling
    private val _event: MutableSharedFlow<Event> = MutableSharedFlow()
    val event = _event.asSharedFlow()

    // Effect handling
    private val _effect: Channel<Effect> = Channel()
    val effect = _effect.receiveAsFlow()

    init {
        subscribeEvents()
    }

    // Abstract function to handle state changes based on events
    protected abstract fun reduceState(event: Event)

    // Function to post effects (to be observed)
    protected fun postEffect(effect: Effect) {
        viewModelScope.launch {
            _effect.send(effect)
        }
    }

    // Subscribe to events and call reduceState
    private fun subscribeEvents() {
        viewModelScope.launch {
            event.collect {
                reduceState(it)
            }
        }
    }

    // Update the current state
    protected fun updateState(currentState: State) {
        _uiState.update {
            currentState
        }
    }

    // Send events to trigger state changes
    fun sendEvent(event: Event) {
        viewModelScope.launch { _event.emit(event) }
    }
}