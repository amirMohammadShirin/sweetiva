package com.sweet.iva.core.designsystem.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ProvideTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.sweet.iva.core.designsystem.R
import com.sweet.iva.core.designsystem.component.model.FeatureUiModel
import com.sweet.iva.core.designsystem.theme.AppTheme
import com.sweet.iva.core.designsystem.theme.BlueRoyal500
import com.sweet.iva.core.designsystem.theme.dimens

/**
 * Created by aShirin on 7/7/2024.
 */

@Composable
fun HorizontalFeatureList(
    modifier: Modifier,
    name: String = "",
    containerColor: Color = MaterialTheme.colorScheme.background,
    features: List<FeatureUiModel>,
    actionIcon: Int = -1,
    onActionIconClicked: (() -> Unit)? = null,
    onFeatureClicked: (feature: FeatureUiModel) -> Unit
) {

    Card(
        modifier = modifier
            .padding(MaterialTheme.dimens.defaultPadding),
        colors = CardDefaults.cardColors(
            containerColor = containerColor,
        )
    ) {
        ConstraintLayout(
            modifier = Modifier.fillMaxWidth()
        ) {

            val (
                headerImageRef,
                nameRef,
                iconRef,
                featureListRef
            ) = createRefs()

            Image(
                modifier = Modifier.constrainAs(headerImageRef) {
                    top.linkTo(parent.top, MaterialTheme.dimens.defaultGap)
                    end.linkTo(parent.end, MaterialTheme.dimens.defaultGap)
                    width = Dimension.value(10.dp)
                    height = Dimension.value(10.dp)
                },
                painter = painterResource(id = R.drawable.ic_list_bullet),
                contentDescription = "headerImage",
                colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onBackground)
            )

            ProvideTextStyle(
                value = MaterialTheme.typography.labelMedium.copy(
                    fontWeight = FontWeight.Bold
                )
            ) {

                Text(
                    modifier = Modifier.constrainAs(nameRef) {
                        top.linkTo(parent.top, MaterialTheme.dimens.defaultGap)
                        end.linkTo(headerImageRef.start, MaterialTheme.dimens.defaultGap)
                        start.linkTo(iconRef.end, MaterialTheme.dimens.defaultGap)
                        bottom.linkTo(headerImageRef.bottom)
                        width = Dimension.fillToConstraints

                    },
                    text = name,
                    color = BlueRoyal500,
                    textAlign = TextAlign.End
                )

            }

            Image(
                modifier = Modifier.constrainAs(iconRef) {
                    start.linkTo(parent.start, MaterialTheme.dimens.defaultGap)
                    top.linkTo(parent.top, MaterialTheme.dimens.defaultGap)
                    width = Dimension.value(20.dp)
                    height = Dimension.value(20.dp)
                },
                painter = painterResource(id = actionIcon),
                contentDescription = "actionIcon",
                colorFilter = ColorFilter.tint(BlueRoyal500)
            )

            LazyRow(
                contentPadding = PaddingValues(MaterialTheme.dimens.smallPadding),
                modifier = Modifier.constrainAs(featureListRef) {
                    top.linkTo(nameRef.bottom, MaterialTheme.dimens.largeGap)
                    bottom.linkTo(parent.bottom, MaterialTheme.dimens.defaultGap)
                    start.linkTo(parent.start, MaterialTheme.dimens.largeGap)
                    end.linkTo(parent.end, MaterialTheme.dimens.largeGap)
                }
            ) {

                items(
                    count = features.size
                ) {

                    if (it == 0)
                        Spacer(modifier = Modifier.width(5.dp))

                    FeatureItem(
                        modifier = Modifier.size(60.dp, 60.dp),
                        feature = features[it]
                    )
                    Spacer(modifier = Modifier.width(5.dp))
                }

            }

        }
    }

}

@Composable
private fun FeatureItem(modifier: Modifier, feature: FeatureUiModel) {

    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFF5F5F5)
        )
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                modifier = Modifier.size(30.dp, 30.dp),
                painter = painterResource(id = feature.logo),
                contentDescription = "feature logo"
            )
            Spacer(modifier = Modifier.height(MaterialTheme.dimens.smallGap))
            ProvideTextStyle(
                value = MaterialTheme.typography.labelSmall.copy(
                    fontSize = 8.sp,
                    fontWeight = FontWeight.Bold
                )
            ) {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = feature.title,
                    color = MaterialTheme.colorScheme.onBackground,
                    textAlign = TextAlign.Center
                )
            }

        }
    }

}

@ThemePreviews
@Composable
fun PreviewHorizontalFeatureList() {

    AppTheme {

        val features = listOf(
            FeatureUiModel(
                "1",
                "کارت به کارت",
            ),
            FeatureUiModel(
                "2",
                "خرید بیمه",
            ),
            FeatureUiModel(
                "3",
                "نشان بانک",
            ),
            FeatureUiModel(
                "4",
                "عوارض خروج",
            ),
            FeatureUiModel(
                "5",
                "اینترنت",
            ),
            FeatureUiModel(
                "6",
                "شارژ",
            ),
            FeatureUiModel(
                "7",
                "موجودی",
            ),
        )

        AppBackground(modifier = Modifier) {

        }

        Column(
            modifier = Modifier.padding(MaterialTheme.dimens.defaultPadding)
        ) {
            HorizontalFeatureList(
                actionIcon = R.drawable.ic_edit,
                name = "خدمات پرداخت",
                modifier = Modifier.fillMaxWidth(),
                features = features,
                onFeatureClicked = {})
        }


    }

}
