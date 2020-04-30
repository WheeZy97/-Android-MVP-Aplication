package com.alex.microproject.di.component

import com.alex.microproject.activity.FootballMatchesActivity
import com.alex.microproject.di.ActivityScope
import com.alex.microproject.di.module.ActivityFootballMatchesModule
import dagger.Subcomponent

@ActivityScope
@Subcomponent(modules = [ActivityFootballMatchesModule::class])
interface ActivityFootballMatchesComponent {
    fun inject(activity: FootballMatchesActivity)
}