package com.sweet.iva.feature.home.dashboard.view

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ProvideTextStyle
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
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
import com.sweet.iva.core.ui.util.ColorUtil.asColor
import com.sweet.iva.core.ui.view.BaseScreen
import com.sweet.iva.feature.home.R
import com.sweet.iva.feature.home.dashboard.model.DashboardAction
import com.sweet.iva.feature.home.dashboard.model.DashboardEvent
import com.sweet.iva.feature.home.dashboard.model.DashboardUiModel
import com.sweet.iva.feature.home.dashboard.model.UserCardUiModel
import com.sweet.iva.feature.home.dashboard.viewmodel.DashboardViewModel
import kotlinx.coroutines.launch

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
                },
                cards = state.userCards
            )

        }

    }

    @OptIn(ExperimentalFoundationApi::class)
    @Composable
    private fun HorizontalUserCards(
        modifier: Modifier,
        cards: List<UserCardUiModel>
    ) {

        val pagerState =
            rememberPagerState(initialPage = 0, pageCount = { cards.size })


        HorizontalPager(
            modifier = modifier
                .padding(horizontal = MaterialTheme.dimens.largePadding),
            state = pagerState,
            pageSpacing = MaterialTheme.dimens.defaultGap,
        ) {

            val coroutineScope = rememberCoroutineScope()

            UserCard(
                cards[it],
                onLeftIconClicked = {
                    coroutineScope.launch {
                        pagerState.animateScrollToPage(it - 1)
                    }
                },
                onRightArrowClicked = {
                    coroutineScope.launch {
                        pagerState.animateScrollToPage(it + 1)
                    }
                }
            )

        }
    }

    @Composable
    private fun UserCard(
        card: UserCardUiModel,
        onRightArrowClicked: () -> Unit = {},
        onLeftIconClicked: () -> Unit = {}
    ) {
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
                defaultElevation = 1.dp,
                disabledElevation = 1.dp
            )
        ) {

            Surface(
                contentColor = Color.Transparent,
                color = card.containerColor.asColor(),
                modifier = Modifier.fillMaxSize(),
            ) {
                Box(modifier = Modifier.fillMaxSize()) {

                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .paint(
                                painterResource(id = R.drawable.pattern_card),
                                contentScale = ContentScale.FillBounds
                            )
                    )

                    ConstraintLayout(
                        modifier = Modifier.fillMaxSize()
                    ) {

                        val (leftArrowRef, rightArrowRef, titleRef, iconRef, panRef,
                            nameRef,
                            expTimeRef) = createRefs()

                        Image(
                            modifier = Modifier
                                .constrainAs(leftArrowRef) {
                                    start.linkTo(parent.start, MaterialTheme.dimens.defaultGap)
                                    top.linkTo(parent.top)
                                    bottom.linkTo(parent.bottom)
                                    width = Dimension.value(8.dp)
                                    height = Dimension.value(8.dp)
                                }
                                .clickable { onLeftIconClicked.invoke() },
                            painter = painterResource(id = R.drawable.ic_double_arrow_left),
                            contentDescription = "left arrow",
                            colorFilter = ColorFilter.tint(card.contentColor.asColor())
                        )

                        Image(
                            modifier = Modifier
                                .constrainAs(rightArrowRef) {
                                    end.linkTo(parent.end, MaterialTheme.dimens.defaultGap)
                                    top.linkTo(parent.top)
                                    bottom.linkTo(parent.bottom)
                                    width = Dimension.value(8.dp)
                                    height = Dimension.value(8.dp)
                                }
                                .clickable { onRightArrowClicked.invoke() },
                            painter = painterResource(id = R.drawable.ic_double_arrow_right),
                            contentDescription = "left arrow",
                            colorFilter = ColorFilter.tint(card.contentColor.asColor())
                        )

                        Image(
                            modifier = Modifier.constrainAs(iconRef) {
                                top.linkTo(parent.top, MaterialTheme.dimens.defaultGap)
                                end.linkTo(rightArrowRef.start, MaterialTheme.dimens.defaultGap)
                                width = Dimension.value(55.dp)
                                height = Dimension.value(55.dp)
                            },
                            painter = painterResource(id = card.bankImage),
                            contentDescription = "ic bank"
                        )

                        ProvideTextStyle(value = MaterialTheme.typography.labelLarge) {
                            Text(
                                card.bankName,
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
                        ProvideTextStyle(value = MaterialTheme.typography.titleLarge) {
                            Text(
                                card.pan,
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
                        ProvideTextStyle(value = MaterialTheme.typography.labelLarge) {
                            Text(
                                card.cardHolderName,
                                modifier = Modifier.constrainAs(nameRef) {
                                    top.linkTo(panRef.bottom)
                                    end.linkTo(iconRef.end)
                                    bottom.linkTo(parent.bottom)
                                },
                                textAlign = TextAlign.Center,
                                color = Color(0XFF707070)
                            )
                        }
                        ProvideTextStyle(value = MaterialTheme.typography.labelLarge) {
                            Text(
                                card.expTime,
                                modifier = Modifier.constrainAs(expTimeRef) {
                                    top.linkTo(panRef.bottom)
                                    start.linkTo(panRef.start)
                                    bottom.linkTo(parent.bottom)
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
            Content(
                state = DashboardUiModel()
            )

        }

    }
}