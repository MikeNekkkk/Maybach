package maihyenhi1.com.beauty

import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import maihyenhi1.com.beauty.data.SampleData
import maihyenhi1.com.beauty.model.BeautySpot
import maihyenhi1.com.beauty.model.Service
import maihyenhi1.com.beauty.model.Review
import java.text.SimpleDateFormat
import java.util.*

enum class DetailTab {
    ABOUT, SERVICES, REVIEWS
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(
    spotId: Int,
    onBackClick: () -> Unit
) {
    val spot = SampleData.beautySpots.find { it.id == spotId }
        ?: return

    var selectedTab by remember { mutableStateOf(DetailTab.ABOUT) }
    var isWorkingHoursExpanded by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Quay lại",
                            tint = Color.White
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Transparent
                )
            )
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = padding.calculateTopPadding())
        ) {
            // Header image
            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(220.dp)
                ) {
                    // Use the beauty spot's specific image resource
                    val imageRes = spot.imageResIds.firstOrNull() ?: getDefaultImageForCategory(spot.category)

                    Image(
                        painter = painterResource(id = imageRes),
                        contentDescription = null,
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop
                    )
                }
            }

            // Logo and basic info
            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Logo
                    Box(
                        modifier = Modifier
                            .size(80.dp)
                            .clip(CircleShape)
                            .background(Color.White)
                            .padding(8.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = getLogoText(spot),
                            style = MaterialTheme.typography.headlineSmall,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.primary
                        )
                    }

                    Spacer(modifier = Modifier.width(16.dp))

                    // Name and address
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = spot.name,
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.Bold
                        )

                        Text(
                            text = spot.address,
                            style = MaterialTheme.typography.bodyMedium,
                            color = Color.DarkGray
                        )

                        // Rating row
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.padding(top = 4.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.Star,
                                contentDescription = null,
                                tint = Color(0xFFFFC107),
                                modifier = Modifier.size(20.dp)
                            )
                            Text(
                                text = "${spot.rating}",
                                style = MaterialTheme.typography.bodyMedium,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.padding(horizontal = 4.dp)
                            )

                            Text(
                                text = "${spot.reviews.size}",
                                style = MaterialTheme.typography.bodyMedium,
                                color = Color.Gray
                            )
                        }
                    }
                }
            }

            // Action buttons
            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    // Like button
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .weight(1f)
                            .clickable { }
                    ) {
                        Icon(
                            imageVector = Icons.Default.Favorite,
                            contentDescription = "Yêu thích",
                            tint = Color.Red.copy(alpha = 0.5f),
                            modifier = Modifier.size(24.dp)
                        )
                        Text(
                            text = "Yêu thích",
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }

                    // Chat button
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .weight(1f)
                            .clickable { }
                    ) {
                        Icon(
                            imageVector = Icons.Default.Comment,
                            contentDescription = "Tư vấn",
                            tint = Color.Gray,
                            modifier = Modifier.size(24.dp)
                        )
                        Text(
                            text = "Tư vấn",
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }

                    // Share button
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .weight(1f)
                            .clickable { }
                    ) {
                        Icon(
                            imageVector = Icons.Default.Share,
                            contentDescription = "Chia sẻ",
                            tint = Color.Gray,
                            modifier = Modifier.size(24.dp)
                        )
                        Text(
                            text = "Chia sẻ",
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                }
            }

            // Working time
            item {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                        .clickable { isWorkingHoursExpanded = !isWorkingHoursExpanded },
                    colors = CardDefaults.cardColors(
                        containerColor = Color(0xFFF8F9FA)
                    )
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp)
                    ) {
                        // Header row
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                imageVector = Icons.Default.Schedule,
                                contentDescription = null,
                                tint = Color.Gray
                            )
                            Text(
                                text = "Thời gian làm việc",
                                style = MaterialTheme.typography.bodyMedium,
                                modifier = Modifier
                                    .weight(1f)
                                    .padding(horizontal = 8.dp)
                            )

                            // Show today's status
                            val todayStatus = getTodayWorkingStatus()
                            Text(
                                text = todayStatus,
                                style = MaterialTheme.typography.bodyMedium,
                                fontWeight = FontWeight.Bold,
                                color = if (todayStatus.contains("Đang mở")) Color(0xFF4CAF50) else Color(0xFFFF5722)
                            )

                            Icon(
                                imageVector = if (isWorkingHoursExpanded) Icons.Default.ArrowDropUp else Icons.Default.ArrowDropDown,
                                contentDescription = null,
                                tint = Color(0xFF673AB7)
                            )
                        }

                        // Expanded content
                        if (isWorkingHoursExpanded) {
                            Divider(
                                modifier = Modifier.padding(vertical = 12.dp),
                                color = Color.LightGray
                            )

                            val workingHours = getWeeklyWorkingHours()
                            val currentDay = getCurrentDayOfWeek()

                            workingHours.forEach { (day, hours) ->
                                val isToday = day == currentDay
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(vertical = 4.dp)
                                        .then(
                                            if (isToday) {
                                                Modifier.background(
                                                    Color(0xFF673AB7).copy(alpha = 0.1f),
                                                    RoundedCornerShape(4.dp)
                                                ).padding(horizontal = 8.dp, vertical = 4.dp)
                                            } else {
                                                Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                                            }
                                        ),
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Text(
                                        text = day,
                                        style = MaterialTheme.typography.bodyMedium,
                                        fontWeight = if (isToday) FontWeight.Bold else FontWeight.Normal,
                                        color = if (isToday) Color(0xFF673AB7) else Color.DarkGray
                                    )
                                    Text(
                                        text = hours,
                                        style = MaterialTheme.typography.bodyMedium,
                                        fontWeight = if (isToday) FontWeight.Bold else FontWeight.Normal,
                                        color = if (isToday) Color(0xFF673AB7) else Color.Black
                                    )
                                }
                            }
                        }
                    }
                }
            }

            // Tab row - Reordered tabs: ABOUT, SERVICES, REVIEWS
            item {
                TabRow(
                    modifier = Modifier.fillMaxWidth(),
                    selectedTabIndex = selectedTab.ordinal
                ) {
                    Tab(
                        selected = selectedTab == DetailTab.ABOUT,
                        onClick = { selectedTab = DetailTab.ABOUT },
                        text = { Text("Về doanh nghiệp") }
                    )
                    Tab(
                        selected = selectedTab == DetailTab.SERVICES,
                        onClick = { selectedTab = DetailTab.SERVICES },
                        text = { Text("Dịch vụ") }
                    )
                    Tab(
                        selected = selectedTab == DetailTab.REVIEWS,
                        onClick = { selectedTab = DetailTab.REVIEWS },
                        text = { Text("Đánh giá") }
                    )
                }
            }

            // Content based on selected tab
            when (selectedTab) {
                DetailTab.ABOUT -> {
                    item {
                        Text(
                            text = "Giới thiệu",
                            style = MaterialTheme.typography.headlineMedium,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(16.dp)
                        )
                        if (spot.imageResIds.size >= 2) {
                            val firstImageRes = spot.imageResIds[spot.imageResIds.size - 2]
                            Image(
                                painter = painterResource(id = firstImageRes),
                                contentDescription = null,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(200.dp)
                                    .padding(horizontal = 16.dp)
                                    .clip(RoundedCornerShape(8.dp)),
                                contentScale = ContentScale.Crop
                            )
                            Spacer(modifier = Modifier.height(16.dp))
                        }
                        Text(
                            text = spot.description,
                            style = MaterialTheme.typography.bodyLarge,
                            modifier = Modifier.padding(16.dp)
                        )
                        if (spot.imageResIds.isNotEmpty()) {
                            val secondImageRes = spot.imageResIds.last()
                            Image(
                                painter = painterResource(id = secondImageRes),
                                contentDescription = null,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(200.dp)
                                    .padding(horizontal = 16.dp)
                                    .clip(RoundedCornerShape(8.dp)),
                                contentScale = ContentScale.Crop
                            )
                            Spacer(modifier = Modifier.height(16.dp))
                        }
                        Text(
                            text = "Bản đồ & Chi nhánh",
                            style = MaterialTheme.typography.headlineMedium,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(16.dp)
                        )

                        // Map using WebView to load Google Maps URL
                        if (spot.mapUrl.isNotEmpty()) {
                            GoogleMapView(
                                mapUrl = spot.mapUrl,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(300.dp)
                                    .padding(horizontal = 16.dp)
                                    .clip(RoundedCornerShape(8.dp))
                            )
                        } else {
                            // Fallback if no map URL is available
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(200.dp)
                                    .padding(horizontal = 16.dp)
                                    .clip(RoundedCornerShape(8.dp))
                                    .background(Color.LightGray),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = "Bản đồ không khả dụng",
                                    modifier = Modifier.align(Alignment.Center)
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(16.dp))
                    }
                }
                DetailTab.SERVICES -> {
                    item {
                        Text(
                            text = "Dịch vụ",
                            style = MaterialTheme.typography.headlineMedium,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(16.dp)
                        )
                    }

                    // Pass the index and spot parameters to ServiceListItem
                    itemsIndexed(spot.services) { index, service ->
                        ServiceListItem(service = service, index = index, spot = spot)
                    }
                }
                DetailTab.REVIEWS -> {
                    item {
                        // Reviews header with rating summary
                        Column(
                            modifier = Modifier.padding(16.dp)
                        ) {
                            Text(
                                text = "Đánh giá từ khách hàng",
                                style = MaterialTheme.typography.headlineMedium,
                                fontWeight = FontWeight.Bold
                            )

                            Spacer(modifier = Modifier.height(16.dp))

                            // Overall rating summary
                            Card(
                                modifier = Modifier.fillMaxWidth(),
                                colors = CardDefaults.cardColors(
                                    containerColor = Color(0xFFF8F9FA)
                                )
                            ) {
                                Row(
                                    modifier = Modifier.padding(16.dp),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Column(
                                        horizontalAlignment = Alignment.CenterHorizontally
                                    ) {
                                        Text(
                                            text = "${spot.rating}",
                                            style = MaterialTheme.typography.headlineLarge,
                                            fontWeight = FontWeight.Bold,
                                            color = Color(0xFF673AB7)
                                        )
                                        Row(
                                            verticalAlignment = Alignment.CenterVertically
                                        ) {
                                            repeat(5) { index ->
                                                Icon(
                                                    imageVector = Icons.Default.Star,
                                                    contentDescription = null,
                                                    tint = if (index < spot.rating.toInt()) {
                                                        Color(0xFFFFC107)
                                                    } else {
                                                        Color.LightGray
                                                    },
                                                    modifier = Modifier.size(16.dp)
                                                )
                                            }
                                        }
                                    }

                                    Spacer(modifier = Modifier.width(24.dp))

                                    Column {
                                        Text(
                                            text = "Dựa trên ${spot.reviews.size} đánh giá",
                                            style = MaterialTheme.typography.bodyMedium,
                                            color = Color.Gray
                                        )
                                        Text(
                                            text = "Chất lượng dịch vụ tuyệt vời",
                                            style = MaterialTheme.typography.bodyLarge,
                                            fontWeight = FontWeight.Medium
                                        )
                                    }
                                }
                            }
                        }
                    }

                    // Display reviews
                    if (spot.reviews.isEmpty()) {
                        item {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(32.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                Column(
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.Comment,
                                        contentDescription = null,
                                        modifier = Modifier.size(48.dp),
                                        tint = Color.LightGray
                                    )
                                    Spacer(modifier = Modifier.height(16.dp))
                                    Text(
                                        text = "Chưa có đánh giá nào",
                                        style = MaterialTheme.typography.bodyLarge,
                                        color = Color.Gray,
                                        textAlign = TextAlign.Center
                                    )
                                }
                            }
                        }
                    } else {
                        items(spot.reviews) { review ->
                            ReviewItem(review = review)
                        }
                    }
                }
            }
        }
    }
}

// Helper functions for working hours
fun getWeeklyWorkingHours(): List<Pair<String, String>> {
    return listOf(
        "Thứ 2" to "08:30 - 19:00",
        "Thứ 3" to "08:30 - 19:00",
        "Thứ 4" to "08:30 - 19:00",
        "Thứ 5" to "08:30 - 19:00",
        "Thứ 6" to "08:30 - 19:00",
        "Thứ 7" to "08:30 - 19:00",
        "Chủ nhật" to "08:30 - 19:00"
    )
}

fun getCurrentDayOfWeek(): String {
    val calendar = Calendar.getInstance()
    return when (calendar.get(Calendar.DAY_OF_WEEK)) {
        Calendar.MONDAY -> "Thứ 2"
        Calendar.TUESDAY -> "Thứ 3"
        Calendar.WEDNESDAY -> "Thứ 4"
        Calendar.THURSDAY -> "Thứ 5"
        Calendar.FRIDAY -> "Thứ 6"
        Calendar.SATURDAY -> "Thứ 7"
        Calendar.SUNDAY -> "Chủ nhật"
        else -> "Thứ 2"
    }
}

fun getTodayWorkingStatus(): String {
    val calendar = Calendar.getInstance()
    val currentHour = calendar.get(Calendar.HOUR_OF_DAY)
    val currentMinute = calendar.get(Calendar.MINUTE)
    val currentTime = currentHour * 60 + currentMinute

    // Business hours: 08:30 - 19:00
    val openTime = 8 * 60 + 30  // 8:30 AM = 510 minutes
    val closeTime = 19 * 60     // 7:00 PM = 1140 minutes

    return when {
        currentTime in openTime until closeTime -> "Đang mở cửa"
        currentTime < openTime -> {
            val minutesUntilOpen = openTime - currentTime
            val hoursUntilOpen = minutesUntilOpen / 60
            val minutesRemainder = minutesUntilOpen % 60
            if (hoursUntilOpen > 0) {
                "Mở cửa sau ${hoursUntilOpen}h${if (minutesRemainder > 0) "${minutesRemainder}p" else ""}"
            } else {
                "Mở cửa sau ${minutesRemainder}p"
            }
        }
        else -> {
            "Đã đóng cửa"
        }
    }
}

@Composable
fun ReviewItem(review: Review) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 2.dp
        )
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            // Reviewer name and rating
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Avatar circle with first letter of name
                    Box(
                        modifier = Modifier
                            .size(40.dp)
                            .clip(CircleShape)
                            .background(Color(0xFF673AB7)),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = review.userName.first().toString().uppercase(),
                            color = Color.White,
                            fontWeight = FontWeight.Bold,
                            style = MaterialTheme.typography.bodyLarge
                        )
                    }

                    Spacer(modifier = Modifier.width(12.dp))

                    Text(
                        text = review.userName,
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold
                    )
                }

                Text(
                    text = review.date,
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.Gray
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Rating stars
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                repeat(5) { index ->
                    Icon(
                        imageVector = Icons.Default.Star,
                        contentDescription = null,
                        tint = if (index < review.rating.toInt()) {
                            Color(0xFFFFC107)
                        } else {
                            Color.LightGray
                        },
                        modifier = Modifier.size(16.dp)
                    )
                }

                Spacer(modifier = Modifier.width(8.dp))

                Text(
                    text = "${review.rating}/5",
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.Medium,
                    color = Color(0xFF673AB7)
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Review comment
            Text(
                text = review.comment,
                style = MaterialTheme.typography.bodyMedium,
                lineHeight = 20.sp
            )
        }
    }
}

@Composable
fun GoogleMapView(mapUrl: String, modifier: Modifier = Modifier) {
    AndroidView(
        factory = { context ->
            WebView(context).apply {
                layoutParams = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT
                )
                webViewClient = WebViewClient()
                settings.javaScriptEnabled = true
            }
        },
        update = { webView ->
            webView.loadUrl(mapUrl)
        },
        modifier = modifier
    )
}
@Composable
fun ServiceListItem(service: Service, index: Int, spot: BeautySpot) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Service image - using the beauty spot's images starting from the second image
        Box(
            modifier = Modifier
                .size(80.dp)
                .clip(RoundedCornerShape(8.dp))
                .background(Color.LightGray)
        ) {
            // Start with the second image (index 1) if available
            // Then continue with subsequent images
            val imageRes = if (spot.imageResIds.size > 1) {
                // Start from index 1 (second image) and cycle through available images
                // We add 1 to start from the second image, then use modulo to cycle
                spot.imageResIds[(index + 1) % spot.imageResIds.size]
            } else if (spot.imageResIds.isNotEmpty()) {
                // If there's only one image, use that
                spot.imageResIds[0]
            } else {
                // Fallback to placeholder if no images
                R.drawable.service_placeholder
            }

            Image(
                painter = painterResource(id = imageRes),
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
        }

        Spacer(modifier = Modifier.width(16.dp))

        // Service details
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = service.name,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Medium
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = formatPrice(service.price),
                style = MaterialTheme.typography.titleMedium,
                color = Color(0xFF673AB7),
                fontWeight = FontWeight.Bold
            )
        }
    }

    Divider(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp)
    )
}

fun getDefaultImageForCategory(category: String): Int {
    return when (category) {
        "Spa" -> R.drawable.spa_1_1
        "Hair" -> R.drawable.salon_2_2
        "Clinic" -> R.drawable.clinic_3_1
        "Nail" -> R.drawable.nail_5_1
        else -> R.drawable.massage_4_1
    }
}

fun getLogoText(spot: BeautySpot): String {
    // Create a logo text based on the shop name
    val words = spot.name.split(" ")
    return if (words.size > 1) {
        // Use first letter of first two words
        "${words[0].first()}${words[1].first()}"
    } else if (words.isNotEmpty() && words[0].length >= 2) {
        // Use first two letters of the first word
        words[0].substring(0, 2).uppercase()
    } else {
        // Fallback
        "BS"
    }
}
