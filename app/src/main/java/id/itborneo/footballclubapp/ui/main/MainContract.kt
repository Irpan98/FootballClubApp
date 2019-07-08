package id.itborneo.footballclubapp.ui.main

import id.itborneo.footballclubapp.data.Responses.League
import id.itborneo.footballclubapp.data.Responses.TeamItem
import id.itborneo.footballclubapp.ui.main.model.Team

interface MainContract {

    interface Presenter{
        fun onAttach(view: View)
        fun onDetach()
        fun getLeagues()
        fun getTeamsByLeague(leagueName : String)
    }

    interface View{
        fun showloading(isLoading : Boolean){}
        fun showLeagues(League: List<League>?){}
        fun showError(msg: String){}
        fun showTeams(teams: List<Team>){}

    }
}