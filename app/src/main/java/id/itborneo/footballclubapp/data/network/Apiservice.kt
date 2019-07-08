package id.itborneo.footballclubapp.data.network

import id.itborneo.footballclubapp.data.Responses.LeaguesResponse
import id.itborneo.footballclubapp.data.Responses.TeamsResponse
import id.itborneo.footballclubapp.utils.*
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface Apiservice {


    @GET(ENDPOINT_ALL_LEAGUES)
    fun getAllLeagues() : Call<LeaguesResponse>

//    @GET(ENDPOINT_SOCCER_LEAGUES)
//    fun getAllLeagues() : Call<LeaguesResponse>

    @GET(ENDPOINT_TEAMS)
    fun getTeamsByLeague(
        @Query ("l") league: String
    ): Call<TeamsResponse>

    @GET(ENDPOINT_TEAMS_BY_ID)
    fun getDetailTeamById(
        @Query("id") id: String
    ): Call<TeamsResponse>

//    @GET(ENDPOINT_TEAMS+ENDPOINT_DUMMY)
//    fun getTeamsByLeague() : Call<TeamsResponse>



}