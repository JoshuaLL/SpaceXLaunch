package com.joshua.spacexlaunch.ui.launches

import android.os.Bundle
import android.view.View
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.joshua.spacexlaunch.R
//import com.joshua.spacexlaunch.databinding.FragmentLaunchesBinding
import com.joshua.spacexlaunch.model.vo.LaunchItem
import com.joshua.spacexlaunch.ui.compose.GlideImage
import com.joshua.spacexlaunch.utils.getLocalTimeFromUnix
import timber.log.Timber

class LaunchesFragment : Fragment() {

//    private lateinit var binding: FragmentLaunchesBinding
    private val viewModel: LaunchesViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.allLaunches.observe(viewLifecycleOwner){

        }

    }

//
//    @Composable
//    fun MySimpleListItem(itemViewState: ItemViewState) {
//        Text(text = itemViewState.text)
//    }
}

@ExperimentalFoundationApi
@Composable
fun GetLaunches(
    navController: NavController,
    setTitle: (String) -> Unit,
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
                LaunchesComposable(state.list)

            }
        }
    }


}

@Composable
fun LaunchesComposable(launchesList: List<LaunchItem>) {
    LazyColumn(
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp),)
    {
        items(launchesList) { item ->
            LaunchItemCard(item)
        }
    }
}

@Composable
fun LaunchItemCard(launch: LaunchItem){
    Row(
//            modifier = Modifier.fillMaxWidth()
//                .wrapContentHeight(),
        verticalAlignment = Alignment.CenterVertically
    ){
        Column {
            Text(
                stringResource(
                    R.string.flight_number_template,
                    launch.flight_number
                )
            )
            Text(launch.missionName ?: stringResource(R.string.launch_date_null))
            Text(
                if (launch.launchDateUnix != null) getLocalTimeFromUnix(launch.launchDateUnix)
                else stringResource(R.string.launch_date_null))
        }
        launch.links.missionPatchSmall?.let {
            GlideImage(it)
        }

    }
}