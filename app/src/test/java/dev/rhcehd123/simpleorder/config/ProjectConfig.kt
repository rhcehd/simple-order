package dev.rhcehd123.simpleorder.config

import io.kotest.core.config.AbstractProjectConfig
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain

@OptIn(ExperimentalCoroutinesApi::class)
object ProjectConfig: AbstractProjectConfig() {

    override suspend fun beforeProject() {
        super.beforeProject()
        Dispatchers.setMain(StandardTestDispatcher())
    }

    override suspend fun afterProject() {
        super.afterProject()
        Dispatchers.resetMain()
    }
}