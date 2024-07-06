package com.sweet.iva.feature.home.dashboard.view

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.hilt.navigation.compose.hiltViewModel
import com.sweet.iva.core.designsystem.component.AppBackground
import com.sweet.iva.core.designsystem.component.AppToolbar
import com.sweet.iva.core.designsystem.component.HorizontalUserCards
import com.sweet.iva.core.designsystem.component.ThemePreviews
import com.sweet.iva.core.designsystem.theme.AppTheme
import com.sweet.iva.core.designsystem.theme.dimens
import com.sweet.iva.core.ui.navigation.ApplicationRoutes
import com.sweet.iva.core.ui.view.BaseScreen
import com.sweet.iva.feature.home.R
import com.sweet.iva.feature.home.dashboard.model.DashboardAction
import com.sweet.iva.feature.home.dashboard.model.DashboardEvent
import com.sweet.iva.feature.home.dashboard.model.DashboardUiModel
import com.sweet.iva.feature.home.dashboard.viewmodel.DashboardViewModel

class DashboardScreen : BaseScreen<DashboardUiModel, DashboardAction, DashboardEvent>(
    route = ApplicationRoutes.dashboardScreenRoute,
    name = "خانه"
) {
    @Composable
    override fun viewModel(): DashboardViewModel = hiltViewModel()

    @Composable
    override fun Content(state: DashboardUiModel) {

        val viewModel = viewModel()

        ConstraintLayout(
            modifier = Modifier.fillMaxSize()
        ) {

            val (toolbarRef, cardsRef) = createRefs()

            DashboardToolbar(
                modifier = Modifier.constrainAs(toolbarRef) {
                    top.linkTo(parent.top, MaterialTheme.dimens.defaultGap)
                    end.linkTo(parent.end)
                    start.linkTo(parent.start)
                    width = Dimension.fillToConstraints
                },
                onLeftIconClicked = {},
                onRightIconClicked = {}
            )

            HorizontalUserCards(
                modifier = Modifier.constrainAs(cardsRef) {
                    top.linkTo(toolbarRef.bottom, MaterialTheme.dimens.defaultGap)
                    start.linkTo(parent.start, MaterialTheme.dimens.defaultGap)
                    end.linkTo(parent.end, MaterialTheme.dimens.defaultGap)
                    width = Dimension.fillToConstraints
                },
                cards = state.userCards,
                onPanChanged = { card, pan ->
                    viewModel.process(DashboardAction.PanChanged(card, pan))
                },
                onNameChanged = { card, name ->
                    viewModel.process(DashboardAction.NameChanged(card, name))
                },
                onMonthChanged = { card, month ->
                    viewModel.process(DashboardAction.MonthChanged(card, month))
                },
                onYearChanged = { card, year ->
                    viewModel.process(DashboardAction.YearChanged(card, year))
                }
            )

        }

    }

    @Composable
    private fun DashboardToolbar(
        modifier: Modifier,
        onLeftIconClicked: () -> Unit,
        onRightIconClicked: () -> Unit,
    ) {

        AppToolbar(
            modifier = modifier,
            toolbarTitle = "ایوا",
            onLeftIconClicked = onLeftIconClicked,
            leftIcon = R.drawable.ic_person,
            rightIcon = R.drawable.ic_more,
            onRightIconClicked = onRightIconClicked
        )

    }

    @ThemePreviews
    @Composable
    fun DashboardContent() {

        AppTheme {

            AppBackground(modifier = Modifier) {

            }
            Content(
                state = DashboardUiModel()
            )

        }

    }
}