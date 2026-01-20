package com.example.presentation

import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import com.example.domain.model.ErrorMessage
import com.example.presentation.views.renderEmptyScreenView
import com.example.presentation.views.renderErrorFullScreen
import com.example.presentation.views.renderErrorPopup
import com.example.presentation.views.renderLoadingFullScreen
import com.example.presentation.views.renderLoadingPopup

sealed class StateRenderer<out S, O> {
    // S for ViewState and O for Output

    // Content State
    class ScreenContent<S, O>(val viewState: S) : StateRenderer<S, O>()

    // Loading States
    // 1- Popup Loading State
    data class LoadingPopup<S, O>(
        val viewState: S,
        @StringRes val loadingMessage: Int = R.string.loading_message,
    ) : StateRenderer<S, O>()

    // 2- Full Screen Loading State
    data class LoadingFullScreen<S, O>(
        val viewState: S,
        @StringRes val loadingMessage: Int = R.string.loading_message,
    ) : StateRenderer<S, O>()

    // Error States
    // 1- Popup Error State
    data class ErrorPopup<S, O>(
        val viewState: S,
        val errorMessage: ErrorMessage,
    ) : StateRenderer<S, O>()

    // 2- Full Screen Error State
    data class ErrorFullScreen<S, O>(
        val viewState: S,
        val errorMessage: ErrorMessage,
    ) : StateRenderer<S, O>()

    // Empty State
    data class Empty<S, O>(
        val viewState: S,
        @StringRes val emptyMessage: Int = R.string.empty_message,
    ) : StateRenderer<S, O>()

    // Success State
    data class Success<S, O>(val output: O) : StateRenderer<S, O>()

    // ScreenContent

    @Composable
    fun onUiState(action: @Composable (S) -> Unit): StateRenderer<S, O> {
        if (this is ScreenContent) {
            action(viewState)
        }
        return this
    }

    @Composable
    fun onLoadingState(action: @Composable (S) -> Unit): StateRenderer<S, O> {
        if (this is LoadingPopup) {
            action(viewState)
        } else if (this is LoadingFullScreen) {
            action(viewState)
        }
        return this
    }

    fun onSuccessState(action: (O) -> Unit): StateRenderer<S, O> {
        if (this is Success) {
            action(output)
        }
        return this
    }

    @Composable
    fun onErrorState(action: @Composable (S) -> Unit): StateRenderer<S, O> {
        if (this is ErrorPopup) {
            action(viewState)
        } else if (this is ErrorFullScreen) {
            action(viewState)
        }
        return this
    }

    fun onEmptyState(action: () -> Unit): StateRenderer<S, O> {
        if (this is Empty) {
            action()
        }
        return this
    }

    companion object {
        @Composable
        fun <S, O> of(
            retryAction: () -> Unit = {},
            stateRenderer: StateRenderer<S, O>,
            block: @Composable StateRenderer<S, O>.() -> Unit,

        ): StateRenderer<S, O> {
            stateRenderer.block()
            when (stateRenderer) {
                is Empty<S, O> -> renderEmptyScreenView(stateRenderer.emptyMessage)
                is ErrorFullScreen<S, O> -> renderErrorFullScreen(
                    stateRenderer.errorMessage,
                    retryAction,
                )

                is ErrorPopup<S, O> -> renderErrorPopup(stateRenderer.errorMessage, retryAction)
                is LoadingFullScreen<S, O> -> renderLoadingFullScreen(stateRenderer.loadingMessage)
                is LoadingPopup<S, O> -> renderLoadingPopup(stateRenderer.loadingMessage)
                else -> {}
            }
            return stateRenderer
        }
    }
}
