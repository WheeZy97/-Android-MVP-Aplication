package com.alex.microproject.di.component

import com.alex.microproject.di.module.*
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [RemoteModule::class, RealmModule::class, DisposableModule::class])
interface ApplicationComponent {
    fun plus(module: ActivityFootballMatchesModule) : ActivityFootballMatchesComponent
    fun plus(module: ActivityFootballMatchInfoModule) : ActivityFootballMatchInfoComponent
}