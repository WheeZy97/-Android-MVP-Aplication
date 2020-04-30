package com.alex.microproject.di.module

import com.alex.microproject.api.service.FootballService
import com.alex.microproject.di.ActivityScope
import com.alex.microproject.manager.RealmDataManager
import com.alex.microproject.presenter.FootballMatchesPresenter
import com.alex.microproject.view.FootballMatchesView
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable

@Module
class ActivityFootballMatchesModule(private val viewInterface: FootballMatchesView?) {

    @Provides
    @ActivityScope
    fun providePresenter(footballApi: FootballService, realmDataManager: RealmDataManager,
                         compositeDisposable: CompositeDisposable) : FootballMatchesPresenter =
        FootballMatchesPresenter(viewInterface, footballApi, realmDataManager, compositeDisposable)
}