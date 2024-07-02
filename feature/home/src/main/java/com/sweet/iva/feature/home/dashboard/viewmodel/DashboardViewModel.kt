package com.sweet.iva.feature.home.dashboard.viewmodel

import com.sweet.iva.core.ui.viewmodel.BaseViewModel
import com.sweet.iva.feature.home.dashboard.model.DashboardAction
import com.sweet.iva.feature.home.dashboard.model.DashboardEvent
import com.sweet.iva.feature.home.dashboard.model.DashboardUiModel
import com.sweet.iva.feature.home.dashboard.model.UserCardUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor() :
    BaseViewModel<DashboardUiModel, DashboardAction, DashboardEvent>(
        initialState = DashboardUiModel()
    ) {

    override fun handleAction(action: DashboardAction) {
        when (action) {
            is DashboardAction.PanChanged -> changeCardPan(action.cardIndex, action.pan)
            is DashboardAction.NameChanged -> changeCardName(action.cardIndex, action.name)
        }
    }

    private fun changeCardName(index: Int, name: String) {
        updateState {
            it.copy(
                userCards = updateList(it.userCards, it.userCards[index].copy(name = name), index)
            )
        }
    }

    private fun updateList(
        source: List<UserCardUiModel>,
        newCard: UserCardUiModel,
        index: Int
    ): List<UserCardUiModel> {

        return mutableListOf<UserCardUiModel>().apply {

            addAll(source)
            removeAt(index)
            add(index, newCard)

        }

    }

    private fun isPanValid(pan: String): Boolean {
        return pan.length <= 16
    }

    private fun changeCardPan(index: Int, pan: String) {

        updateState {
            it.copy(
                userCards = updateList(it.userCards, it.userCards[index].copy(pan = pan), index)
            )
        }

    }
}