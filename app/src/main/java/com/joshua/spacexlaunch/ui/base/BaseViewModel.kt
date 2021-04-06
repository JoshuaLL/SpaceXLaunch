package com.joshua.spacexlaunch.ui.base

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow
import org.koin.core.component.KoinComponent
import java.util.*

abstract class BaseViewModel : ViewModel() , KoinComponent