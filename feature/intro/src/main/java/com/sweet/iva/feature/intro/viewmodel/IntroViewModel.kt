package com.sweet.iva.feature.intro.viewmodel

import com.sweet.iva.core.common.dispatcher.DispatcherProvider
import com.sweet.iva.core.ui.navigation.ApplicationRoutes
import com.sweet.iva.core.ui.navigation.NavigationCommand
import com.sweet.iva.core.ui.viewmodel.BaseViewModel
import com.sweet.iva.feature.intro.model.IntroAction
import com.sweet.iva.feature.intro.model.IntroEvent
import com.sweet.iva.feature.intro.model.IntroUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

/**
 * Created by aShirin on 6/8/2024.
 */
@HiltViewModel
class IntroViewModel @Inject constructor(
    private val dispatcherProvider: DispatcherProvider
) :
    BaseViewModel<IntroUiModel, IntroAction, IntroEvent>(
        initialState = IntroUiModel()
    ) {
    override fun handleAction(action: IntroAction) {
        when (action) {
            IntroAction.EntryButtonClicked -> {
                navigateToLogin()
            }
        }
    }

    private fun navigateToLogin() {
        navigateTo(
            NavigationCommand.ToScreen(ApplicationRoutes.loginGraphRoute)
        )
    }
}