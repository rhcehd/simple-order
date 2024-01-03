package dev.rhcehd123.simpleorder.config

import io.kotest.core.config.AbstractProjectConfig
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain

/*
@OptIn(ExperimentalCoroutinesApi::class)
object ProjectConfig: AbstractProjectConfig() {
    private val testDispatcher = StandardTestDispatcher()
    //override val coroutineDebugProbes: Boolean = true
    //override val coroutineTestScope: Boolean = true
    override suspend fun beforeProject() {
        super.beforeProject()
        Dispatchers.setMain(testDispatcher)
    }

    override suspend fun afterProject() {
        super.afterProject()
        Dispatchers.resetMain()
    }
}*/
