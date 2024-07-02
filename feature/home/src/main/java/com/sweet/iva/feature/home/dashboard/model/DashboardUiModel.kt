package com.sweet.iva.feature.home.dashboard.model

import com.sweet.iva.feature.home.R

val mockCards = mutableListOf<UserCardUiModel>().apply {
    add(
        UserCardUiModel(
            id = "saman",
            bankName = "بانک سامان",
            name = "امیر محمد شیرین",
            contentColor = "#006fb8",
            containerColor = "#CBECFB",
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
            contentColor = "#97199a",
            containerColor = "#ead1eb",
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
            contentColor = "#d32a3d",
            containerColor = "#ebd6d6",
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
            contentColor = "#fcb817",
            containerColor = "#fef1d1",
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
            contentColor = "#202d14",
            containerColor = "#d2d5d0",
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
            contentColor = "#e8651d",
            containerColor = "#fae0d2",
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
            contentColor = "#D3C400",
            containerColor = "#fffde6",
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
)


data class UserCardUiModel(
    val id: String,
    val bankImage: Int,
    val bankName: String,
    val pan: String,
    val month: String,
    val year: String,
    var name: String,
    val containerColor: String,
    val contentColor: String
)