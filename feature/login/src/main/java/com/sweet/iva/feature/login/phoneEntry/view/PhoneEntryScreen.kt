package com.sweet.iva.feature.login.phoneEntry.view

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.hilt.navigation.compose.hiltViewModel
import com.sweet.iva.core.designsystem.component.AppBackground
import com.sweet.iva.core.designsystem.component.AppTextField
import com.sweet.iva.core.designsystem.component.AppTextFieldLabel
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

        ConstraintLayout {

            val (toolbarRef, phoneNumberRef, topSpacerRef) = createRefs()

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
                onLeftIconClicked = {
                    viewModel.navigateBack()
                }
            )

            Spacer(
                modifier = Modifier
                    .constrainAs(topSpacerRef) {
                        top.linkTo(toolbarRef.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
                    .height(50.dp)
            )

            AppTextField(
                modifier = Modifier
                    .constrainAs(phoneNumberRef) {
                        top.linkTo(topSpacerRef.bottom, MaterialTheme.dimens.defaultGap)
                        start.linkTo(parent.start, MaterialTheme.dimens.defaultGap)
                        end.linkTo(parent.end, MaterialTheme.dimens.defaultGap)
                    }
                    .fillMaxWidth(),
                value = "",
                onValueChange = {},
                label = "شماره تلفن همراه"
            )

        }

    }

    @ThemePreviews
    @Composable
    fun PreviewPhoneEntryScreen() {
        AppTheme {
            AppBackground(modifier = Modifier) {

            }
            Content(state = PhoneEntryUiModel())
        }
    }

}