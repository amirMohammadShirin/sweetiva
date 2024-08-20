package com.sweet.iva.feature.home.useraccount.viewmodel

import androidx.lifecycle.viewModelScope
import com.sweet.iva.core.common.dispatcher.DispatcherProvider
import com.sweet.iva.core.ui.viewmodel.BaseViewModel
import com.sweet.iva.feature.home.useraccount.model.Account
import com.sweet.iva.feature.home.useraccount.model.UserAccountEvent
import com.sweet.iva.feature.home.useraccount.model.UserAccountUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class UserAccountViewModel @Inject constructor(
    private val dispatcherProvider: DispatcherProvider
) :
    BaseViewModel<UserAccountUiModel, UserAccountEvent>(
        initialState = UserAccountUiModel()
    ) {

    fun getUserAccounts() {
        viewModelScope.launch {
            withContext(dispatcherProvider.io) {
                delay(2000)
            }
            updateState {
                it.copy(
                    accounts = mockAccounts,
                )
            }
        }
    }

}

val mockAccounts = listOf(
    Account(
        name = "حساب قرض الحسنه",
        accountNumber = "0307160114004",
        id = "1",
        iban = "IR030716011400400000000",
        balance = "100,000,000"
    ),
    Account(
        name = "حساب جاری",
        accountNumber = "0307160114004",
        id = "2",
        iban = "IR030716011400400000000"
    ),
)
