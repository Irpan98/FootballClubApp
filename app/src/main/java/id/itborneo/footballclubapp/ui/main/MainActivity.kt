package id.itborneo.footballclubapp.ui.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import id.itborneo.footballclubapp.R
import id.itborneo.footballclubapp.data.Responses.League
import id.itborneo.footballclubapp.ui.TeamListener
import id.itborneo.footballclubapp.ui.TeamsAdapter
import id.itborneo.footballclubapp.ui.detail.DetailTeamActivity
import id.itborneo.footballclubapp.ui.main.model.Team
import id.itborneo.footballclubapp.utils.EXTRA_ID_TEAM
import id.itborneo.footballclubapp.utils.ItemClickSupport
import id.itborneo.footballclubapp.utils.toast
import kotlinx.android.synthetic.main.activity_main.*



class MainActivity : AppCompatActivity(), MainContract.View {


    private lateinit var presenter: MainPresenter

    private  var leaguesName = mutableListOf<String>()
    private  var teamsName = mutableListOf<String>()

    private lateinit var teamsAdapter: TeamsAdapter
    private val teams = mutableListOf<Team>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        presenter = MainPresenter()
        presenter.onAttach(this)
        presenter.getLeagues()

        teamsAdapter = TeamsAdapter(teams){team ->
            this.toast(team.teamName)
            val intent = Intent(this,DetailTeamActivity::class.java)
            intent.putExtra(EXTRA_ID_TEAM, team.teamId)
            startActivity(intent)
        }

//        teamsAdapter = TeamsAdapter(teams, object : (Team)-> Unit{
//            override fun onTeamClicked(team: Team) {
//                Toast.makeText(this@MainActivity,team.teamName, Toast.LENGTH_LONG).show()
//            }
//
//        })
        rv_teams.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = teamsAdapter


//            ItemClickSupport.addTo()
        }



        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                Toast.makeText(this@MainActivity, leaguesName[position], Toast.LENGTH_LONG).show()
                Log.d("MainActivity"," onItemSelected  ${leaguesName[position]}")
                showRecyclerView( leaguesName[position])
            }
        }
    }

    fun showRecyclerView( leagues: String){
        presenter.getTeamsByLeague(leagues)
    }

    override fun showloading(isLoading: Boolean) {
        swipeRefresh.isRefreshing = isLoading
    }



    override fun showTeams(teamsS: List<Team>) {
        if(this.teams.isNotEmpty())teams.clear()
//        teamsS?.forEach {
//            teams.add(Team(it.teamId,it.teamName,it.TeamLogo))
//        }

        teams.addAll(teamsS)
        teamsAdapter.notifyDataSetChanged()
    }

    override fun showLeagues(League: List<League>?) {
        if(League != null){ League.forEach {  leaguesName.add(it.strLeague) }
            setDataSpinner(leaguesName)
        }
    }

    override fun onDestroy() {
        presenter.onDetach()
        super.onDestroy()
    }

    override fun showError(msg: String) {
        Toast.makeText(this,msg, Toast.LENGTH_LONG).show()
    }

    private fun setDataSpinner(data: List<String>){
        val adapter = ArrayAdapter<String>(
            this, R.layout.item_row_leagues, leaguesName)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter
    }
}
