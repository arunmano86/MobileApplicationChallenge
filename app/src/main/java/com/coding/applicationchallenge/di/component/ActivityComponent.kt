package com.coding.applicationchallenge.di.component


import com.coding.applicationchallenge.di.module.ActivityModule
import com.coding.applicationchallenge.ui.list.ListActivity
import com.coding.applicationchallenge.ui.login.LoginActivity
import dagger.Component

@Component(modules = arrayOf(ActivityModule::class))
interface ActivityComponent {

    fun inject(loginActivity: LoginActivity)

    fun inject(listActivity: ListActivity)

}