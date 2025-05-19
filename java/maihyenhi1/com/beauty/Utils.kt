
package maihyenhi1.com.beauty

fun formatPrice(price: Int): String {
    return "$price.000đ"
}

// Helper function to safely parse price without throwing exceptions
fun safeParsePrice(priceString: String): Int {
    return try {
        priceString.replace(Regex("[^0-9]"), "").toInt()
    } catch (e: Exception) {
        0
    }
}