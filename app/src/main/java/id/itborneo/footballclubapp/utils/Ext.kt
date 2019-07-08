package id.itborneo.footballclubapp.utils

import android.content.Context
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import com.bumptech.glide.Glide

fun View.visible(){
    this.visibility = View.VISIBLE
}
fun View.invis(){
    this.visibility = View.INVISIBLE
}

fun Context.toast (msg: String){
    Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
}

fun ImageView.loadImageFromUrl(url: String){
    Glide.with(this.context)
        .load(url)
        .into(this)
}