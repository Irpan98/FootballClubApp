package id.itborneo.footballclubapp.ui.main.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Team(
    val teamId: String,
    val teamName: String,
    val TeamLogo: String?
): Parcelable
