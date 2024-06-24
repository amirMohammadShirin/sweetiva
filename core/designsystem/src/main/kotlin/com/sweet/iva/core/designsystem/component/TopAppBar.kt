package com.sweet.iva.core.designsystem.component

import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ProvideTextStyle
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.sweet.iva.core.designsystem.R
import com.sweet.iva.core.designsystem.icon.AppIcons
import com.sweet.iva.core.designsystem.theme.AppTheme
import com.sweet.iva.core.designsystem.theme.dimens

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBar(
    @StringRes titleRes: Int? = null,
    title: String? = null,
    navigationIcon: ImageVector? = null,
    navigationIconContentDescription: String = "navigationIconContentDescription",
    actionIcon: ImageVector? = null,
    actionIconContentDescription: String = "actionIconContentDescription",
    modifier: Modifier = Modifier,
    colors: TopAppBarColors = TopAppBarDefaults.centerAlignedTopAppBarColors(
        containerColor = MaterialTheme.colorScheme.surface,
        actionIconContentColor = MaterialTheme.colorScheme.onSurface,
        titleContentColor = MaterialTheme.colorScheme.onBackground,
        navigationIconContentColor = MaterialTheme.colorScheme.onSurface,
        scrolledContainerColor = MaterialTheme.colorScheme.onSurface
    ),
    onNavigationClick: () -> Unit = {},
    onActionClick: () -> Unit = {},
) {

    CenterAlignedTopAppBar(
        title = {
            ProvideTextStyle(value = MaterialTheme.typography.bodyMedium) {
                Text(
                    text = if (titleRes != null) stringResource(id = titleRes) else title ?: ""
                )
            }
        },
        navigationIcon = {
            navigationIcon?.let {
                IconButton(onClick = onNavigationClick) {
                    Icon(
                        imageVector = it,
                        contentDescription = navigationIconContentDescription
                    )
                }
            }
        },
        actions = {
            actionIcon?.let {
                IconButton(onClick = onActionClick) {
                    Icon(
                        imageVector = it,
                        contentDescription = actionIconContentDescription
                    )
                }
            }
        },
        colors = colors,
        modifier = modifier
            .clip(
                RoundedCornerShape(bottomStart = 10.dp, bottomEnd = 10.dp)
            ),
        scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior()
    )

}

@Composable
fun AppToolbar(
    modifier: Modifier,
    toolbarTitle: String,
    onLeftIconClicked: () -> Unit,
    leftIcon: Int,
    rightIcon: Int? = null
) {

    ConstraintLayout(
        modifier = modifier
            .padding(horizontal = MaterialTheme.dimens.largeGap)
            .clip(RoundedCornerShape(10.dp))
            .background(
                MaterialTheme.colorScheme.secondary,
                RoundedCornerShape(10.dp),
            )
    ) {

        val (titleRef, leftIconRef, rightIconRef) = createRefs()

        ProvideTextStyle(value = MaterialTheme.typography.bodyLarge) {
            Text(
                textAlign = TextAlign.Center,
                modifier = Modifier.constrainAs(titleRef) {
                    top.linkTo(parent.top, MaterialTheme.dimens.defaultGap)
                    start.linkTo(leftIconRef.end, MaterialTheme.dimens.defaultGap)
                    end.linkTo(rightIconRef.start, MaterialTheme.dimens.defaultGap)
                    bottom.linkTo(parent.bottom, MaterialTheme.dimens.defaultGap)
                    width = Dimension.fillToConstraints
                },
                text = toolbarTitle,
                color = MaterialTheme.colorScheme.onBackground
            )
        }

        Image(
            modifier = Modifier
                .constrainAs(leftIconRef) {
                    start.linkTo(parent.start, MaterialTheme.dimens.defaultGap)
                    top.linkTo(parent.top, MaterialTheme.dimens.defaultGap)
                    bottom.linkTo(parent.bottom, MaterialTheme.dimens.defaultGap)
                    end.linkTo(titleRef.start, MaterialTheme.dimens.defaultGap)
                    width = Dimension.wrapContent
                    height = Dimension.wrapContent
                }
                .size(
                    30.dp,
                    30.dp
                )
                .clickable {
                    onLeftIconClicked.invoke()
                },
            painter = painterResource(id = leftIcon),
            contentDescription = "left icon"
        )

        rightIcon?.let {
            Image(
                modifier = Modifier
                    .constrainAs(rightIconRef) {
                        end.linkTo(parent.end, MaterialTheme.dimens.defaultGap)
                        top.linkTo(parent.top, MaterialTheme.dimens.defaultGap)
                        bottom.linkTo(parent.bottom, MaterialTheme.dimens.defaultGap)
                        start.linkTo(titleRef.end, MaterialTheme.dimens.defaultGap)
                        width = Dimension.wrapContent
                        height = Dimension.wrapContent
                    }
                    .size(
                        40.dp,
                        40.dp
                    ),
                painter = painterResource(id = it),
                contentDescription = "right icon"
            )

        } ?: run {
            Box(
                modifier = Modifier
                    .constrainAs(rightIconRef) {
                        end.linkTo(parent.end, MaterialTheme.dimens.defaultGap)
                        top.linkTo(parent.top, MaterialTheme.dimens.defaultGap)
                        bottom.linkTo(parent.bottom, MaterialTheme.dimens.defaultGap)
                        start.linkTo(titleRef.end, MaterialTheme.dimens.defaultGap)
                        width = Dimension.wrapContent
                        height = Dimension.wrapContent
                    }
                    .size(
                        40.dp,
                        40.dp
                    ),
            )
        }

    }

}

@OptIn(ExperimentalMaterial3Api::class)
@ThemePreviews
@Composable
private fun AppTopAppBarPreview() {
    AppTheme {
        TopAppBar(
            titleRes = R.string.untitled,
            navigationIcon = AppIcons.Add,
            navigationIconContentDescription = "Navigation icon",
            actionIcon = AppIcons.MoreVert,
            actionIconContentDescription = "Action icon",
        )
    }
}

@ThemePreviews
@Composable
private fun AppToolbarPreview() {
    AppTheme {
        AppToolbar(
            modifier = Modifier.fillMaxWidth(),
            toolbarTitle = "ایوا",
            onLeftIconClicked = {},
            R.drawable.ic_iva
        )
    }
}

