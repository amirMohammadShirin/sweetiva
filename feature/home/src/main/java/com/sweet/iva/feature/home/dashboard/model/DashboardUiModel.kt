package com.sweet.iva.feature.home.dashboard.model

import com.sweet.iva.core.designsystem.component.model.BannerUiModel
import com.sweet.iva.core.designsystem.component.model.FeatureUiModel
import com.sweet.iva.core.designsystem.component.model.UserAccountUiModel
import com.sweet.iva.core.designsystem.component.model.UserCardUiModel
import com.sweet.iva.core.ui.util.ColorUtil.asColor
import com.sweet.iva.feature.home.R

val bannersMock = listOf(
    BannerUiModel(
        "1",
        com.sweet.iva.core.designsystem.R.drawable.banner3
    ),
    BannerUiModel(
        "2",
        com.sweet.iva.core.designsystem.R.drawable.banner2
    ),
    BannerUiModel(
        "3",
        com.sweet.iva.core.designsystem.R.drawable.banner1
    ),
)
val mockFrequentFeatures = listOf(
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

val mockCards = mutableListOf<UserCardUiModel>().apply {
    add(
        UserCardUiModel(
            id = "saman",
            bankName = "بانک سامان",
            name = "امیر محمد شیرین",
            contentColor = "#006fb8".asColor(),
            containerColor = "#CBECFB".asColor(),
            pan = "6037 9972 6372 8496",
            month = "08",
            year = "1405",
            bankImage = R.drawable.ic_saman_bank
        )
    )
    add(
        UserCardUiModel(
            id = "eghtesad",
            bankName = "بانک اقتصاد نوین",
            name = "امیر محمد شیرین",
            contentColor = "#97199a".asColor(),
            containerColor = "#ead1eb".asColor(),
            pan = "6037 9972 6372 8496",
            month = "08",
            year = "1405",
            bankImage = R.drawable.ic_eghtesad_novin_bank
        )
    )
    add(
        UserCardUiModel(
            id = "mellat",
            bankName = "بانک ملت",
            name = "امیر محمد شیرین",
            contentColor = "#d32a3d".asColor(),
            containerColor = "#ebd6d6".asColor(),
            pan = "6037 9972 6372 8496",
            month = "08",
            year = "1405",
            bankImage = R.drawable.ic_mellat_bank
        )
    )
    add(
        UserCardUiModel(
            id = "pasargad",
            bankName = "بانک پاسارگاد",
            name = "امیر محمد شیرین",
            contentColor = "#fcb817".asColor(),
            containerColor = "#fef1d1".asColor(),
            pan = "6037 9972 6372 8496",
            month = "08",
            year = "1405",
            bankImage = R.drawable.ic_pasargad_bank
        )
    )
    add(
        UserCardUiModel(
            id = "keshavarzi",
            bankName = "بانک کشاورزی",
            name = "امیر محمد شیرین",
            contentColor = "#202d14".asColor(),
            containerColor = "#d2d5d0".asColor(),
            pan = "6037 9972 6372 8496",
            month = "08",
            year = "1405",
            bankImage = R.drawable.ic_keshavarzi_bank
        )
    )
    add(
        UserCardUiModel(
            id = "sepah",
            bankName = "بانک سپه",
            name = "امیر محمد شیرین",
            contentColor = "#e8651d".asColor(),
            containerColor = "#fae0d2".asColor(),
            pan = "6037 9972 6372 8496",
            month = "08",
            year = "1405",
            bankImage = R.drawable.ic_bank_sepah
        )
    )
    add(
        UserCardUiModel(
            id = "melli",
            bankName = "بانک ملی ایران",
            name = "امیر محمد شیرین",
            contentColor = "#D3C400".asColor(),
            containerColor = "#fffde6".asColor(),
            pan = "6037 9972 6372 8496",
            month = "08",
            year = "1405",
            bankImage = R.drawable.ic_melli_bank
        )
    )
}

data class DashboardUiModel(
    val loading: Boolean = false,
    val userCards: List<UserCardUiModel> = mockCards,
    val banners: List<BannerUiModel> = bannersMock,
    val frequentFeatures: List<FeatureUiModel> = mockFrequentFeatures,
    val bankFeatures: List<FeatureUiModel> = mockFrequentFeatures,
    val carFeatures: List<FeatureUiModel> = mockFrequentFeatures,
)

