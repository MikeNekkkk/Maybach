package maihyenhi1.com.beauty.data

import maihyenhi1.com.beauty.R
import maihyenhi1.com.beauty.model.PromotionCardInfo

object topTenPromotions {

    val topTenPromotions = listOf(
        PromotionCardInfo(
            title = "HAIR SALON",
            logo =R.drawable.salon_icon,
            imageRes = R.drawable.salon_top10,
            buttonText = "Săn deal tóc",
            route = "detail_top10hair",
            headerTitle = "TOP 10 ĐỊA CHỈ LÀM TÓC UY TÍN"
        ),
        PromotionCardInfo(
            title = "SPA",
            logo =R.drawable.spa_icon,
            imageRes = R.drawable.spa_top10,
            buttonText = "Khám phá spa",
            route = "detail_top10spa",
            headerTitle = "TOP 10 ĐỊA CHỈ SPA & MASSAGE UY TÍN"
        ),
        PromotionCardInfo(
            title = "NAIL",
            logo =R.drawable.nail_icon,
            imageRes = R.drawable.nail_top10,
            buttonText = "Làm móng đẹp",
            route = "detail_top10nail",
            headerTitle = "TOP 10 ĐỊA CHỈ LÀM MÓNG UY TÍN"
        ),
    )
}