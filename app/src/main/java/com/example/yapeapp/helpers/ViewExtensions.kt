package com.example.yapeapp.helpers

import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide

/**
 * @author Axel Sanchez
 */
fun View.show(){
    this.visibility = View.VISIBLE
}

fun View.hide(){
    this.visibility = View.GONE
}

fun ImageView.load(url: String?){
    Glide
        .with(this.context)
        .load(url)
        .centerCrop()
        .into(this)
}