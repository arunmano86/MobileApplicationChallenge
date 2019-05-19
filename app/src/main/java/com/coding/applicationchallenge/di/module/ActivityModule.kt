package com.coding.applicationchallenge.di.module

import android.app.Activity
import com.coding.applicationchallenge.ui.list.ListContract
import com.coding.applicationchallenge.ui.list.ListPresenter
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
    fun provideLoginPresenter(): LoginContract.Presenter {
        return LoginPresenter()
    }

    @Provides
    fun provideListPresenter(): ListContract.Presenter {
        return ListPresenter()
    }
}