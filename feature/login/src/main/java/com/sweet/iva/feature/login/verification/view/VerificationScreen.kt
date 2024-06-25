package com.sweet.iva.feature.login.verification.view

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ProvideTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.hilt.navigation.compose.hiltViewModel
import com.sweet.iva.core.designsystem.component.AppBackground
import com.sweet.iva.core.designsystem.component.AppPrimaryButton
import com.sweet.iva.core.designsystem.component.AppToolbar
import com.sweet.iva.core.designsystem.component.OTPInput
import com.sweet.iva.core.designsystem.theme.AppTheme
import com.sweet.iva.core.designsystem.theme.dimens
import com.sweet.iva.core.ui.navigation.ApplicationRoutes
import com.sweet.iva.core.ui.navigation.NavigationParam
import com.sweet.iva.core.ui.view.BaseScreen
import com.sweet.iva.feature.login.R
import com.sweet.iva.feature.login.verification.model.VerificationAction
import com.sweet.iva.feature.login.verification.model.VerificationEvent
import com.sweet.iva.feature.login.verification.model.VerificationUiModel
import com.sweet.iva.feature.login.verification.viewmodel.VerificationViewModel

class VerificationScreen :
    BaseScreen<VerificationUiModel, VerificationAction, VerificationEvent>(
        route = ApplicationRoutes.loginVerificationScreenRoute,
        name = "فعال سازی"
    ) {
    @Composable
    override fun viewModel(): VerificationViewModel = hiltViewModel()

    @Composable
    override fun Content(state: VerificationUiModel) {

        val viewModel = viewModel()

        LaunchedEffect(Unit) {
            viewModel.process(
                VerificationAction.StorePhoneNumber(parameters[NavigationParam.PHONE_NUMBER] ?: "")
            )
        }

        VerificationContent(
            onToolbarIconClicked = { viewModel.navigateBack() },
            phoneNumber = parameters[NavigationParam.PHONE_NUMBER],
            countDownValue = state.timer.value,
            isTimeEnded = state.timer.finished,
            verificationCodeLength = state.verificationCode.length,
            verificationCode = state.verificationCode.value,
            onVerificationCodeChanged = {

            },
            onSubmitButtonClicked = {}
        )

    }

    @Composable
    private fun VerificationContent(
        onToolbarIconClicked: () -> Unit,
        phoneNumber: String?,
        countDownValue: String,
        isTimeEnded: Boolean,
        verificationCode: String,
        verificationCodeLength: Int,
        onVerificationCodeChanged: (String) -> Unit,
        onSubmitButtonClicked: () -> Unit
    ) {

        val submitButtonEnabled =
            !isTimeEnded && (verificationCode.length == verificationCodeLength)

        ConstraintLayout(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {

            val (
                toolbarRef,
                headerRef,
                counterRef,
                otpRef,
                submitRef
            ) = createRefs()

            AppToolbar(
                modifier = Modifier
                    .constrainAs(toolbarRef) {
                        top.linkTo(parent.top, MaterialTheme.dimens.defaultGap)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
                    .fillMaxWidth(),
                toolbarTitle = name,
                leftIcon = R.drawable.ic_arrow_left,
                rightIcon = R.drawable.iv_iva_with_text,
                onLeftIconClicked = onToolbarIconClicked
            )

            ProvideTextStyle(value = MaterialTheme.typography.titleLarge) {
                Text(
                    modifier = Modifier
                        .constrainAs(counterRef) {
                            top.linkTo(toolbarRef.bottom, MaterialTheme.dimens.largePadding)
                            start.linkTo(parent.start, MaterialTheme.dimens.largeGap)
                            end.linkTo(parent.end, MaterialTheme.dimens.largeGap)
                        }
                        .padding(MaterialTheme.dimens.smallPadding),
                    text = countDownValue,
                    color = MaterialTheme.colorScheme.primary
                )
            }

            phoneNumber?.let {

                ProvideTextStyle(value = MaterialTheme.typography.labelMedium) {
                    Text(
                        modifier = Modifier
                            .constrainAs(headerRef) {
                                top.linkTo(counterRef.bottom, MaterialTheme.dimens.largeGap)
                                start.linkTo(parent.start, MaterialTheme.dimens.defaultGap)
                                end.linkTo(parent.end, MaterialTheme.dimens.defaultGap)
                                width = Dimension.fillToConstraints
                            },
                        text = buildAnnotatedString {
                            append("کد فعال سازی به شماره")
                            withStyle(style = SpanStyle(MaterialTheme.colorScheme.primary)) {
                                append(" $it ")
                            }
                            append("ارسال شده است")
                            append("\n")
                            append("لطفا کد ارسال شده را در قسمت زیر ")
                            append("وارد نمایید")
                        },
                        textAlign = TextAlign.Center
                    )
                }

            }

            OTPInput(
                modifier = Modifier.constrainAs(otpRef) {
                    top.linkTo(headerRef.bottom, MaterialTheme.dimens.xLargeGap)
                    start.linkTo(parent.start, MaterialTheme.dimens.largeGap)
                    end.linkTo(parent.end, MaterialTheme.dimens.largeGap)
                    width = Dimension.wrapContent
                },
                value = verificationCode,
                onValueChange = onVerificationCodeChanged,
                size = verificationCodeLength
            )

            AppPrimaryButton(
                modifier = Modifier.constrainAs(submitRef) {
                    start.linkTo(parent.start, MaterialTheme.dimens.defaultGap)
                    end.linkTo(parent.end, MaterialTheme.dimens.defaultGap)
                    bottom.linkTo(parent.bottom, MaterialTheme.dimens.defaultGap)
                    width = Dimension.fillToConstraints
                },
                enabled = submitButtonEnabled,
                onClick = onSubmitButtonClicked,
                text = "تایید و ادامه"
            )

        }
    }

    @Preview
    @Composable
    private fun PreviewVerificationScreen() {
        AppTheme {
            AppBackground(modifier = Modifier) {

            }
            VerificationContent(
                onToolbarIconClicked = {},
                phoneNumber = "09210425101",
                countDownValue = "05:00",
                isTimeEnded = false,
                verificationCode = "554",
                verificationCodeLength = 4,
                onVerificationCodeChanged = {},
                onSubmitButtonClicked = {}
            )
        }
    }

}