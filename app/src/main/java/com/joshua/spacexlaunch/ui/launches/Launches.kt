package com.joshua.spacexlaunch.ui.launches

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.google.accompanist.coil.rememberCoilPainter
import com.joshua.spacexlaunch.R
import com.joshua.spacexlaunch.model.vo.LaunchItem
import com.joshua.spacexlaunch.utils.getLocalTimeFromUnix
import timber.log.Timber

@ExperimentalFoundationApi
@Composable
fun GetLaunches(
    navController: NavController,
    setTitle: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val viewModel: LaunchesViewModel = viewModel()
    LaunchesComposable(modifier, viewModel)
}

@Composable
fun LaunchesComposable(
    modifier: Modifier,
    viewModel: LaunchesViewModel
) {
    val lazyPagingItems= viewModel.launchesData.collectAsLazyPagingItems()
    LazyColumn(modifier)
    {
         items(lazyPagingItems){it->
             when(it){
                 LaunchesState.EndFooterItem ->{
                     Timber.i("LaunchesState EndFooterItem")
                 }
                 is LaunchesState.Launches -> {
                     Timber.i("LaunchesState Launches")
                     LaunchItemCard(it.data)
                 }
                 LaunchesState.Loaded ->{
                     Timber.i("LaunchesState Loaded")
                 }
                 LaunchesState.Loading -> {
                     Timber.i("LaunchesState Loading")
                 }
                 is LaunchesState.NonRecoverableError -> {
                     Timber.i("LaunchesState NonRecoverableError:${it.exception}")
                 }
                 is LaunchesState.RecoverableError -> {
                     Timber.i("LaunchesState RecoverableError:${it.exception}")
                 }
                 else -> {
                     Timber.i("LaunchesState unknown")
                 }

             }

         }
    }
}

@Composable
fun LaunchItemCard(launch: LaunchItem){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(10.dp),
        verticalAlignment = Alignment.CenterVertically
    ){
        val painter = rememberCoilPainter(
            request =  launch.links?.patch?.small,
            fadeIn = true,
            requestBuilder = {
                placeholder(R.drawable.ic_baseline_guess)
            },
            previewPlaceholder = R.drawable.ic_baseline_guess,
        )

        Image(
            modifier = Modifier
                .size(50.dp)
                .weight(1f),
            painter = painter,
            contentDescription= launch.details
        )

        Column(
            modifier = Modifier
                .weight(4f)
                .padding(start = 20.dp, end = 10.dp)
        ) {
            Text(
                text = stringResource(
                    R.string.flight_number_template,
                    launch.flight_number
                ),
                fontSize = 16.sp
            )
            Text(
                text = launch.name ?: stringResource(R.string.launch_date_null),
                fontSize = 12.sp
            )
            Text(
                text = if (launch.date_unix != null) getLocalTimeFromUnix(launch.date_unix)
                else stringResource(R.string.launch_date_null),
                fontSize = 12.sp
            )

        }

        Column(
            modifier= Modifier.weight(1.5f),
            verticalArrangement= Arrangement.Top,
            horizontalAlignment= Alignment.End
        ) {
            Text(
                 text = if(launch.success == true) stringResource(R.string.launch_success)
                 else stringResource(R.string.launch_failed),
                 color = if(launch.success == true) Color.Blue else Color.Red
            )
        }

    }
}