package id.itborneo.footballclubapp.utils.mapper

import id.itborneo.footballclubapp.data.Responses.TeamItem
import id.itborneo.footballclubapp.ui.detail.model.TeamDetail
import id.itborneo.footballclubapp.ui.main.model.Team

fun getTeams( teams: List<TeamItem>): List<Team>{
    val list: MutableList<Team> = mutableListOf()
    teams.forEach {
        list.add(Team(it.idTeam, it.strTeam, it.strTeamBadge))
    }
    return list
}

//fun getTeamDetail(teamItem: TeamItem) : TeamDetail{
////    return TeamDetail(teamItem.idTeam,teamItem.strTeam,teamItem.strTeamBadge,teamItem.)
//}