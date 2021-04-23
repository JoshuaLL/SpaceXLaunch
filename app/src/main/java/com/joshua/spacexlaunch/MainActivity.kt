package com.joshua.spacexlaunch

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.joshua.spacexlaunch.ui.Navigation
import com.joshua.spacexlaunch.ui.theme.AppTheme
import com.joshua.spacexlaunch.ui.theme.black500

class MainActivity : AppCompatActivity() {

    @ExperimentalFoundationApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme{
                MainScreen()
            }
        }


    }
}

@Preview
@Composable
fun SplashScreen(){
    val modifier = Modifier.fillMaxSize()

    Image(
        modifier = modifier,
        painter = painterResource(R.drawable.splash_rocket),
        contentDescription = "Splash",
        contentScale= ContentScale.Crop,
    )

    Image(
        modifier = modifier,
        painter = painterResource(R.color.black_500),
        contentDescription = "Splash",
        contentScale= ContentScale.Crop,
    )

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = stringResource(R.string.app_name),
            fontSize = 36.sp,
            color = Color.White,
            )
    }
}

@ExperimentalFoundationApi
@Preview("Light Theme", widthDp = 360, heightDp = 640)
@Composable
fun MainScreen(){
    Spacer(Modifier.sizeIn(8.dp))
    Navigation()
}

