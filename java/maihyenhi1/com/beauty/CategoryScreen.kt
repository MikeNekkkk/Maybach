package maihyenhi1.com.beauty

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import maihyenhi1.com.beauty.data.SampleData

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategoryScreen(
    categoryName: String,
    onBackClick: () -> Unit,
    onSpotClick: (Int) -> Unit
) {
    val filteredSpots = SampleData.beautySpots.filter {
        it.name.contains(categoryName, ignoreCase = true) ||
                it.description.contains(categoryName, ignoreCase = true)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Danh mục: $categoryName") },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(Icons.Default.ArrowBack, contentDescription = null)
                    }
                }
            )
        }
    ) { padding ->
        LazyColumn(
            contentPadding = padding,
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(filteredSpots) { spot ->
                BeautySpotCard(spot = spot, onClick = { onSpotClick(spot.id) })
            }

            if (filteredSpots.isEmpty()) {
                item {
                    Text("Không có địa điểm nào trong danh mục \"$categoryName\".")
                }
            }
        }
    }
}
@Composable
fun BeautySpotCard(spot: maihyenhi1.com.beauty.model.BeautySpot, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(spot.name, style = MaterialTheme.typography.titleLarge)
            Text(spot.address, style = MaterialTheme.typography.bodyMedium)
            Text("Đánh giá: ${spot.rating} ★", style = MaterialTheme.typography.bodySmall)
        }
    }
}

