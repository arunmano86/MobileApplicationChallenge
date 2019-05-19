package com.coding.applicationchallenge.di.component

import com.coding.applicationchallenge.BaseApp
import com.coding.applicationchallenge.di.module.ApplicationModule
import dagger.Component

@Component(modules = arrayOf(ApplicationModule::class))
interface ApplicationComponent {

    fun inject(application: BaseApp)

}