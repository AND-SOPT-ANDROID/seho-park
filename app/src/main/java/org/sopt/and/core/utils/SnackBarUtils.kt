package org.sopt.and.core.utils

import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult

object SnackBarUtils {
    private lateinit var snackBarHostState: SnackbarHostState

    fun init(snackBarHostState: SnackbarHostState) {
        SnackBarUtils.snackBarHostState = snackBarHostState
    }

    suspend fun showSnackBar(
        message: String,
        actionLabel: String? = null,
        onActionClick: (() -> Unit)? = null,
        duration: SnackbarDuration = SnackbarDuration.Short,
    ) {
        if (this::snackBarHostState.isInitialized) {
            val result = snackBarHostState.showSnackbar(
                message = message,
                actionLabel = actionLabel,
                duration = duration
            )
            if (result == SnackbarResult.ActionPerformed) {
                onActionClick?.invoke()
            }
        } else {
            throw UninitializedPropertyAccessException("SnackBarHostState가 초기화 되지 않았습니다. init()을 먼저 호출하세요")
        }
    }
}