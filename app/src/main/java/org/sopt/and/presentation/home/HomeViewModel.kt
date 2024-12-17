package org.sopt.and.presentation.home

import dagger.hilt.android.lifecycle.HiltViewModel
import org.sopt.and.domain.repository.DummyHomeRepository
import org.sopt.and.presentation.home.HomeContract.HomeUiEffect
import org.sopt.and.presentation.home.HomeContract.HomeUiEvent
import org.sopt.and.presentation.home.HomeContract.HomeUiState
import org.sopt.and.presentation.util.BaseViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val dummyHomeContentRepository: DummyHomeRepository
) : BaseViewModel<HomeUiState, HomeUiEvent, HomeUiEffect>(HomeUiState()) {

    override fun reduceState(event: HomeUiEvent) {
        when (event) {
            is HomeUiEvent.SetContentType -> {
                updateState(
                    currentState.copy(
                        selectedContentType = event.contentType
                    )
                )
            }
        }
    }

    fun getDummyHomeContent() = updateState(
        currentState.copy(
            mainContents = dummyHomeContentRepository.getDummyMainContents(),
            commonContents = dummyHomeContentRepository.getDummyCommonContents(),
            rankingContents = dummyHomeContentRepository.getDummyRankingContents()
        )
    )
}