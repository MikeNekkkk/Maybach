package maihyenhi1.com.beauty

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Star
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
import maihyenhi1.com.beauty.data.SampleData
import maihyenhi1.com.beauty.model.BeautySpot
import maihyenhi1.com.beauty.model.Service

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CompareScreen(
    onBackClick: () -> Unit
) {
    var selectedSpot1 by remember { mutableStateOf<BeautySpot?>(null) }
    var selectedSpot2 by remember { mutableStateOf<BeautySpot?>(null) }
    var showSpotSelector by remember { mutableStateOf(false) }
    var selectingForFirst by remember { mutableStateOf(true) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("So sánh dịch vụ") },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Quay lại"
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = Color.White,
                    navigationIconContentColor = Color.White
                )
            )
        }
    ) { paddingValues ->
        if (showSpotSelector) {
            SpotSelectorScreen(
                onSpotSelected = { spot ->
                    if (selectingForFirst) {
                        selectedSpot1 = spot
                    } else {
                        selectedSpot2 = spot
                    }
                    showSpotSelector = false
                },
                onCancelClick = { showSpotSelector = false }
            )
        } else {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(16.dp)
            ) {
                Text(
                    text = "Chọn hai địa điểm để so sánh",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(16.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    SpotSelector(
                        spot = selectedSpot1,
                        onClick = {
                            selectingForFirst = true
                            showSpotSelector = true
                        },
                        modifier = Modifier.weight(1f)
                    )

                    SpotSelector(
                        spot = selectedSpot2,
                        onClick = {
                            selectingForFirst = false
                            showSpotSelector = true
                        },
                        modifier = Modifier.weight(1f)
                    )
                }

                Spacer(modifier = Modifier.height(24.dp))

                if (selectedSpot1 != null && selectedSpot2 != null) {
                    ComparisonResult(spot1 = selectedSpot1!!, spot2 = selectedSpot2!!)
                } else {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "Vui lòng chọn hai địa điểm để so sánh",
                            style = MaterialTheme.typography.bodyLarge,
                            textAlign = TextAlign.Center,
                            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun SpotSelector(
    spot: BeautySpot?,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .height(180.dp),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        onClick = onClick
    ) {
        if (spot != null) {
            Column {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(120.dp)
                ) {
                    // Use the beauty spot's specific image resource from imageResIds
                    val imageRes = if (spot.imageResIds.isNotEmpty()) {
                        spot.imageResIds.first()
                    } else {
                        getDefaultImageForCategory(spot.category)
                    }

                    Image(
                        painter = painterResource(id = imageRes),
                        contentDescription = spot.name,
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop
                    )

                    Box(
                        modifier = Modifier
                            .align(Alignment.TopEnd)
                            .padding(8.dp)
                            .size(32.dp)
                            .clip(CircleShape)
                            .background(MaterialTheme.colorScheme.surface.copy(alpha = 0.9f)),
                        contentAlignment = Alignment.Center
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Text(
                                text = spot.rating.toString(),
                                style = MaterialTheme.typography.bodySmall,
                                fontWeight = FontWeight.Bold
                            )
                            Icon(
                                imageVector = Icons.Default.Star,
                                contentDescription = null,
                                tint = Color(0xFFFFC107),
                                modifier = Modifier.size(12.dp)
                            )
                        }
                    }
                }

                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(8.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = spot.name,
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center
                    )
                }
            }
        } else {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Chọn địa điểm",
                    style = MaterialTheme.typography.bodyLarge,
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colorScheme.primary
                )
            }
        }
    }
}

@Composable
fun SpotSelectorScreen(
    onSpotSelected: (BeautySpot) -> Unit,
    onCancelClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Chọn địa điểm",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )

            TextButton(onClick = onCancelClick) {
                Text("Hủy")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(SampleData.beautySpots) { spot ->
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
                    onClick = { onSpotSelected(spot) }
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(12.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        // Use the first image from the spot's imageResIds collection
                        val imageRes = if (spot.imageResIds.isNotEmpty()) {
                            spot.imageResIds.first()
                        } else {
                            getDefaultImageForCategory(spot.category)
                        }

                        Image(
                            painter = painterResource(id = imageRes),
                            contentDescription = spot.name,
                            modifier = Modifier
                                .size(60.dp)
                                .clip(RoundedCornerShape(8.dp)),
                            contentScale = ContentScale.Crop
                        )

                        Spacer(modifier = Modifier.width(16.dp))

                        Column {
                            Text(
                                text = spot.name,
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.Bold
                            )

                            Spacer(modifier = Modifier.height(4.dp))

                            Text(
                                text = spot.address,
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)
                            )

                            Spacer(modifier = Modifier.height(4.dp))

                            // Add rating display
                            Row(
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Star,
                                    contentDescription = null,
                                    tint = Color(0xFFFFC107),
                                    modifier = Modifier.size(16.dp)
                                )
                                Text(
                                    text = spot.rating.toString(),
                                    style = MaterialTheme.typography.bodySmall,
                                    modifier = Modifier.padding(start = 4.dp)
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun ComparisonResult(spot1: BeautySpot, spot2: BeautySpot) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // General comparison
        item {
            ComparisonSection(title = "Thông tin chung") {
                ComparisonRow(
                    label = "Đánh giá",
                    value1 = "${spot1.rating} ★",
                    value2 = "${spot2.rating} ★"
                )

                ComparisonRow(
                    label = "Địa chỉ",
                    value1 = spot1.address,
                    value2 = spot2.address
                )

                val reviewCount1 = spot1.reviews.size
                val reviewCount2 = spot2.reviews.size
                ComparisonRow(
                    label = "Số lượng đánh giá",
                    value1 = "$reviewCount1",
                    value2 = "$reviewCount2"
                )

                ComparisonRow(
                    label = "Loại hình",
                    value1 = spot1.category,
                    value2 = spot2.category
                )
            }
        }

        // Service comparison
        item {
            ComparisonSection(title = "So sánh dịch vụ") {
                // Find comparable services
                val allServiceTypes = mutableSetOf<String>()
                spot1.services.forEach { allServiceTypes.add(it.name) }
                spot2.services.forEach { allServiceTypes.add(it.name) }

                allServiceTypes.forEach { serviceName ->
                    val service1 = spot1.services.find { it.name == serviceName }
                    val service2 = spot2.services.find { it.name == serviceName }

                    if (service1 != null || service2 != null) {
                        ComparisonRow(
                            label = serviceName,
                            value1 = service1?.let { formatPrice(it.price) } ?: "Không có",
                            value2 = service2?.let { formatPrice(it.price) } ?: "Không có",
                            highlight = service1 != null && service2 != null &&
                                    service1.price != service2.price,
                            highlightLower = true
                        )
                    }
                }
            }
        }

        // Price range comparison
        item {
            ComparisonSection(title = "Phạm vi giá") {
                val minPrice1 = spot1.services.minByOrNull { it.price }?.price ?: 0
                val maxPrice1 = spot1.services.maxByOrNull { it.price }?.price ?: 0
                val minPrice2 = spot2.services.minByOrNull { it.price }?.price ?: 0
                val maxPrice2 = spot2.services.maxByOrNull { it.price }?.price ?: 0

                ComparisonRow(
                    label = "Giá thấp nhất",
                    value1 = formatPrice(minPrice1),
                    value2 = formatPrice(minPrice2),
                    highlight = minPrice1 != minPrice2,
                    highlightLower = true
                )

                ComparisonRow(
                    label = "Giá cao nhất",
                    value1 = formatPrice(maxPrice1),
                    value2 = formatPrice(maxPrice2),
                    highlight = maxPrice1 != maxPrice2,
                    highlightLower = true
                )

                val avgPrice1 = if (spot1.services.isNotEmpty()) {
                    spot1.services.map { it.price }.average().toInt()
                } else 0

                val avgPrice2 = if (spot2.services.isNotEmpty()) {
                    spot2.services.map { it.price }.average().toInt()
                } else 0

                ComparisonRow(
                    label = "Giá trung bình",
                    value1 = formatPrice(avgPrice1),
                    value2 = formatPrice(avgPrice2),
                    highlight = avgPrice1 != avgPrice2,
                    highlightLower = true
                )
            }
        }

        // Duration comparison
        item {
            ComparisonSection(title = "Thời gian dịch vụ") {
                val allServiceNames = (spot1.services + spot2.services).map { it.name }.toSet()

                allServiceNames.forEach { serviceName ->
                    val service1 = spot1.services.find { it.name == serviceName }
                    val service2 = spot2.services.find { it.name == serviceName }

                    if (service1 != null || service2 != null) {
                        ComparisonRow(
                            label = serviceName,
                            value1 = service1?.durationMinutes?.let { "$it phút" } ?: "Không có",
                            value2 = service2?.durationMinutes?.let { "$it phút" } ?: "Không có",
                            highlight = service1 != null && service2 != null &&
                                    service1.durationMinutes != service2.durationMinutes,
                            highlightLower = false // Không so sánh thời gian ít hơn là tốt hơn
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun ComparisonSection(
    title: String,
    content: @Composable ColumnScope.() -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 12.dp),
                color = MaterialTheme.colorScheme.primary
            )
            content()
        }
    }
}

@Composable
fun ComparisonRow(
    label: String,
    value1: String,
    value2: String,
    highlight: Boolean = false,
    highlightLower: Boolean = false
) {
    val (highlightColor1, highlightColor2) = when {
        highlight && highlightLower -> {
            val v1 = safeParsePrice(value1)
            val v2 = safeParsePrice(value2)
            when {
                v1 < v2 -> Color(0xFF4CAF50) to Color(0xFFE57373) // Green and Light Red
                v1 > v2 -> Color(0xFFE57373) to Color(0xFF4CAF50) // Light Red and Green
                else -> Color.Unspecified to Color.Unspecified
            }
        }
        highlight -> Color(0xFF2196F3) to Color(0xFF2196F3) // Blue for both if just highlighting
        else -> Color.Unspecified to Color.Unspecified
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = label,
            modifier = Modifier.weight(1.2f),
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = FontWeight.Medium
        )
        Text(
            text = value1,
            modifier = Modifier.weight(0.9f),
            style = MaterialTheme.typography.bodyMedium,
            textAlign = TextAlign.Center,
            color = highlightColor1
        )
        Text(
            text = value2,
            modifier = Modifier.weight(0.9f),
            style = MaterialTheme.typography.bodyMedium,
            textAlign = TextAlign.Center,
            color = highlightColor2
        )
    }

    Divider(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        thickness = 0.5.dp,
        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.1f)
    )
}
