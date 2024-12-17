package org.sopt.and.presentation.home

import org.sopt.and.core.ContentType
import org.sopt.and.domain.entity.HomeCommonContent
import org.sopt.and.domain.entity.HomeContent
import org.sopt.and.presentation.util.UiEffect
import org.sopt.and.presentation.util.UiEvent
import org.sopt.and.presentation.util.UiState

class HomeContract {
    data class HomeUiState(
        val mainContents: List<HomeContent> = emptyList(),

        val commonContents: List<HomeCommonContent> = emptyList(),

        val rankingContents: HomeCommonContent = HomeCommonContent(
            mainTitle = "",
            contentStates = emptyList()
        ),
        val selectedContentType: ContentType? = null
    ) : UiState

    sealed class HomeUiEvent : UiEvent {
        data class SetContentType(val contentType: ContentType) : HomeUiEvent()
    }

    sealed class HomeUiEffect : UiEffect
}