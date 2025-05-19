package maihyenhi1.com.beauty.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val LightColors = lightColorScheme(
    primary = Color(0xFFFF6B98),
    onPrimary = Color.White,
    secondary = Color(0xFFFF8FB8),
    tertiary = Color(0xFFFF5C8D),
    background = Color(0xFFFDF7F9),
    surface = Color.White
)

private val DarkColors = darkColorScheme(
    primary = Color(0xFFFF6B98),
    onPrimary = Color.White,
    secondary = Color(0xFFFF8FB8),
    tertiary = Color(0xFFFF5C8D),
    background = Color(0xFF1F1F1F),
    surface = Color(0xFF252525)
)

@Composable
fun BeautySpotsTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColors else LightColors

    MaterialTheme(
        colorScheme = colorScheme,
        content = content
    )
}
