package com.wagnermessias.bankapp.core.base

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancelChildren
import kotlin.coroutines.CoroutineContext


abstract class BaseViewModel: ViewModel(),CoroutineScope {
    override val coroutineContext = Dispatchers.Main

//    val mainContext: CoroutineContext = Main
//    val ioContext: CoroutineContext = IO

    private val viewModelJob = SupervisorJob()

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancelChildren()
    }
}