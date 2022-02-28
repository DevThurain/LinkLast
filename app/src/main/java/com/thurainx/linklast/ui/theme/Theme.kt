package com.thurainx.linklast.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.thurainx.linklast.R

private val Jost = FontFamily(
    Font(R.font.jost_italic, style = androidx.compose.ui.text.font.FontStyle.Italic),
    Font(R.font.jost_normal, FontWeight.Bold)
)

val text_header = TextStyle(
    fontFamily = Jost,
    fontSize = 35.sp,
    fontWeight = FontWeight.Bold
)

val text_regular = TextStyle(
    fontFamily = Jost,
    fontSize = 14.sp
)

val text_small = TextStyle(
    fontFamily = Jost,
    fontSize = 12.sp
)

private val DarkColorPalette = darkColors(
    primary = primary_purple,
    primaryVariant = Purple700,
    secondary = Teal200
)

private val LightColorPalette = lightColors(
    primary = primary_purple,
    primaryVariant = Purple700,
    secondary = Teal200

    /* Other default colors to override
    background = Color.White,
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black,
    */
)

@Composable
fun LinkLastTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}