package com.sweet.iva.feature.home.dashboard.viewmodel

import com.sweet.iva.core.designsystem.component.model.UserCardUiModel
import com.sweet.iva.core.ui.viewmodel.BaseViewModel
import com.sweet.iva.feature.home.dashboard.model.DashboardAction
import com.sweet.iva.feature.home.dashboard.model.DashboardEvent
import com.sweet.iva.feature.home.dashboard.model.DashboardUiModel
import com.sweet.iva.feature.home.dashboard.model.mockAccounts
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel
    @Inject
    constructor() :
    BaseViewModel<DashboardUiModel, DashboardAction, DashboardEvent>(
            initialState = DashboardUiModel(),
        ) {
        override fun handleAction(action: DashboardAction) {
            when (action) {
                is DashboardAction.PanChanged -> changeCardPan(action.cardIndex, action.pan)
                is DashboardAction.NameChanged -> changeCardName(action.cardIndex, action.name)
                is DashboardAction.MonthChanged -> changeCardMonth(action.cardIndex, action.month)
                is DashboardAction.YearChanged -> changeCardYear(action.cardIndex, action.year)
            }
        }

        private fun changeCardYear(
            cardIndex: Int,
            year: String,
        ) {
            updateState {
                it.copy(
                    userCards =
                        updateUserCardAtIndex(it.userCards, cardIndex) { oldCard ->
                            oldCard.copy(year = year)
                        },
                )
            }
        }

        private fun changeCardMonth(
            cardIndex: Int,
            month: String,
        ) {
            updateState {
                it.copy(
                    userCards =
                        updateUserCardAtIndex(it.userCards, cardIndex) { oldCard ->
                            oldCard.copy(
                                month = month,
                            )
                        },
                )
            }
        }

        private fun changeCardName(
            index: Int,
            name: String,
        ) {
            updateState {
                it.copy(
                    userCards =
                        updateUserCardAtIndex(it.userCards, index) { oldCard ->
                            oldCard.copy(name = name)
                        },
                )
            }
        }

        private fun updateUserCardAtIndex(
            source: List<UserCardUiModel>,
            index: Int,
            update: (card: UserCardUiModel) -> UserCardUiModel,
        ): List<UserCardUiModel> =
            mutableListOf<UserCardUiModel>().apply {
                addAll(source)
                removeAt(index)
                add(index, update.invoke(source[index]))
            }

        private fun isPanValid(pan: String): Boolean = pan.length <= 16

        private fun changeCardPan(
            index: Int,
            pan: String,
        ) {
            updateState {
                it.copy(
                    userCards =
                        updateUserCardAtIndex(it.userCards, index) { oldCard ->
                            oldCard.copy(pan = pan)
                        },
                )
            }
        }

        fun test() {
            updateState {
                it.copy(
                    userAccounts = mockAccounts,
                )
            }
        }
    }
