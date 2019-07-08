package id.itborneo.footballclubapp.ui.detail

import id.itborneo.footballclubapp.ui.main.MainContract
import id.itborneo.footballclubapp.data.network.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import android.util.Log
import id.itborneo.footballclubapp.data.Responses.LeaguesResponse
import id.itborneo.footballclubapp.data.Responses.TeamsResponse

class DetailPresenter : DetailContract.Presenter{
    private var mView : DetailContract.View? =null

    override fun getTeamsById(id: String) {
        ApiClient.create().getDetailTeamById(id)
            .enqueue(object : Callback<TeamsResponse>{
                override fun onFailure(call: Call<TeamsResponse>, t: Throwable) {
                    mView?.showError(t.message.toString())

                }

                override fun onResponse(call: Call<TeamsResponse>, response: Response<TeamsResponse>) {
                    val teams = response.body()
                    Log.d("DetailPresenter", teams?.teams?.get(0)?.strDescriptionEN)
                    teams?.teams?.let { mView?.showTeams(it) }

                }

            })

    }


    override fun onAttach(view: DetailContract.View) {
        mView = view
    }

    override fun onDetach() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}