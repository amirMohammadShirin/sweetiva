package com.sweet.iva.feature.home.useraccount.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.sweet.iva.core.designsystem.component.AppBackground
import com.sweet.iva.core.designsystem.component.AppShimmer
import com.sweet.iva.core.designsystem.component.HorizontalUserAccounts
import com.sweet.iva.core.designsystem.theme.AppTheme
import com.sweet.iva.core.ui.DevicePreviews
import com.sweet.iva.core.ui.view.component.ViewComponent
import com.sweet.iva.feature.home.useraccount.model.Account
import com.sweet.iva.feature.home.useraccount.model.UserAccountEvent
import com.sweet.iva.feature.home.useraccount.model.UserAccountUiModel
import com.sweet.iva.feature.home.useraccount.viewmodel.UserAccountViewModel

class UserAccountView : ViewComponent<UserAccountUiModel, UserAccountEvent>() {
    @Composable
    override fun viewModel(): UserAccountViewModel = hiltViewModel()

    @Composable
    override fun Content(state: UserAccountUiModel) {

        val viewModel = viewModel()

        LaunchedEffect(Unit) {
            viewModel.getUserAccounts()
        }

        if (state.accounts.isEmpty()) {
            AppShimmer(
                modifier =
                Modifier
                    .fillMaxSize()
                    .height(180.dp),
            )
        } else {
            HorizontalUserAccounts(
                modifier = Modifier,
                accounts = state.accounts.map { it.toWidgetModel() },
                onIbanIconClicked = {},
                onAccountNumberIconClicked = {},
                onBalanceIconClicked = {},
                onSettingIconClicked = {},
            )
        }

    }
}

@DevicePreviews
@Composable
private fun PreviewUserAccount() {

    AppTheme {
        AppBackground(modifier = Modifier) {}
        Column(
            modifier = Modifier.fillMaxSize()
        ) {

            UserAccountView().Compose(modifier = Modifier)

        }
    }

}

private fun Account.toWidgetModel(): com.sweet.iva.core.designsystem.component.model.UserAccountUiModel {
    return com.sweet.iva.core.designsystem.component.model.UserAccountUiModel(
        id = id,
        accountNumber = accountNumber,
        name = name,
        iban = iban,
        balance = balance
    )
}

