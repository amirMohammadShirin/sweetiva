package com.sweet.iva.feature.login.verification.view

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import com.sweet.iva.core.designsystem.component.AppToolbar
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
            viewModel.startTimer(3000, 1000)
        }

        VerificationContent(
            onToolbarIconClicked = { viewModel.navigateBack() },
            phoneNumber = parameters[NavigationParam.PHONE_NUMBER],

            )

    }

    @Composable
    private fun VerificationContent(
        onToolbarIconClicked: () -> Unit,
        phoneNumber: String?
    ) {
        ConstraintLayout(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {

            val (toolbarRef, headerRef, counterRef) = createRefs()

            AppToolbar(
                modifier = Modifier
                    .constrainAs(toolbarRef) {
                        top.linkTo(parent.top, MaterialTheme.dimens.largeGap)
                        start.linkTo(parent.start, MaterialTheme.dimens.largeGap)
                        end.linkTo(parent.end, MaterialTheme.dimens.largeGap)
                    }
                    .fillMaxWidth(),
                toolbarTitle = name,
                leftIcon = R.drawable.ic_arrow_left,
                onLeftIconClicked = onToolbarIconClicked
            )

            ProvideTextStyle(value = MaterialTheme.typography.titleLarge) {
                Text(
                    modifier = Modifier
                        .constrainAs(counterRef) {
                            top.linkTo(toolbarRef.bottom, MaterialTheme.dimens.largePadding)
                            start.linkTo(parent.start, MaterialTheme.dimens.largeGap)
                            end.linkTo(parent.end, MaterialTheme.dimens.largeGap)
                        },
                    text = "04:59",
                    color = MaterialTheme.colorScheme.primary
                )
            }

            phoneNumber?.let {

                ProvideTextStyle(value = MaterialTheme.typography.labelMedium) {
                    Text(
                        modifier = Modifier
                            .constrainAs(headerRef) {
                                top.linkTo(counterRef.bottom, MaterialTheme.dimens.smallGap)
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
                        },
                        textAlign = TextAlign.Center,
                        color = MaterialTheme.colorScheme.outline
                    )
                }

            }

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
                phoneNumber = "09210425101"
            )
        }
    }

}