package com.sweet.iva.core.designsystem.component

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sweet.iva.core.designsystem.R
import com.sweet.iva.core.designsystem.component.model.BannerUiModel
import com.sweet.iva.core.designsystem.theme.AppTheme
import com.sweet.iva.core.designsystem.theme.dimens

/**
 * Created by aShirin on 7/7/2024.
 */

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HorizontalBannerList(
    modifier: Modifier,
    banners: List<BannerUiModel>,
    onBannerClicked: ((banner: BannerUiModel) -> Unit)?
) {

    val pagerState = rememberPagerState(initialPage = 0, pageCount = { banners.size })

    HorizontalPager(
        reverseLayout = true,
        modifier = modifier
            .padding(horizontal = MaterialTheme.dimens.largePadding),
        state = pagerState,
        pageSpacing = MaterialTheme.dimens.defaultGap,
    ) {

        Banner(
            modifier = Modifier
                .fillMaxWidth()
                .height(80.dp),
            image = banners[it].img,
            onBannerClicked = {
                onBannerClicked?.invoke(banners[it])
            }
        )

    }

}

@Composable
private fun Banner(modifier: Modifier, image: Int, onBannerClicked: () -> Unit?) {

    Image(
        contentScale = ContentScale.FillBounds,
        modifier = modifier,
        painter = painterResource(id = image),
        contentDescription = "banner"
    )

}

@Preview
@Composable
fun PreviewHorizontalBannerList() {

    val banners = listOf(
        BannerUiModel(
            "1",
            R.drawable.banner3
        ),
        BannerUiModel(
            "2",
            R.drawable.banner2
        ),
        BannerUiModel(
            "3",
            R.drawable.banner1
        ),
    )

    AppTheme {

        AppBackground(modifier = Modifier) {

        }

        HorizontalBannerList(
            modifier = Modifier.fillMaxWidth(),
            banners = banners,
            onBannerClicked = {}
        )

    }

}