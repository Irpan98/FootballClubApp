package id.itborneo.footballclubapp.ui

import android.net.sip.SipSession
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import id.itborneo.footballclubapp.R
import id.itborneo.footballclubapp.ui.main.model.Team
import kotlinx.android.synthetic.main.item_row_teams.view.*
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import id.itborneo.footballclubapp.utils.loadImageFromUrl
import java.net.URL


interface TeamListener{
    fun onTeamClicked(team: Team){

    }
}

class TeamsAdapter (private val teams : List<Team>, val listener:(Team )-> Unit): RecyclerView.Adapter<TeamsViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeamsViewHolder {
        val view =LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_row_teams, parent, false)
        return TeamsViewHolder(view)
    }



    override fun getItemCount()= teams.size

    override fun onBindViewHolder(holder: TeamsViewHolder, position: Int) {
        holder.bindItem(teams[position], listener)
    }
}


class TeamsViewHolder(itemView : View):RecyclerView.ViewHolder(itemView){
    fun bindItem(team: Team, listener: (Team)->Unit) = with(itemView){
        txt_team.text = team.teamName
//        val myURL = URL(team.TeamLogo)
//        img_team.setImageResource(myURL)

        team.TeamLogo?.let { img_team.loadImageFromUrl(it) }
//
//        Glide.with(context)
//            .load(team.TeamLogo)
//            .apply(RequestOptions().override(100, 100))
//            .into(img_team)

        itemView.setOnClickListener {
            listener(team)
        }


    }
}