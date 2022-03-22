package com.example.viewtocomposetheming

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import com.example.viewtocomposetheming.ui.theme.*
import com.google.android.material.composethemeadapter.MdcTheme


private val DarkColorPalette = darkColors(
//    primary = Color(R.color.blue1),
//    primaryVariant = Color(R.color.blue5),
//    secondary = Color(R.color.red200),
//    secondaryVariant = Color(R.color.red300)
    primary = green1,
    primaryVariant = Teal200,
    secondary = green2,
    secondaryVariant = Red300,
    error = Red200
)

private val LightColorPalette = lightColors(
//    primary = Color(R.color.blue2),
//    primaryVariant = Color(R.color.blue4),
//    secondary = Color(R.color.red300),
//    secondaryVariant = Color(R.color.red700)
    primary = green3,
    primaryVariant = Teal200,
    secondary = green4,
    secondaryVariant = Teal200,
    error = Red700
)

@Composable
fun Theme2(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Theme2Typography,
        shapes = Theme2Shapes,
        content = content
    )
}

object Theme2 {
    val colors: Colors
        @Composable
        get() = MaterialTheme.colors

    //    val typography: Typography
//        @Composable
//        get() = MaterialTheme.typography
//
    val shapes: Shapes
        @Composable
        get() = MaterialTheme.shapes
}


/**
 * MDCTHEME wrapper
 * per avere accesso ai colori, shapes ecc
 */
@Composable
fun MyMdcTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    MdcTheme(content = content)
}

object MyMdcTheme {
    val colors: Colors
        @Composable
        get() = MaterialTheme.colors
    val typography: Typography
        @Composable
        get() = MaterialTheme.typography
    val shapes: Shapes
        @Composable
        get() = MaterialTheme.shapes
}


//
//@Immutable
//data class CustomColors(
//    val content: Color,
//    val component: Color,
//    val background: List<Color>
//)
//
//@Immutable
//data class CustomTypography(
//    val body: TextStyle,
//    val title: TextStyle
//)
//
//@Immutable
//data class CustomElevation(
//    val default: Dp,
//    val pressed: Dp
//)
//
//val LocalCustomColors = staticCompositionLocalOf {
//    CustomColors(
//        content = Color.Unspecified,
//        component = Color.Unspecified,
//        background = emptyList()
//    )
//}
//val LocalCustomTypography = staticCompositionLocalOf {
//    CustomTypography(
//        body = TextStyle.Default,
//        title = TextStyle.Default
//    )
//}
//val LocalCustomElevation = staticCompositionLocalOf {
//    CustomElevation(
//        default = Dp.Unspecified,
//        pressed = Dp.Unspecified
//    )
//}
//
//
//@Composable
//fun CustomTheme(
//    content: @Composable () -> Unit
//) {
//    val customColors = CustomColors(
//        content = Color(0xFFDD0D3C),
//        component = Color(0xFFC20029),
//        background = listOf(Color.White, Color(0xFFF8BBD0))
//    )
//    val customTypography = CustomTypography(
//        body = TextStyle(fontSize = 16.sp),
//        title = TextStyle(fontSize = 32.sp)
//    )
//    val customElevation = CustomElevation(
//        default = 4.dp,
//        pressed = 8.dp
//    )
//    CompositionLocalProvider(
//        LocalCustomColors provides customColors,
//        LocalCustomTypography provides customTypography,
//        LocalCustomElevation provides customElevation,
//        content = content
//    )
//}
//
//// Use with eg. CustomTheme.elevation.small
//object CustomTheme {
//    val colors: CustomColors
//        @Composable
//        get() = LocalCustomColors.current
//    val typography: CustomTypography
//        @Composable
//        get() = LocalCustomTypography.current
//    val elevation: CustomElevation
//        @Composable
//        get() = LocalCustomElevation.current
//}