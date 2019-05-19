package com.coding.applicationchallenge.di.module

import android.app.Application
import com.coding.applicationchallenge.BaseApp
import com.coding.applicationchallenge.di.scope.PerApplication
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ApplicationModule(private val baseApp: BaseApp) {

    @Provides
    @Singleton
    @PerApplication
    fun provideApplication(): Application {
        return baseApp
    }
}