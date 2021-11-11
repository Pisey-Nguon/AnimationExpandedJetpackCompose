package com.example.jetpackcomposeexample.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import com.google.accompanist.systemuicontroller.rememberSystemUiController

private val DarkColorPalette = darkColors(
    primary = White,
    primaryVariant = WhiteGray,
    secondary = White,
    onBackground = BlueLight,
    background = DarkBlue
)

private val LightColorPalette = lightColors(
    primary = Black,
    primaryVariant = BlackGray,
    secondary = Black,
    onBackground = White,
    background = WhiteGray

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
fun TitleActionBar(title: String){
    Text(text = title,color = MaterialTheme.colors.primary)
}

@Composable
fun JetpackComposeExampleTheme(
    titleActionBar:String = "",
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable() () -> Unit
) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }
    val colorAppBar = if (darkTheme) {
        BlueLight
    } else {
        White
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = {
            // Update the system bars to be translucent
            val systemUiController = rememberSystemUiController()
            val useDarkIcons = MaterialTheme.colors.isLight
            SideEffect {
                systemUiController.setSystemBarsColor(Color.Transparent, darkIcons = useDarkIcons)
            }
            Scaffold(
                topBar = {
                    TopAppBar(title = { TitleActionBar(title = titleActionBar) },backgroundColor = colorAppBar)
                },
            ){
                Surface(color = MaterialTheme.colors.background) {
                    content()
                }
            }
        }
    )
}