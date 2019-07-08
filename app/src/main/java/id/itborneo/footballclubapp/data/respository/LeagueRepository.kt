package id.itborneo.footballclubapp.data.respository

import id.itborneo.footballclubapp.data.network.ApiClient

fun getAllLeague() = ApiClient.create().getAllLeagues()