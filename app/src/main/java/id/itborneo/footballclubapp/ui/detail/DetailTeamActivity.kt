package id.itborneo.footballclubapp.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import id.itborneo.footballclubapp.R
import id.itborneo.footballclubapp.data.Responses.TeamItem
import id.itborneo.footballclubapp.utils.EXTRA_ID_TEAM
import id.itborneo.footballclubapp.utils.loadImageFromUrl
import kotlinx.android.synthetic.main.activity_detail_team.*

class DetailTeamActivity : AppCompatActivity(), DetailContract.View {
    private lateinit var presenter: DetailPresenter
    private val teams = mutableListOf<TeamItem>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_team)

        val teamId =intent.getStringExtra(EXTRA_ID_TEAM)
        Log.d("DetailTeamActivity",teamId)

        presenter = DetailPresenter()
        presenter.onAttach(this)
        presenter.getTeamsById(teamId)


    }

    override fun showTeams(teams: List<TeamItem>) {

        val descTeam = teams[0].strDescriptionEN
        if( descTeam != null){
            txtDescription.text = descTeam
        }

        txtTeamName.text = teams[0].strTeam
        imgTeam.loadImageFromUrl(teams[0].strTeamFanart1)
    }
}
