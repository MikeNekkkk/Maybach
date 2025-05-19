package maihyenhi1.com.beauty

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CompareArrows
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import maihyenhi1.com.beauty.data.SampleData
import maihyenhi1.com.beauty.data.topTenPromotions
import maihyenhi1.com.beauty.model.BeautySpot
import maihyenhi1.com.beauty.model.PromotionCardInfo

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    navController: NavController,
    onSpotClick: (Int) -> Unit = {},
    onCompareClick: () -> Unit = {},
    onCategoryClick: (String) -> Unit = {}
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("HPL Beauty", style = MaterialTheme.typography.titleLarge) },
                actions = {
                    IconButton(onClick = onCompareClick) {
                        Icon(imageVector = Icons.Default.CompareArrows, contentDescription = "So sánh")
                    }
                }
            )
        }
    ) { padding ->
        // We use a Box to ensure the LazyColumn gets the full screen space
        Box(modifier = Modifier
            .fillMaxSize()
            .padding(padding)
        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                item {
                    OutlinedTextField(
                        value = "",
                        onValueChange = {},
                        placeholder = { Text("Tìm kiếm dịch vụ") },
                        leadingIcon = { Icon(Icons.Default.Search, contentDescription = null) },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(56.dp)
                            .clickable {
                                // Khi nhấn vào thanh tìm kiếm, chuyển đến màn hình search với tất cả các category
                                navController.navigate("spa_search/Tất cả")
                            }
                    )
                }
                item { Banner() }
                item { CategoryIcons(onCategoryClick) }

                // Title for "DOANH NGHIỆP MỚI THAM GIA" in its own row
                item {
                    SectionTitleRow(title = "DOANH NGHIỆP MỚI THAM GIA", textColor = Color(0xFFFF6F00))
                }

                item { HorizontalPromotionList(navController) }

                item { HorizontalPromotionListTopTenPromotionCard(navController) }

                item { WhyChooseBeautyX() }

                // Add extra padding at the bottom for better scrolling experience
                item {
                    Spacer(modifier = Modifier.height(24.dp))
                }
            }
        }
    }
}

@Composable
fun SectionTitleRow(title: String, textColor: Color = Color.Black) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        Text(
            text = title,
            fontWeight = FontWeight.Bold,
            color = textColor,
            style = MaterialTheme.typography.titleMedium
        )
    }
}


@Composable
fun HorizontalPromotionListTopTenPromotionCard(navController: NavController) {
    LazyRow(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
        items(topTenPromotions.topTenPromotions) { promo ->
            Column(modifier = Modifier.padding(vertical = 8.dp)) {
                // Section title for each promotion
                Text(
                    text = promo.headerTitle,
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.padding(vertical = 8.dp)
                )

                // The promotion card
                TopTenPromotionCard(promo, navController)
            }
        }
    }
}

@Composable
fun TopTenPromotionCard(promo: PromotionCardInfo, navController: NavController) {
    // Card với khoảng cách mép màn hình 5dp
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 5.dp, vertical = 5.dp) // 5dp cách mép và giữa các cards
            .height(180.dp)
            .clickable { navController.navigate(promo.route) },
        shape = RoundedCornerShape(10.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Box {
            // Ảnh nền chiếm toàn bộ card
            Image(
                painter = painterResource(id = promo.imageRes),
                contentDescription = promo.title,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )

            // Logo và tiêu đề
            Row(
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = R.drawable.spa_icon),
                    contentDescription = null,
                    modifier = Modifier
                        .size(40.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .background(Color.White)
                        .padding(4.dp)
                )

                Spacer(modifier = Modifier.width(8.dp))

                Text(
                    text = promo.title,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            }
        }
    }
}


@Composable
fun ServicePreviewItem(
    imageResId: Int,
    title: String,
    price: String,
    location: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {}
) {
    Column(
        modifier = modifier
            .clickable(onClick = onClick)
    ) {
        Image(
            painter = painterResource(id = imageResId),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(120.dp)
                .clip(RoundedCornerShape(8.dp)),
            contentScale = ContentScale.Crop
        )

        Text(
            text = title,
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = FontWeight.Bold,
            maxLines = 1
        )

        Text(
            text = price,
            style = MaterialTheme.typography.bodyMedium,
            color = Color(0xFF6200EE)
        )

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(id = R.drawable.location_icon),
                contentDescription = "Location",
                modifier = Modifier.size(16.dp),
                tint = Color.Red
            )

            Text(
                text = location,
                style = MaterialTheme.typography.bodySmall,
                color = Color.Gray
            )
        }
    }
}

@Composable
fun CategoryIcons(onCategoryClick: (String) -> Unit) {
    val categories = listOf("Spa", "Nail", "Hair", "Clinic")
    val categoryImages = mapOf(
        "Spa" to R.drawable.spa_icon,
        "Nail" to R.drawable.nail_icon,
        "Hair" to R.drawable.salon_icon,
        "Clinic" to R.drawable.dental_icon
    )

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        categories.forEach { category ->
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.clickable {
                    onCategoryClick(category)
                }
            ) {
                Box(
                    modifier = Modifier
                        .size(56.dp)
                        .clip(CircleShape)
                        .background(Color(0xFFF0F0F0)),
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        painter = painterResource(id = categoryImages[category] ?: R.drawable.spa_icon),
                        contentDescription = category,
                        modifier = Modifier.size(32.dp),
                        contentScale = ContentScale.Crop
                    )
                }
                Spacer(modifier = Modifier.height(4.dp))
                Text(category, style = MaterialTheme.typography.bodySmall)
            }
        }
    }
}

@Composable
fun Banner() {
    Image(
        painter = painterResource(id = R.drawable.spa_1),
        contentDescription = null,
        modifier = Modifier
            .fillMaxWidth()
            .height(160.dp)
            .clip(RoundedCornerShape(12.dp)),
        contentScale = ContentScale.Crop
    )
}

@Composable
fun HorizontalPromotionList(navController: NavController) {
    LazyRow(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
        items(SampleData.beautySpots) { spot ->
            PromotionCard(spot, navController)
        }
    }
}

@Composable
fun PromotionCard(spot: BeautySpot, navController: NavController) {
    Card(
        modifier = Modifier
            .width(220.dp)
            .clickable { navController.navigate("detail/${spot.id}") },
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column {
            Image(
                painter = painterResource(
                    id = if (spot.imageResIds.isNotEmpty()) spot.imageResIds.first() else R.drawable.spa_1
                ),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp),
                contentScale = ContentScale.Crop
            )
            Column(modifier = Modifier.padding(8.dp)) {
                Text(spot.name, fontWeight = FontWeight.Bold, maxLines = 1)
                Spacer(modifier = Modifier.height(4.dp))
            }
        }
    }
}

@Composable
fun WhyChooseBeautyX() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp)
    ) {
        Text(
            "Vì sao nên chọn HPL Beauty ?",
            style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
            modifier = Modifier.padding(bottom = 12.dp)
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            // First feature box
            BeautyXFeatureBox(
                iconResId = R.drawable.location_icon,
                title = "Tìm kiếm nơi làm đẹp",
                description = "Khám phá và tìm kiếm Spa, Salon, Thẩm mỹ, Phòng khám gần bạn",
                modifier = Modifier.weight(1f)
            )

            Spacer(modifier = Modifier.width(12.dp))

            // Second feature box
            BeautyXFeatureBox(
                iconResId = R.drawable.thumbs_up_icon,
                title = "+10,387 địa điểm uy tín",
                description = "Mua đa dạng dịch vụ làm đẹp uy tín, an toàn với các đánh giá xác thực",
                modifier = Modifier.weight(1f)
            )
        }
    }
}

@Composable
fun BeautyXFeatureBox(
    iconResId: Int,
    title: String,
    description: String,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(2.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = iconResId),
                contentDescription = null,
                modifier = Modifier
                    .size(48.dp)
                    .padding(bottom = 8.dp)
            )

            Text(
                text = title,
                style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold),
                textAlign = TextAlign.Center
            )

            Text(
                text = description,
                style = MaterialTheme.typography.bodySmall,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(top = 4.dp)
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun HomeScreenPreview() {
    val navController = rememberNavController()
    MainScreen(navController = navController)
}