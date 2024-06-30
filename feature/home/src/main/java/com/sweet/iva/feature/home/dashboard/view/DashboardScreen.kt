package com.sweet.iva.feature.home.dashboard.view

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ProvideTextStyle
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.hilt.navigation.compose.hiltViewModel
import com.sweet.iva.core.designsystem.component.AppBackground
import com.sweet.iva.core.designsystem.component.AppToolbar
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
                }
            )

        }

    }

    @OptIn(ExperimentalFoundationApi::class)
    @Composable
    private fun HorizontalUserCards(
        modifier: Modifier
    ) {

        val pagerState =
            rememberPagerState(initialPage = 0, pageCount = { 3 })

        HorizontalPager(
            modifier = modifier
                .padding(horizontal = MaterialTheme.dimens.largePadding),
            state = pagerState,
            pageSpacing = MaterialTheme.dimens.defaultGap,
        ) {

            UserCard()

        }
    }

    @Composable
    private fun UserCard() {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp),
            colors = CardDefaults.elevatedCardColors(
                containerColor = Color.Transparent,
                contentColor = Color.Transparent,
                disabledContainerColor = Color.Transparent,
                disabledContentColor = Color.Transparent,
            ),
            elevation = CardDefaults.elevatedCardElevation(
                defaultElevation = 2.dp,
                disabledElevation = 2.dp
            )
        ) {

            Surface(
                contentColor = Color.Transparent,
                color = Color(0XFFCBECFB),
                modifier = Modifier.fillMaxSize(),
            ) {
                Box(modifier = Modifier.fillMaxSize()) {

                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .paint(
                                painterResource(id = R.drawable.pattern_card)
                            )
                    )

                    ConstraintLayout(
                        modifier = Modifier.fillMaxSize()
                    ) {

                        val (leftArrowRef, rightArrowRef, titleRef, iconRef, panRef) = createRefs()

                        Image(
                            modifier = Modifier.constrainAs(leftArrowRef) {
                                start.linkTo(parent.start, MaterialTheme.dimens.defaultGap)
                                top.linkTo(parent.top)
                                bottom.linkTo(parent.bottom)
                                width = Dimension.value(8.dp)
                                height = Dimension.value(8.dp)
                            },
                            painter = painterResource(id = R.drawable.ic_double_arrow_left),
                            contentDescription = "left arrow",
                            colorFilter = ColorFilter.tint(Color(0XFF006fb8))
                        )

                        Image(
                            modifier = Modifier.constrainAs(rightArrowRef) {
                                end.linkTo(parent.end, MaterialTheme.dimens.defaultGap)
                                top.linkTo(parent.top)
                                bottom.linkTo(parent.bottom)
                                width = Dimension.value(8.dp)
                                height = Dimension.value(8.dp)
                            },
                            painter = painterResource(id = R.drawable.ic_double_arrow_right),
                            contentDescription = "left arrow",
                            colorFilter = ColorFilter.tint(Color(0XFF006fb8))
                        )

                        Image(
                            modifier = Modifier.constrainAs(iconRef) {
                                top.linkTo(parent.top, MaterialTheme.dimens.defaultGap)
                                end.linkTo(rightArrowRef.start, MaterialTheme.dimens.defaultGap)
                                width = Dimension.value(55.dp)
                                height = Dimension.value(55.dp)
                            },
                            painter = painterResource(id = R.drawable.ic_saman_bank),
                            contentDescription = "ic bank"
                        )

                        ProvideTextStyle(value = MaterialTheme.typography.labelLarge) {
                            Text(
                                "بانک سامان",
                                modifier = Modifier.constrainAs(titleRef) {
                                    top.linkTo(iconRef.top)
                                    start.linkTo(leftArrowRef.end)
                                    end.linkTo(rightArrowRef.start)
                                    bottom.linkTo(iconRef.bottom)
                                    width = Dimension.fillToConstraints
                                },
                                textAlign = TextAlign.Center,
                                color = Color(0XFF707070)
                            )
                        }
                        ProvideTextStyle(value = MaterialTheme.typography.labelLarge) {
                            Text(
                                "6037 9972 6372 8496",
                                modifier = Modifier.constrainAs(panRef) {
                                    top.linkTo(iconRef.bottom, MaterialTheme.dimens.xLargeGap)
                                    start.linkTo(leftArrowRef.end)
                                    end.linkTo(rightArrowRef.start)
                                    width = Dimension.fillToConstraints
                                },
                                textAlign = TextAlign.Center,
                                color = Color(0XFF707070)
                            )
                        }

                    }
                }

            }
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
            Content(state = DashboardUiModel())

        }

    }
}