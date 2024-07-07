package com.sweet.iva.feature.home.dashboard.view

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.hilt.navigation.compose.hiltViewModel
import com.sweet.iva.core.designsystem.component.AppBackground
import com.sweet.iva.core.designsystem.component.AppToolbar
import com.sweet.iva.core.designsystem.component.HorizontalBannerList
import com.sweet.iva.core.designsystem.component.HorizontalFeatureList
import com.sweet.iva.core.designsystem.component.HorizontalUserAccounts
import com.sweet.iva.core.designsystem.component.HorizontalUserCards
import com.sweet.iva.core.designsystem.component.ThemePreviews
import com.sweet.iva.core.designsystem.component.model.BannerUiModel
import com.sweet.iva.core.designsystem.component.model.FeatureUiModel
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
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {

            val (
                toolbarRef,
                cardsRef,
                frequentFeaturesRef,
                bannerRef,
                bankFeaturesRef,
                carFeaturesRef
            ) = createRefs()

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

            HorizontalUserAccounts(
                modifier = Modifier.constrainAs(cardsRef) {
                    top.linkTo(toolbarRef.bottom, MaterialTheme.dimens.defaultGap)
                    start.linkTo(parent.start, MaterialTheme.dimens.defaultGap)
                    end.linkTo(parent.end, MaterialTheme.dimens.defaultGap)
                    width = Dimension.fillToConstraints
                },
                accounts = state.userAccounts,
                onIbanIconClicked = {},
                onAccountNumberIconClicked = {},
                onBalanceIconClicked = {},
                onSettingIconClicked = {}
            )

            FrequentFeatures(
                modifier = Modifier.constrainAs(frequentFeaturesRef) {
                    top.linkTo(cardsRef.bottom, MaterialTheme.dimens.smallGap)
                    start.linkTo(parent.start, MaterialTheme.dimens.largeGap)
                    end.linkTo(parent.end, MaterialTheme.dimens.largeGap)
                    width = Dimension.fillToConstraints
                },
                features = state.frequentFeatures
            )

            BannerList(
                modifier = Modifier.constrainAs(bannerRef) {
                    top.linkTo(frequentFeaturesRef.bottom, MaterialTheme.dimens.smallGap)
                    start.linkTo(parent.start, MaterialTheme.dimens.defaultGap)
                    end.linkTo(parent.end, MaterialTheme.dimens.defaultGap)
                    width = Dimension.fillToConstraints
                },
                banners = state.banners
            )

            BankFeatures(
                modifier = Modifier.constrainAs(bankFeaturesRef) {
                    top.linkTo(bannerRef.bottom, MaterialTheme.dimens.smallGap)
                    start.linkTo(parent.start, MaterialTheme.dimens.largeGap)
                    end.linkTo(parent.end, MaterialTheme.dimens.largeGap)
                    width = Dimension.fillToConstraints
                },
                features = state.bankFeatures
            )

            CarFeatures(
                modifier = Modifier.constrainAs(carFeaturesRef) {
                    top.linkTo(bankFeaturesRef.bottom, MaterialTheme.dimens.smallGap)
                    start.linkTo(parent.start, MaterialTheme.dimens.largeGap)
                    end.linkTo(parent.end, MaterialTheme.dimens.largeGap)
                    width = Dimension.fillToConstraints
                },
                features = state.carFeatures
            )

        }

    }

    @Composable
    private fun CarFeatures(modifier: Modifier, features: List<FeatureUiModel>) {
        HorizontalFeatureList(
            actionIcon = com.sweet.iva.core.designsystem.R.drawable.ic_edit,
            name = "خدمات پر خودرو",
            modifier = modifier,
            features = features,
            onFeatureClicked = {})
    }

    @Composable
    private fun BankFeatures(modifier: Modifier, features: List<FeatureUiModel>) {
        HorizontalFeatureList(
            actionIcon = com.sweet.iva.core.designsystem.R.drawable.ic_edit,
            name = "خدمات ویژه بانکی",
            modifier = modifier,
            features = features,
            onFeatureClicked = {})
    }

    @Composable
    private fun BannerList(modifier: Modifier, banners: List<BannerUiModel>) {
        HorizontalBannerList(
            modifier = modifier.fillMaxWidth(),
            banners = banners,
            onBannerClicked = {}
        )
    }

    @Composable
    private fun FrequentFeatures(modifier: Modifier, features: List<FeatureUiModel>) {
        HorizontalFeatureList(
            actionIcon = com.sweet.iva.core.designsystem.R.drawable.ic_edit,
            name = "خدمات پر کاربرد",
            modifier = modifier,
            features = features,
            onFeatureClicked = {})
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