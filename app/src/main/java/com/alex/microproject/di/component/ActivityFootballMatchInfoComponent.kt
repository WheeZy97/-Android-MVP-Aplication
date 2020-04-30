package com.alex.microproject.di.component

import com.alex.microproject.activity.FootballMatchInfoActivity
import com.alex.microproject.di.ActivityScope
import com.alex.microproject.di.module.ActivityFootballMatchInfoModule
import dagger.Subcomponent

@ActivityScope
@Subcomponent(modules = [ActivityFootballMatchInfoModule::class])
interface ActivityFootballMatchInfoComponent {
    fun inject(activity: FootballMatchInfoActivity)
}