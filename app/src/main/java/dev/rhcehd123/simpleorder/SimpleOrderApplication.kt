package dev.rhcehd123.simpleorder

import android.app.Application
import dev.rhcehd123.simpleorder.data.AppContainer
import dev.rhcehd123.simpleorder.data.AppContainerImpl
import dev.rhcehd123.simpleorder.data.repository.FakeOrderRepository

class SimpleOrderApplication: Application() {

    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = AppContainerImpl(
            FakeOrderRepository()
        )
    }
}