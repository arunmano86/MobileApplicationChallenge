package com.coding.applicationchallenge

import android.app.Application
import com.coding.applicationchallenge.di.component.ApplicationComponent
import com.coding.applicationchallenge.di.component.DaggerApplicationComponent
import com.coding.applicationchallenge.di.module.ApplicationModule

class BaseApp: Application() {

    lateinit var component: ApplicationComponent

    override fun onCreate() {
        super.onCreate()

        instance = this
        setup()

    }

    fun setup() {
        component = DaggerApplicationComponent.builder()
                .applicationModule(ApplicationModule(this)).build()
        component.inject(this)

        /*if(LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to Perflib for heap analysis.
            // You should not init your app in this process.
            return
        }*/
    }

    fun getApplicationComponent(): ApplicationComponent {
        return component
    }

    companion object {
        lateinit var instance: BaseApp private set
    }
}