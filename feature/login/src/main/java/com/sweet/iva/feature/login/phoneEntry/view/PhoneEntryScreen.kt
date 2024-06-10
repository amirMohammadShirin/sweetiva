package com.sweet.iva.feature.login.phoneEntry.view

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ProvideTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.hilt.navigation.compose.hiltViewModel
import com.sweet.iva.core.designsystem.component.AppBackground
import com.sweet.iva.core.designsystem.component.AppPrimaryButton
import com.sweet.iva.core.designsystem.component.AppTextField
import com.sweet.iva.core.designsystem.component.AppToolbar
import com.sweet.iva.core.designsystem.component.ThemePreviews
import com.sweet.iva.core.designsystem.theme.AppTheme
import com.sweet.iva.core.designsystem.theme.dimens
import com.sweet.iva.core.ui.navigation.ApplicationRoutes
import com.sweet.iva.core.ui.view.BaseScreen
import com.sweet.iva.feature.login.R
import com.sweet.iva.feature.login.phoneEntry.model.PhoneEntryAction
import com.sweet.iva.feature.login.phoneEntry.model.PhoneEntryEvent
import com.sweet.iva.feature.login.phoneEntry.model.PhoneEntryUiModel
import com.sweet.iva.feature.login.phoneEntry.model.PhoneNumberModel
import com.sweet.iva.feature.login.phoneEntry.viewmodel.PhoneEntryViewModel

/**
 * Created by aShirin on 6/9/2024.
 */
class PhoneEntryScreen : BaseScreen<PhoneEntryUiModel, PhoneEntryAction, PhoneEntryEvent>(
    name = "ورود",
    route = ApplicationRoutes.phoneEntryScreenRoute
) {
    @Composable
    override fun viewModel(): PhoneEntryViewModel = hiltViewModel()

    @Composable
    override fun Content(state: PhoneEntryUiModel) {

        val viewModel = viewModel()

        PhoneNumberEntryContent(
            onToolbarIconClicked = { viewModel.navigateBack() },
            phoneNumber = state.phoneNumberModel,
            onPhoneNumberChanged = { viewModel.process(PhoneEntryAction.OnPhoneNumberChanged(it)) }
        )

    }

    @Composable
    private fun PhoneNumberEntryContent(
        phoneNumber: PhoneNumberModel,
        onToolbarIconClicked: () -> Unit,
        onPhoneNumberChanged: (value: String) -> Unit
    ) {
        ConstraintLayout(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {

            val (toolbarRef, phoneNumberRef, topSpacerRef, termsRef, btnEnterRef) = createRefs()

            AppToolbar(
                modifier = Modifier
                    .constrainAs(toolbarRef) {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
                    .fillMaxWidth(),
                toolbarTitle = "ایوا",
                leftIcon = R.drawable.ic_arrow_left,
                onLeftIconClicked = onToolbarIconClicked
            )

            Spacer(
                modifier = Modifier
                    .constrainAs(topSpacerRef) {
                        top.linkTo(toolbarRef.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
                    .height(MaterialTheme.dimens.largeGap)
            )

            AppTextField(
                modifier = Modifier
                    .constrainAs(phoneNumberRef) {
                        top.linkTo(topSpacerRef.bottom, MaterialTheme.dimens.defaultGap)
                        start.linkTo(parent.start, MaterialTheme.dimens.defaultGap)
                        end.linkTo(parent.end, MaterialTheme.dimens.defaultGap)
                    }
                    .fillMaxWidth(),
                value = phoneNumber.value,
                onValueChange = onPhoneNumberChanged,
                isError = !phoneNumber.errorMessage.isNullOrEmpty(),
                supportingText = phoneNumber.errorMessage,
                label = "شماره تلفن همراه"
            )
            

            ProvideTextStyle(value = MaterialTheme.typography.labelSmall) {
                Text(
                    modifier = Modifier
                        .constrainAs(termsRef) {
                            top.linkTo(phoneNumberRef.bottom)
                            start.linkTo(phoneNumberRef.start,MaterialTheme.dimens.defaultGap)
                            end.linkTo(phoneNumberRef.end,MaterialTheme.dimens.defaultGap)
                            width = Dimension.fillToConstraints
                        },

                    textAlign = TextAlign.End,
                    color = MaterialTheme.colorScheme.primary,
                    text = "با ثبت نام و ورود قوانین و مقررات ایوا را می\u200Cپذیرم"
                )
            }

            AppPrimaryButton(
                modifier = Modifier
                    .constrainAs(btnEnterRef) {
                        top.linkTo(termsRef.bottom, MaterialTheme.dimens.largeGap)
                        start.linkTo(phoneNumberRef.start,MaterialTheme.dimens.defaultGap)
                        end.linkTo(phoneNumberRef.end,MaterialTheme.dimens.defaultGap)
                        width = Dimension.fillToConstraints
                    },
                onClick = { },
                text = "قبول شرایط و ادامه"
            )

        }
    }

    @ThemePreviews
    @Composable
    fun PreviewPhoneEntryScreen() {
        AppTheme {
            AppBackground(modifier = Modifier) {

            }
            PhoneNumberEntryContent(
                phoneNumber = PhoneNumberModel(),
                onToolbarIconClicked = { },
                onPhoneNumberChanged = {}
            )
        }
    }

}