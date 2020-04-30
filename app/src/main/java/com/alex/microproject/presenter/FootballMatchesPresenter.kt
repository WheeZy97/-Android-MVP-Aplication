package com.alex.microproject.presenter

import com.alex.microproject.api.service.FootballService
import com.alex.microproject.manager.RealmDataManager
import com.alex.microproject.view.FootballMatchesView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class FootballMatchesPresenter(private var view: FootballMatchesView?, private val footballApi: FootballService,
                               private val realmDataManager: RealmDataManager, private val compositeDisposable: CompositeDisposable) {

    fun fetchFootballMatchesData() {
        val disposable = footballApi.getFootballMatches()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({ footballMatches ->
                realmDataManager.saveFootballMatchesToRealm(footballMatches)
            }, {
                it.printStackTrace()
                view?.onError(it.localizedMessage)
            })
        compositeDisposable.add(disposable)
    }

    fun detachView() {
        compositeDisposable.dispose()
        view = null
    }
}