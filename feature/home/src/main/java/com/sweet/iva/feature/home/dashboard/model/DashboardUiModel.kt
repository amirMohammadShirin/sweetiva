package com.sweet.iva.feature.home.dashboard.model

import com.sweet.iva.feature.home.R

val mockCards = mutableListOf<UserCardUiModel>().apply {
    add(
        UserCardUiModel(
            bankName = "بانک سامان",
            cardHolderName = "امیر محمد شیرین",
            contentColor = "#006fb8",
            containerColor = "#CBECFB",
            pan = "6037 9972 6372 8496",
            expTime = "تاریخ انقضا ۰۶/۰۸",
            bankImage = R.drawable.ic_saman_bank
        )
    )
    add(
        UserCardUiModel(
            bankName = "بانک اقتصاد نوین",
            cardHolderName = "امیر محمد شیرین",
            contentColor = "#97199a",
            containerColor = "#ead1eb",
            pan = "6037 9972 6372 8496",
            expTime = "تاریخ انقضا ۰۶/۰۸",
            bankImage = R.drawable.ic_eghtesad_novin_bank
        )
    )
    add(
        UserCardUiModel(
            bankName = "بانک ملت",
            cardHolderName = "امیر محمد شیرین",
            contentColor = "#d32a3d",
            containerColor = "#ebd6d6",
            pan = "6037 9972 6372 8496",
            expTime = "تاریخ انقضا ۰۶/۰۸",
            bankImage = R.drawable.ic_mellat_bank
        )
    )
    add(
        UserCardUiModel(
            bankName = "بانک پاسارگاد",
            cardHolderName = "امیر محمد شیرین",
            contentColor = "#fcb817",
            containerColor = "#fef1d1",
            pan = "6037 9972 6372 8496",
            expTime = "تاریخ انقضا ۰۶/۰۸",
            bankImage = R.drawable.ic_pasargad_bank
        )
    )
    add(
        UserCardUiModel(
            bankName = "بانک کشاورزی",
            cardHolderName = "امیر محمد شیرین",
            contentColor = "#202d14",
            containerColor = "#d2d5d0",
            pan = "6037 9972 6372 8496",
            expTime = "تاریخ انقضا ۰۶/۰۸",
            bankImage = R.drawable.ic_keshavarzi_bank
        )
    )
    add(
        UserCardUiModel(
            bankName = "بانک سپه",
            cardHolderName = "امیر محمد شیرین",
            contentColor = "#e8651d",
            containerColor = "#fae0d2",
            pan = "6037 9972 6372 8496",
            expTime = "تاریخ انقضا ۰۶/۰۸",
            bankImage = R.drawable.ic_bank_sepah
        )
    )
    add(
        UserCardUiModel(
            bankName = "بانک ملی ایران",
            cardHolderName = "امیر محمد شیرین",
            contentColor = "#D3C400",
            containerColor = "#fffde6",
            pan = "6037 9972 6372 8496",
            expTime = "تاریخ انقضا ۰۶/۰۸",
            bankImage = R.drawable.ic_melli_bank
        )
    )
}


data class DashboardUiModel(
    val loading: Boolean = false,
    val userCards: List<UserCardUiModel> = mockCards,
)


data class UserCardUiModel(
    val bankImage: Int,
    val bankName: String,
    val pan: String,
    val expTime: String,
    val cardHolderName: String,
    val containerColor: String,
    val contentColor: String
)