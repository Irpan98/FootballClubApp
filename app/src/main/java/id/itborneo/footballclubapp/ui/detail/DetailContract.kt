package id.itborneo.footballclubapp.ui.detail

import id.itborneo.footballclubapp.data.Responses.TeamItem
import id.itborneo.footballclubapp.ui.main.model.Team


interface DetailContract {
    interface Presenter{
        fun onAttach(view: View)
        fun onDetach()
        fun getTeamsById(id : String)

    }

    interface View{
        fun showTeams(teams: List<TeamItem>)

       fun showError(msg: String)

    }
}