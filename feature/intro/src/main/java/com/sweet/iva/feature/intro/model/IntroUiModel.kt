package com.sweet.iva.feature.intro.model

import com.sweet.iva.feature.intro.R

data class IntroUiModel(
    val introItems: List<IntroItemModel> = listOf(
        IntroItemModel(
            "ایوا",
            "ایوا یک اپلیکیشن پرداخت امن با امکانات متنوع و منحصر به فرد است",
            R.drawable.ic_iva_logo_diamond
        ),
        IntroItemModel(
            "خدمات منحصر به فرد",
            "در ایوا به خدمات بی نظیری چون پرداخت عوارض خروج از کشور و قبوض دولتی دسترسی دارید",
            R.drawable.ic_exit_toll
        ),
        IntroItemModel(
            "خلافی خودرو",
            "ریز خلافی های خودرو را به صورت رایگان استعلام و آن ها را پرداخت کنید",
            R.drawable.ic_driving_penalty
        ),
    )
)

data class IntroItemModel(
    val title:String,
    val description: String,
    val image: Int,
)
