package org.sopt.and.presentation.util

import android.content.Context
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.compose.material3.SnackbarHostState
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import org.sopt.and.R

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


    fun transformationPasswordVisual(isVisible: Boolean): VisualTransformation =
        if (isVisible) VisualTransformation.None else PasswordVisualTransformation()

    fun Context.showToast(
        @StringRes message: Int
    ) = Toast.makeText(
        this,
        this.getString(message),
        Toast.LENGTH_SHORT
    ).show()

    fun Context.showSnackbar(
        scope: CoroutineScope,
        snackbarHostState: SnackbarHostState,
        @StringRes message: Int
    ) = scope.launch {
        snackbarHostState.showSnackbar(message = getString(message))
    }
}