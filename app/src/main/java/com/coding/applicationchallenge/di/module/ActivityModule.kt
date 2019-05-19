package com.coding.applicationchallenge.di.module

import android.app.Activity
import com.coding.applicationchallenge.ui.login.LoginContract
import com.coding.applicationchallenge.ui.login.LoginPresenter
import dagger.Module
import dagger.Provides

@Module
class ActivityModule(private var activity: Activity) {

    @Provides
    fun provideActivity(): Activity {
        return activity
    }

    @Provides
    fun provideListPresenter(): LoginContract.Presenter {
        return LoginPresenter()
    }
}