package com.joshua.spacexlaunch.ui.about

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.joshua.spacexlaunch.R
import com.joshua.spacexlaunch.ui.UrlProvider

@ExperimentalFoundationApi
@Composable
fun GetAboutPage(
    navController: NavController,
    setTitle: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    val context = LocalContext.current
    AboutPageComposable(
        modifier= modifier,
        onClick = {
            openWebUrl(context, it)
        }
    )
}

@Preview(
    name = "In a group: Ship 1",
    group = "AboutPage",
    showBackground = true
)
@Composable
private fun AboutPageComposable(
    modifier: Modifier = Modifier,
    @PreviewParameter(UrlProvider::class)
    onClick: (url:String) -> Unit
){

    Column(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(
                horizontal = 20.dp,
                vertical = 5.dp
            ),
        horizontalAlignment = Alignment.Start
    ) {

        Row(
            modifier= modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.Top
        ){
            Image(
                modifier = Modifier.height(100.dp),
                painter = painterResource(R.drawable.ic_android_green),
                contentDescription = "",
            )
        }

        Text(
            text = stringResource(R.string.attribution_space_api),
            fontSize = 16.sp
        )
        val spaceXUrl = stringResource(R.string.space_api_url)

        Text(
            modifier= modifier.clickable (
                onClick = {
                    onClick(spaceXUrl)
                }
            ),
            text = spaceXUrl,
            fontSize = 14.sp,
            color = Color.Blue
        )

        Text(
            text = stringResource(R.string.lottie_attribution_dongdona),
            fontSize = 16.sp
        )
        val lottieUrl = stringResource(R.string.lottie_files_url_dongdona)

        Text(
            modifier= modifier.clickable (
                onClick = {
                    onClick(lottieUrl)
                }
            ),
            text = lottieUrl,
            fontSize = 14.sp,
            color = Color.Blue
        )

        Text(
            text = stringResource(R.string.photos_attribution_spacex),
            fontSize = 16.sp
        )
        val photosAttributionUrl = stringResource(R.string.photos_spacex_url)

        Text(
            modifier= modifier.clickable (
                onClick = {
                    onClick(photosAttributionUrl)
                }
            ),
            text = photosAttributionUrl,
            fontSize = 14.sp,
            color = Color.Blue
        )
    }
}

private fun openWebUrl(context:Context, urlAddress: String) {
    if (urlAddress.isNotEmpty())
        context.startActivity(
            Intent(
                Intent.ACTION_VIEW,
                Uri.parse(urlAddress)
            )
    )
}
