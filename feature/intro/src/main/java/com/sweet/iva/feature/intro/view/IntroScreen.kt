package com.sweet.iva.feature.intro.view

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ProvideTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.hilt.navigation.compose.hiltViewModel
import com.sweet.iva.core.designsystem.component.AppBackground
import com.sweet.iva.core.designsystem.component.AppPrimaryButton
import com.sweet.iva.core.designsystem.component.ThemePreviews
import com.sweet.iva.core.designsystem.theme.AppTheme
import com.sweet.iva.core.designsystem.theme.dimens
import com.sweet.iva.core.ui.navigation.ApplicationRoutes
import com.sweet.iva.core.ui.view.BaseScreen
import com.sweet.iva.feature.intro.R
import com.sweet.iva.feature.intro.model.IntroAction
import com.sweet.iva.feature.intro.model.IntroEvent
import com.sweet.iva.feature.intro.model.IntroItemModel
import com.sweet.iva.feature.intro.model.IntroUiModel
import com.sweet.iva.feature.intro.viewmodel.IntroViewModel

/**
 * Created by aShirin on 6/8/2024.
 */
class IntroScreen : BaseScreen<IntroUiModel, IntroAction, IntroEvent>(
    name = "intro",
    route = ApplicationRoutes.introScreenRoute
) {
    @Composable
    override fun viewModel(): IntroViewModel = hiltViewModel()

    @OptIn(ExperimentalFoundationApi::class)
    @Composable
    override fun Content(state: IntroUiModel) {

        val viewModel = viewModel()

        ConstraintLayout(
            modifier = Modifier
                .fillMaxSize()
        ) {

            val (btnEnter, pager, indicator) = createRefs()

            val pagerState =
                rememberPagerState(initialPage = 0, pageCount = { state.introItems.size })

            HorizontalPager(
                modifier = Modifier
                    .constrainAs(pager) {
                        top.linkTo(parent.top, MaterialTheme.dimens.largeGap)
                        start.linkTo(parent.start, MaterialTheme.dimens.defaultGap)
                        end.linkTo(parent.end, MaterialTheme.dimens.defaultGap)
                        bottom.linkTo(indicator.top, MaterialTheme.dimens.largeGap)
                        height = Dimension.fillToConstraints
                    }
                    .fillMaxSize(),
                state = pagerState
            ) {

                IntroItemView(
                    modifier = Modifier,
                    item = state.introItems[it]
                )

            }

            IntroIndicators(
                modifier = Modifier
                    .constrainAs(indicator) {
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        bottom.linkTo(btnEnter.top, MaterialTheme.dimens.defaultGap)
                    },
                itemCount = pagerState.pageCount,
                selectedIndex = pagerState.settledPage
            )

            AppPrimaryButton(
                modifier = Modifier
                    .constrainAs(btnEnter) {
                        bottom.linkTo(parent.bottom, MaterialTheme.dimens.defaultGap)
                        start.linkTo(parent.start, MaterialTheme.dimens.defaultGap)
                        end.linkTo(parent.end, MaterialTheme.dimens.defaultGap)
                        width = Dimension.fillToConstraints
                    },
                onClick = {
                    viewModel.process(IntroAction.EntryButtonClicked)
                },
                text = "ورود"
            )


        }

    }

    @Composable
    private fun IntroIndicators(modifier: Modifier, itemCount: Int, selectedIndex: Int) {

        Row(
            modifier = modifier,
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            repeat(itemCount) {
                Box(
                    modifier = Modifier
                        .size(10.dp, 10.dp)
                        .border(
                            width = 1.dp,
                            shape = CircleShape,
                            color = MaterialTheme.colorScheme.secondary
                        )
                        .background(
                            if (selectedIndex == it) MaterialTheme.colorScheme.secondary else MaterialTheme.colorScheme.background,
                            CircleShape
                        )
                ) {

                }
                Spacer(modifier = Modifier.width(2.dp))
            }
        }

    }

    @Composable
    private fun IntroItemView(
        modifier: Modifier,
        item: IntroItemModel
    ) {

        Column(
            modifier = modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(MaterialTheme.dimens.largePadding),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            Image(
                modifier = Modifier
                    .width(200.dp)
                    .height(200.dp),
                painter = painterResource(id = item.image),
                contentDescription = item.image.toString()
            )

            Spacer(modifier = Modifier.height(80.dp))

            ProvideTextStyle(
                value = MaterialTheme.typography.bodyLarge.copy(
                    fontWeight = FontWeight.Bold
                )
            ) {
                Text(
                    textAlign = TextAlign.Center,
                    text = item.title
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            ProvideTextStyle(
                value = MaterialTheme.typography.bodyLarge
            ) {
                Text(
                    textAlign = TextAlign.Center,
                    text = item.description
                )
            }

        }

    }

    @ThemePreviews
    @Composable
    private fun PreviewIntroItemView() {

        AppTheme {

            AppBackground(modifier = Modifier) {

            }

            val introItem = IntroItemModel(
                "ایوا",
                "برنامه ایوا دارای خدمات بسیار زیاد".repeat(3),
                R.drawable.ic_driving_penalty
            )

            IntroItemView(
                modifier = Modifier,
                item = introItem
            )

        }

    }


    @ThemePreviews
    @Composable
    private fun PreviewIntroScreen() {
        AppTheme {
            IntroScreen()
                .Screen(null)
        }

    }


}
