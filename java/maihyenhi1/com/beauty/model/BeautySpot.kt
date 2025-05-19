package maihyenhi1.com.beauty.model

import maihyenhi1.com.beauty.R

data class BeautySpot(
    val id: Int,
    val name: String,
    val address: String,
    val imageUrl: String,
    val imageResIds: List<Int>,
    val rating: Float,
    val description: String,
    val services: List<Service>,
    val reviews: List<Review>,
    val category: String,
    val mapUrl: String // Added new property for Google Maps URL
)

data class Service(
    val name: String,
    val price: Int,
    val durationMinutes: Int
)

data class Review(
    val userName: String,
    val rating: Float,
    val comment: String,
    val date: String
)
data class PromotionCardInfo(
    val title: String,
    val logo: Int,
    val imageRes: Int,
    val buttonText: String,
    val route: String,
    val headerTitle: String // New field for the header title like "TOP 10 ĐỊA CHỈ LÀM TÓC UY TÍN"
)
