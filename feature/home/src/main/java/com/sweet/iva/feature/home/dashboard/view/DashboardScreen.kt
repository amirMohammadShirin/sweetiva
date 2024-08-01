package com.sweet.iva.feature.home.dashboard.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.hilt.navigation.compose.hiltViewModel
import com.sweet.iva.core.designsystem.component.AppBackground
import com.sweet.iva.core.designsystem.component.AppShimmer
import com.sweet.iva.core.designsystem.component.AppToolbar
import com.sweet.iva.core.designsystem.component.HorizontalBannerList
import com.sweet.iva.core.designsystem.component.HorizontalFeatureList
import com.sweet.iva.core.designsystem.component.HorizontalUserAccounts
import com.sweet.iva.core.designsystem.component.ThemePreviews
import com.sweet.iva.core.designsystem.component.model.BannerUiModel
import com.sweet.iva.core.designsystem.component.model.FeatureUiModel
import com.sweet.iva.core.designsystem.theme.AppTheme
import com.sweet.iva.core.designsystem.theme.BlueRoyal100
import com.sweet.iva.core.designsystem.theme.dimens
import com.sweet.iva.core.ui.navigation.ApplicationRoutes
import com.sweet.iva.core.ui.view.BaseScreen
import com.sweet.iva.feature.home.R
import com.sweet.iva.feature.home.dashboard.model.DashboardEvent
import com.sweet.iva.feature.home.dashboard.model.DashboardUiModel
import com.sweet.iva.feature.home.dashboard.viewmodel.DashboardViewModel

class DashboardScreen :
    BaseScreen<DashboardUiModel, DashboardEvent>(
        route = ApplicationRoutes.dashboardScreenRoute,
        name = "خانه",
    ) {
    @Composable
    override fun viewModel(): DashboardViewModel = hiltViewModel()

    @Composable
    override fun Content(state: DashboardUiModel) {
        val viewModel = viewModel()

        LaunchedEffect(Unit) {
//            viewModel.process(DashboardAction.GetUserAccounts)
        }

        ConstraintLayout(
            modifier =
            Modifier
                .fillMaxSize(),
        ) {
            val (
                toolbarRef,
                contentRef,
            ) = createRefs()

            DashboardToolbar(
                modifier =
                Modifier.constrainAs(toolbarRef) {
                    top.linkTo(parent.top, MaterialTheme.dimens.defaultGap)
                    end.linkTo(parent.end)
                    start.linkTo(parent.start)
                    width = Dimension.fillToConstraints
                },
                onLeftIconClicked = {
                },
                onRightIconClicked = {
                },
            )

            Column(
                modifier =
                Modifier
                    .constrainAs(contentRef) {
                        top.linkTo(toolbarRef.bottom, MaterialTheme.dimens.smallGap)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        bottom.linkTo(parent.bottom, MaterialTheme.dimens.smallGap)
                        width = Dimension.fillToConstraints
                        height = Dimension.fillToConstraints
                    }
                    .verticalScroll(rememberScrollState())
                    .padding(
                        horizontal = MaterialTheme.dimens.defaultGap,
                        vertical = MaterialTheme.dimens.largePadding,
                    ),
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                if (state.userAccounts.isEmpty()) {
                    AppShimmer(
                        modifier =
                        Modifier
                            .fillMaxSize()
                            .height(180.dp),
                    )
                } else {
                    HorizontalUserAccounts(
                        modifier = Modifier,
                        accounts = state.userAccounts,
                        onIbanIconClicked = {},
                        onAccountNumberIconClicked = {},
                        onBalanceIconClicked = {},
                        onSettingIconClicked = {},
                    )
                }

                FrequentFeatures(
                    modifier = Modifier.fillMaxWidth(),
                    features = state.frequentFeatures,
                )

                BannerList(
                    modifier = Modifier.fillMaxWidth(),
                    banners = state.banners,
                )

                BankFeatures(
                    modifier = Modifier.fillMaxWidth(),
                    features = state.bankFeatures,
                )

                CarFeatures(
                    modifier = Modifier.fillMaxWidth(),
                    features = state.carFeatures,
                )
            }
        }
    }

    @Composable
    private fun CarFeatures(
        modifier: Modifier,
        features: List<FeatureUiModel>,
    ) {
        HorizontalFeatureList(
            actionIcon = com.sweet.iva.core.designsystem.R.drawable.ic_edit,
            name = "خدمات خودرو",
            modifier = modifier,
            features = features,
            onFeatureClicked = {},
        )
    }

    @Composable
    private fun BankFeatures(
        modifier: Modifier,
        features: List<FeatureUiModel>,
    ) {
        HorizontalFeatureList(
            containerColor = BlueRoyal100,
            actionIcon = com.sweet.iva.core.designsystem.R.drawable.ic_edit,
            name = "خدمات ویژه بانکی",
            modifier = modifier,
            features = features,
            onFeatureClicked = {},
        )
    }

    @Composable
    private fun BannerList(
        modifier: Modifier,
        banners: List<BannerUiModel>,
    ) {
        HorizontalBannerList(
            modifier = modifier.fillMaxWidth(),
            banners = banners,
            onBannerClicked = {},
        )
    }

    @Composable
    private fun FrequentFeatures(
        modifier: Modifier,
        features: List<FeatureUiModel>,
    ) {
        HorizontalFeatureList(
            actionIcon = com.sweet.iva.core.designsystem.R.drawable.ic_edit,
            name = "خدمات پر کاربرد",
            modifier = modifier,
            features = features,
            onFeatureClicked = {},
        )
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
            onRightIconClicked = onRightIconClicked,
        )
    }

    @ThemePreviews
    @Composable
    fun DashboardContent() {
        AppTheme {
            AppBackground(modifier = Modifier) {
            }
            Content(
                state = DashboardUiModel(),
            )
        }
    }
}
