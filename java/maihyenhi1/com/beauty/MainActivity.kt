package maihyenhi1.com.beauty

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import maihyenhi1.com.beauty.ui.screens.DetailTop10Hair
import maihyenhi1.com.beauty.ui.screens.detailtop10nail
import maihyenhi1.com.beauty.ui.screens.detailtop10spa

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                val navController = rememberNavController()

                // Single NavHost to manage all navigation
                NavHost(navController = navController, startDestination = "main") {
                    // Màn chính - MainScreen
                    composable("main") {
                        MainScreen(
                            navController = navController,
                            onSpotClick = { spotId ->
                                navController.navigate("detail/$spotId")
                            },
                            onCompareClick = {
                                navController.navigate("compare")
                            },
                            onCategoryClick = { category ->
                                // Chuyển sang spa_search với category được chọn
                                navController.navigate("spa_search/$category")
                            }
                        )
                    }

                    // Top 10 promotion detail screens
                    composable("detail_top10hair") {
                        DetailTop10Hair(navController = navController)
                    }
                    composable("detail_top10spa") {
                        detailtop10spa(navController = navController)
                    }
                    composable("detail_top10nail") {
                        detailtop10nail(navController = navController)
                    }


                    // Màn Detail hiển thị chi tiết BeautySpot
                    composable(
                        route = "detail/{spotId}",
                        arguments = listOf(navArgument("spotId") { type = NavType.IntType })
                    ) { backStackEntry ->
                        val spotId = backStackEntry.arguments?.getInt("spotId") ?: 0
                        DetailScreen(
                            spotId = spotId,
                            onBackClick = { navController.popBackStack() }
                        )
                    }

                    // Màn SpaSearch với filter category và placeholder tìm kiếm
                    composable(
                        route = "spa_search/{categoryFilter}",
                        arguments = listOf(navArgument("categoryFilter") { type = NavType.StringType })
                    ) { backStackEntry ->
                        val categoryFilter = backStackEntry.arguments?.getString("categoryFilter") ?: "Tất cả"
                        SpaSearchScreen(
                            navController = navController,
                            categoryFilter = categoryFilter,
                            searchPlaceholder = "Tìm kiếm $categoryFilter",
                            onBackClick = { navController.popBackStack() },
                            onSpotClick = { spotId -> navController.navigate("detail/$spotId") }
                        )
                    }

                    // Màn Compare (nếu có)
                    composable("compare") {
                        CompareScreen(onBackClick = { navController.popBackStack() })
                    }
                }
            }
        }
    }
}