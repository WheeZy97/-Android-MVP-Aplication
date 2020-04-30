package com.alex.microproject.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import com.alex.microproject.MicroApplication
import com.alex.microproject.R
import com.alex.microproject.consts.IntentVar
import com.alex.microproject.di.module.ActivityFootballMatchesModule
import com.alex.microproject.manager.RealmDataManager
import com.alex.microproject.model.realm.FootballMatch
import com.alex.microproject.presenter.FootballMatchesPresenter
import com.alex.microproject.view.FootballMatchesView
import com.alex.microproject.widget.adapter.FootballMatchesAdapter
import io.realm.RealmResults
import kotlinx.android.synthetic.main.activity_football_matches.*
import javax.inject.Inject

class FootballMatchesActivity : AppCompatActivity(), FootballMatchesView, FootballMatchesAdapter.OnItemListener {

    private lateinit var footballMatchesAdapter: FootballMatchesAdapter
    private lateinit var footballMatches: RealmResults<FootballMatch>

    @Inject
    lateinit var footballMatchesPresenter: FootballMatchesPresenter

    @Inject
    lateinit var realmDataManager: RealmDataManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_football_matches)
        setSupportActionBar(football_matches_activity_toolbar as Toolbar)
        supportActionBar?.title = resources.getString(R.string.football_matches)
        inject()

        footballMatches = realmDataManager.getFootballMatches()
        initAdapter()

        footballMatches.addChangeListener { t, changeSet ->
            footballMatchesAdapter.notifyDataSetChanged()
        }
        footballMatchesPresenter.fetchFootballMatchesData()
    }

    private fun initAdapter() {
        footballMatchesAdapter = FootballMatchesAdapter(footballMatches, this)
        rv_football_matches.layoutManager = LinearLayoutManager(this)
        rv_football_matches.adapter = footballMatchesAdapter
    }

    override fun onItemClick(adapterPosition: Int) {
        val footballMatch = footballMatches[adapterPosition]

        if (footballMatch != null)
            startFootballMatchInfoActivity(footballMatch.title)
    }

    private fun startFootballMatchInfoActivity(footballMatchName: String) {
        val intent = Intent(this, FootballMatchInfoActivity::class.java)
        intent.putExtra(IntentVar.FOOTBALL_MATCH_NAME, footballMatchName)
        startActivity(intent)
    }

    private fun inject() {
        MicroApplication.applicationComponent
            .plus(ActivityFootballMatchesModule(this))
            .inject(this)
    }

    override fun onError(error: String?) {
        if (error == null) return

        Toast.makeText(this, error, Toast.LENGTH_SHORT).show()
    }

    override fun onDestroy() {
        footballMatchesPresenter.detachView()
        super.onDestroy()
    }
}