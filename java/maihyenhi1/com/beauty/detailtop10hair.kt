package maihyenhi1.com.beauty.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import maihyenhi1.com.beauty.R
import maihyenhi1.com.beauty.data.SampleData
import maihyenhi1.com.beauty.model.BeautySpot
import maihyenhi1.com.beauty.ui.theme.Purple80

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailTop10Hair(navController: NavController) {
    // Đúng category là "Hair" thay vì "Salon"
    val hairSalons = remember { SampleData.beautySpots.filter { it.category == "Hair" } }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "TOP 10 SALON TÓC UY TÍN",
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFFFFFFFF),
                    titleContentColor = Color(0xFF6D51A1)
                )
            )
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .background(Color(0xFFF8F8F8))
        ) {
            // Banner
            item {
                BannerSection(hairSalons)
            }

            // Introduction Text
            item {
                IntroductionSection()
            }

            // List Title
            item {
                ListTitleSection()
            }

            // List of Hair Salons
            items(hairSalons) { salon ->
                HairSalonItem(salon) {
                    navController.navigate("detail/${salon.id}")
                }
            }

            // Bottom Spacer
            item {
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}

@Composable
fun BannerSection(hairSalons: List<BeautySpot>) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(220.dp)
    ) {
        // Banner Image
        if (hairSalons.isNotEmpty() && hairSalons[0].imageResIds.isNotEmpty()) {
            Image(
                painter = painterResource(id = R.drawable.salon_top10),
                contentDescription = "Banner Image",
                contentScale = ContentScale.Fit, // hoặc Crop nếu bạn muốn ảnh luôn phủ toàn box
                modifier = Modifier
                    .padding(2.dp) // cách đều 2dp như yêu cầu
                    .fillMaxWidth()
                    .wrapContentHeight() // để giữ đúng tỷ lệ gốc ảnh
            )

        }

        // Gradient Overlay
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        colors = listOf(
                            Color.Black.copy(alpha = 0.0f),
                            Color.Black.copy(alpha = 0.6f)
                        )
                    )
                )
        )

        // Logo overlay
        Box(
            modifier = Modifier
                .padding(16.dp)
                .size(50.dp)
                .clip(CircleShape)
                .background(Color.White)
                .align(Alignment.TopStart)
        ) {
            Image(
                painter = painterResource(id = R.drawable.app),
                contentDescription = "Banner Image",
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
        }

        // Banner Title
        Column(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(16.dp)
        ) {
            Text(
                text = "SALON TÓC HÀNG ĐẦU",
                color = Color.White,
                fontWeight = FontWeight.ExtraBold,
                fontSize = 24.sp,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            // Call to action button
        }
    }
}

@Composable
fun IntroductionSection() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(
                text = "✨ TRẢI NGHIỆM LÀM TÓC CAO CẤP",
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                color = Color(0xFF6D51A1),
                modifier = Modifier.padding(bottom = 8.dp)
            )

            Text(
                text = "Chúng tôi đã chọn lọc 10 salon tóc uy tín nhất Đà Nẵng giúp bạn có mái tóc đẹp như mơ. Đội ngũ stylist chuyên nghiệp, không gian sang trọng và dịch vụ tận tâm sẽ mang đến trải nghiệm làm đẹp hoàn hảo cho bạn.",
                fontSize = 14.sp,
                lineHeight = 20.sp,
                color = Color(0xFF333333)
            )
        }
    }
}

@Composable
fun ListTitleSection() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Divider(
            modifier = Modifier
                .weight(1f)
                .height(1.dp),
            color = Color(0xFFE0E0E0)
        )

        Text(
            text = " DANH SÁCH SALON ",
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp,
            color = Color(0xFF6D51A1),
            modifier = Modifier.padding(horizontal = 8.dp)
        )

        Divider(
            modifier = Modifier
                .weight(1f)
                .height(1.dp),
            color = Color(0xFFE0E0E0)
        )
    }
}

@Composable
fun HairSalonItem(salon: BeautySpot, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .clickable(onClick = onClick),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        )
    ) {
        Column {
            // Salon Image with Rating Badge
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp)
            ) {
                if (salon.imageResIds.isNotEmpty()) {
                    Image(
                        painter = painterResource(id = salon.imageResIds[0]),
                        contentDescription = salon.name,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.fillMaxSize()
                    )
                }

                // Rating Badge
                Card(
                    modifier = Modifier
                        .padding(8.dp)
                        .align(Alignment.TopEnd),
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = Color.White.copy(alpha = 0.9f)
                    ),
                    border = BorderStroke(1.dp, Color(0xFFFFD700))
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Star,
                            contentDescription = "Rating",
                            tint = Color(0xFFFFD700),
                            modifier = Modifier.size(16.dp)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = salon.rating.toString(),
                            fontWeight = FontWeight.Bold,
                            fontSize = 14.sp,
                            color = Color(0xFF333333)
                        )
                    }
                }
            }

            // Salon Info
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                // Salon Name
                Text(
                    text = salon.name,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    color = Color(0xFF333333),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )

                Spacer(modifier = Modifier.height(4.dp))

                // Service Preview
                if (salon.services.isNotEmpty()) {
                    Text(
                        text = "Dịch vụ từ ${salon.services.minByOrNull { it.price }?.price ?: 0}k - ${salon.services.maxByOrNull { it.price }?.price ?: 0}k",
                        color = Color(0xFF6D51A1),
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 16.sp
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))

                // Location
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.LocationOn,
                        contentDescription = "Location",
                        tint = Color(0xFFFF4444),
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = salon.address.split(",").firstOrNull() ?: salon.address,
                        color = Color.Gray,
                        fontSize = 14.sp,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier.weight(1f)
                    )
                }

                Spacer(modifier = Modifier.height(12.dp))

                // Action Button
                Button(
                    onClick = onClick,
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(8.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF6D51A1)
                    )
                ) {
                    Text(
                        text = "XEM CHI TIẾT",
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp,
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }
}