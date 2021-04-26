package com.joshua.spacexlaunch.ui.splash

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.joshua.spacexlaunch.MainScreen
import com.joshua.spacexlaunch.R
import com.joshua.spacexlaunch.ui.theme.black500
import timber.log.Timber

@ExperimentalFoundationApi
@Preview("Splash", widthDp = 360, heightDp = 640)
@Composable
fun SplashScreen(){

    val splashViewModel: SplashViewModel = viewModel()
    val splashState by splashViewModel.state.observeAsState()
    Timber.i("SplashState=$splashState")
    splashState?.let {state->
        when(state){
            SplashState.Loading -> {
                SplashUI()
                splashViewModel.switchHome()
            }
            SplashState.Loaded -> {
                MainScreen()
            }
            is SplashState.Error -> {
                Timber.i("SplashState Error=${state.exception}")
            }
        }
    }


}

@Composable
fun SplashUI(){
    val modifier = Modifier.fillMaxSize()

    Image(
        modifier = modifier,
        painter = painterResource(R.drawable.splash_rocket),
        contentDescription = "Splash",
        contentScale= ContentScale.Crop,
    )

    Box(
        modifier = modifier.background(black500)
    )

    Row( modifier = modifier,
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.Top){

        Spacer(Modifier.weight(1f))

        Column(
            modifier = modifier.weight(5f),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Bottom
        ) {
            Text(
                text = stringResource(R.string.app_name),
                fontSize = 36.sp,
                color = Color.White,
                )

            Image(
                modifier = Modifier.padding(top = 300.dp),
                painter = painterResource(R.drawable.ic_rocket_working),
                contentDescription = "Working",
            )
        }

        Spacer(Modifier.weight(1f))
    }
}