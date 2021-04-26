package com.joshua.spacexlaunch.ui.launches

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.google.accompanist.glide.rememberGlidePainter
import com.joshua.spacexlaunch.R
import com.joshua.spacexlaunch.model.vo.LaunchItem
import com.joshua.spacexlaunch.utils.getLocalTimeFromUnix
import timber.log.Timber

@ExperimentalFoundationApi
@Composable
fun GetLaunches(
    navController: NavController,
    setTitle: (String) -> Unit,
    modifier: Modifier = Modifier,
    launchesViewModel: LaunchesViewModel = viewModel()) {
    val launchesState by launchesViewModel.allLaunches.observeAsState()
    Timber.i("GetLaunches launchesState=$launchesState")
    launchesState?.let {state->
        when(state){
            LaunchesState.Loading -> { }
            LaunchesState.Loaded -> {}
            is LaunchesState.NonRecoverableError -> {

            }
            is LaunchesState.RecoverableError -> {

            }
            is LaunchesState.Success -> {
                LaunchesComposable(modifier, state.list)

            }
        }
    }
}

@Composable
fun LaunchesComposable(modifier: Modifier, launchesList: List<LaunchItem>) {
    LazyColumn( modifier)
    {
        items(launchesList) { item ->
            LaunchItemCard(item)
        }
    }
}

@Composable
fun LaunchItemCard(launch: LaunchItem){
    Row(
        modifier = Modifier.fillMaxWidth().wrapContentHeight().padding(10.dp),
        verticalAlignment = Alignment.CenterVertically
    ){
        Image(
            modifier = Modifier.size(50.dp).weight(1f),
            painter = rememberGlidePainter(
                request =  launch.links.missionPatchSmall,
                fadeIn = true,
                previewPlaceholder = R.drawable.ic_rocket_logo,
            ),
            contentDescription= launch.details

        )

        Column(
            modifier = Modifier.weight(4f)
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
                text = launch.missionName ?: stringResource(R.string.launch_date_null),
                fontSize = 12.sp
            )
            Text(
                text = if (launch.launchDateUnix != null) getLocalTimeFromUnix(launch.launchDateUnix)
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
                 text = if(launch.launchSuccess) stringResource(R.string.launch_success)
                 else stringResource(R.string.launch_failed),
                 color = if(launch.launchSuccess) Color.Blue else Color.Red
            )
        }

    }
}