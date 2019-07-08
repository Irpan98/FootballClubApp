package id.itborneo.footballclubapp.ui.main

import android.util.Log
import id.itborneo.footballclubapp.data.Responses.LeaguesResponse
import id.itborneo.footballclubapp.data.Responses.TeamsResponse
import id.itborneo.footballclubapp.data.network.ApiClient
import id.itborneo.footballclubapp.ui.detail.DetailContract
import id.itborneo.footballclubapp.utils.mapper.getTeams
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response



class MainPresenter : MainContract.Presenter {

    override fun getTeamsByLeague (leagueName: String) {
        mView?.showloading(true)

        ApiClient.create().getTeamsByLeague(leagueName)
            .enqueue(object : Callback<TeamsResponse>{
                override fun onFailure(call: Call<TeamsResponse>, t: Throwable) {
                    mView?.showError(t.message.toString())
                }

                override fun onResponse(call: Call<TeamsResponse>, response: Response<TeamsResponse>) {
                    val teams = response.body()
                    mView?.showloading(false)
                    teams?.teams?.let { getTeams(it) }?.let { mView?.showTeams(it) }

                }
            })
    }

    private var mView : MainContract.View? =null

    override fun getLeagues() {

        mView?.showloading(true)

        ApiClient.create().getAllLeagues().enqueue(object : Callback<LeaguesResponse> {
                override fun onFailure(call: Call<LeaguesResponse>, t: Throwable) {}

                override fun onResponse(call: Call<LeaguesResponse>, response: Response<LeaguesResponse>) {
//                    Log.d("MainPresenter",response.body().toString())
                    mView?.showloading(false)
                    mView?.showLeagues(response.body()?.leagues)

                }
        })
    }

    override fun onAttach(view: MainContract.View) { mView = view }
    override fun onDetach() { mView =null }

}