package maihyenhi1.com.beauty

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavController
import maihyenhi1.com.beauty.data.SampleData
import maihyenhi1.com.beauty.model.BeautySpot
import maihyenhi1.com.beauty.model.Service


@Composable
fun SpaSearchScreen(
    navController: NavController,
    categoryFilter: String,
    searchPlaceholder: String = "Tìm kiếm",
    onBackClick: () -> Unit,
    onSpotClick: (Int) -> Unit = {}
) {
    var searchQuery by remember { mutableStateOf("") }
    var selectedTab by remember { mutableStateOf(0) }
    var showCategoryMenu by remember { mutableStateOf(false) }

    // Lấy tất cả các danh mục duy nhất từ dữ liệu mẫu
    val availableCategories = remember {
        listOf("Tất cả") + SampleData.beautySpots.map { it.category }.distinct().sorted()
    }

    // Hàm kiểm tra xem spot có hợp lệ với tìm kiếm và category
    fun isSpotMatch(spot: BeautySpot): Boolean {
        val categoryOk = categoryFilter.equals("Tất cả", true) ||
                spot.category.equals(categoryFilter, ignoreCase = true)
        val searchOk = searchQuery.isBlank() ||
                spot.name.contains(searchQuery, ignoreCase = true) ||
                spot.description.contains(searchQuery, ignoreCase = true) ||
                spot.services.any { it.name.contains(searchQuery, ignoreCase = true) }
        return categoryOk && searchOk
    }

    Column(modifier = Modifier.fillMaxSize()) {
        // Header tìm kiếm + back button
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = onBackClick) {
                Icon(Icons.Default.ArrowBack, contentDescription = "Back")
            }
            OutlinedTextField(
                value = searchQuery,
                onValueChange = { searchQuery = it },
                placeholder = { Text(searchPlaceholder) },
                leadingIcon = {
                    Icon(Icons.Default.Search, contentDescription = null, tint = Color(0xFF9370DB))
                },
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 8.dp)
                    .height(56.dp),
                singleLine = true,
                shape = RoundedCornerShape(50)
            )
        }

        // Tab chọn Dịch vụ / Doanh nghiệp
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp),
            shape = RoundedCornerShape(50),
            color = Color(0xFFF0F0F0)
        ) {
            Row(modifier = Modifier.fillMaxWidth().height(48.dp)) {
                SpaTabButton("Dịch vụ", selectedTab == 0, onClick = { selectedTab = 0 }, modifier = Modifier.weight(1f))
                SpaTabButton("Doanh nghiệp", selectedTab == 1, onClick = { selectedTab = 1 }, modifier = Modifier.weight(1f))
            }
        }

        // Chọn vị trí và Bộ lọc (placeholder)
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 12.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.clickable { /* TODO: mở chọn vị trí */ }) {
                Text("Chọn vị trí", fontSize = 16.sp, fontWeight = FontWeight.Medium)
                Spacer(modifier = Modifier.width(4.dp))
                Icon(Icons.Default.LocationOn, contentDescription = "Location", tint = Color.Gray, modifier = Modifier.size(16.dp))
            }

            // Hiển thị category đang được lọc với khả năng click để mở menu
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.clickable { showCategoryMenu = true }
            ) {
                Text(
                    text = "Danh mục: $categoryFilter",
                    fontWeight = FontWeight.Medium,
                    fontSize = 16.sp,
                    color = Color(0xFF9370DB)
                )
                Spacer(modifier = Modifier.width(4.dp))
                Icon(
                    Icons.Default.ArrowDropDown,
                    contentDescription = "Chọn danh mục",
                    tint = Color(0xFF9370DB),
                    modifier = Modifier.size(24.dp)
                )
            }
        }

        Divider(color = Color(0xFFEEEEEE), thickness = 1.dp)

        // Nội dung theo tab chọn, lọc theo hàm isSpotMatch
        when (selectedTab) {
            0 -> {
                val filteredServicesSpots = SampleData.beautySpots.filter { isSpotMatch(it) }
                if (filteredServicesSpots.isEmpty()) {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        Text("Không tìm thấy dịch vụ nào", style = MaterialTheme.typography.bodyLarge)
                    }
                } else {
                    LazyColumn(modifier = Modifier.fillMaxSize()) {
                        items(filteredServicesSpots) { spot ->
                            ServiceSpotItem(spot, onSpotClick)
                        }
                    }
                }
            }
            1 -> {
                val filteredBusinessSpots = SampleData.beautySpots.filter { isSpotMatch(it) }
                if (filteredBusinessSpots.isEmpty()) {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        Text("Không tìm thấy doanh nghiệp nào", style = MaterialTheme.typography.bodyLarge)
                    }
                } else {
                    LazyColumn(modifier = Modifier.fillMaxSize()) {
                        items(filteredBusinessSpots) { spot ->
                            BusinessRow(spot, onSpotClick)
                            Divider()
                        }
                    }
                }
            }
        }
    }

    // Hiển thị dialog menu danh mục khi showCategoryMenu = true
    if (showCategoryMenu) {
        CategoryMenuDialog(
            categories = availableCategories,
            selectedCategory = categoryFilter,
            onCategorySelected = { category ->
                // Đóng dialog
                showCategoryMenu = false
                // Điều hướng đến màn hình tìm kiếm với danh mục mới
                if (category != categoryFilter) {
                    navController.navigate("spa_search/$category") {
                        // Xóa màn hình hiện tại khỏi back stack để tránh tích lũy nhiều màn hình tìm kiếm
                        popUpTo("spa_search/$categoryFilter") { inclusive = true }
                    }
                }
            },
            onDismiss = { showCategoryMenu = false }
        )
    }
}

@Composable
fun CategoryMenuDialog(
    categories: List<String>,
    selectedCategory: String,
    onCategorySelected: (String) -> Unit,
    onDismiss: () -> Unit
) {
    Dialog(onDismissRequest = onDismiss) {
        Surface(
            shape = RoundedCornerShape(16.dp),
            color = MaterialTheme.colorScheme.surface,
            tonalElevation = 4.dp,
            shadowElevation = 8.dp,
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(modifier = Modifier.padding(vertical = 8.dp)) {
                Text(
                    "Chọn danh mục",
                    modifier = Modifier.padding(16.dp),
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold
                )

                Divider()

                LazyColumn(
                    modifier = Modifier.fillMaxWidth().heightIn(max = 300.dp)
                ) {
                    items(categories) { category ->
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable { onCategorySelected(category) }
                                .padding(horizontal = 16.dp, vertical = 12.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = category,
                                style = MaterialTheme.typography.bodyLarge,
                                fontWeight = if (category == selectedCategory) FontWeight.Bold else FontWeight.Normal,
                                color = if (category == selectedCategory) Color(0xFF9370DB) else Color.Unspecified,
                                modifier = Modifier.weight(1f)
                            )

                            if (category == selectedCategory) {
                                Icon(
                                    Icons.Default.Check,
                                    contentDescription = "Đã chọn",
                                    tint = Color(0xFF9370DB)
                                )
                            }
                        }
                    }
                }

                Divider()

                TextButton(
                    onClick = onDismiss,
                    modifier = Modifier
                        .align(Alignment.End)
                        .padding(end = 16.dp, bottom = 8.dp)
                ) {
                    Text("HỦY")
                }
            }
        }
    }
}

@Composable
fun SpaTabButton(
    text: String,
    isSelected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        shape = RoundedCornerShape(50),
        color = if (isSelected) Color(0xFF9370DB) else Color.Transparent,
        modifier = modifier
            .padding(4.dp)
            .clickable(onClick = onClick)
    ) {
        Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
            Text(
                text = text,
                color = if (isSelected) Color.White else Color.Gray,
                fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal
            )
        }
    }
}

@Composable
fun ServiceSpotItem(spot: BeautySpot, onSpotClick: (Int) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onSpotClick(spot.id) }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.Top
        ) {
            Image(
                painter = painterResource(
                    id = if (spot.imageResIds.isNotEmpty()) spot.imageResIds.first() else R.drawable.spa_1
                ),
                contentDescription = null,
                modifier = Modifier
                    .size(80.dp)
                    .clip(RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.width(12.dp))
            Column {
                Text(spot.name, fontWeight = FontWeight.Bold, fontSize = 18.sp)
                Text(
                    spot.address,
                    color = Color.Gray,
                    fontSize = 14.sp,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
                // Hiển thị category của spot
                Text(
                    "Danh mục: ${spot.category}",
                    color = Color(0xFF9370DB),
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium
                )
            }
        }

        Divider(color = Color(0xFFEEEEEE), thickness = 1.dp)

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp)
        ) {
            Text("Dịch vụ", fontWeight = FontWeight.Bold, fontSize = 16.sp)
        }

        spot.services.take(3).forEachIndexed { index, service ->
            ServiceRow(service, spot, index)
        }

        Divider(color = Color(0xFFEEEEEE), thickness = 8.dp)
    }
}

@Composable
fun ServiceRow(service: Service, spot: BeautySpot, index: Int) {
    val imageIndex = if (spot.imageResIds.isNotEmpty()) index % spot.imageResIds.size else 0
    val imageResId = if (spot.imageResIds.isNotEmpty()) spot.imageResIds[imageIndex] else R.drawable.spa_1

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = imageResId),
            contentDescription = null,
            modifier = Modifier
                .size(48.dp)
                .clip(RoundedCornerShape(4.dp)),
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.width(12.dp))

        Column(modifier = Modifier.weight(1f)) {
            Text(service.name, fontWeight = FontWeight.Bold, fontSize = 16.sp)
            Text(
                text = formatPrice(service.price),
                color = Color(0xFF9370DB),
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp
            )
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(Icons.Default.LocationOn, contentDescription = null, tint = Color.Red, modifier = Modifier.size(16.dp))
                Spacer(modifier = Modifier.width(4.dp))
                Text(spot.address.split(",")[0], fontSize = 14.sp, color = Color.Gray)
            }
        }

        IconButton(onClick = { /* Thêm yêu thích */ }) {
            Icon(Icons.Default.FavoriteBorder, contentDescription = "Favorite", tint = Color.Red)
        }
    }
}

@Composable
fun BusinessRow(spot: BeautySpot, onSpotClick: (Int) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .clickable { onSpotClick(spot.id) },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(
                id = if (spot.imageResIds.isNotEmpty()) spot.imageResIds.first() else R.drawable.spa_1
            ),
            contentDescription = null,
            modifier = Modifier
                .size(60.dp)
                .clip(CircleShape),
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.width(12.dp))

        Column(modifier = Modifier.weight(1f)) {
            Text(spot.name.uppercase(), fontWeight = FontWeight.Bold, fontSize = 16.sp)
            Text(
                spot.address,
                color = Color.Gray,
                fontSize = 14.sp,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
            // Hiển thị category
            Text(
                "Danh mục: ${spot.category}",
                color = Color(0xFF9370DB),
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium
            )
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(Icons.Default.Star, contentDescription = null, tint = Color(0xFFFFC107), modifier = Modifier.size(16.dp))
                Text("${spot.rating}", fontSize = 14.sp, modifier = Modifier.padding(start = 4.dp, end = 8.dp))
                Icon(Icons.Default.Favorite, contentDescription = null, tint = Color.Red, modifier = Modifier.size(16.dp))
                Text("${spot.reviews.size}", fontSize = 14.sp, modifier = Modifier.padding(start = 4.dp))
            }
        }

        IconButton(onClick = { /* Thêm yêu thích */ }) {
            Icon(Icons.Default.FavoriteBorder, contentDescription = "Add to favorites", tint = Color.Red)
        }
    }
}