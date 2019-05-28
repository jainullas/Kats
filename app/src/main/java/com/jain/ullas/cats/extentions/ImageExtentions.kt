package com.jain.ullas.cats.extentions

import android.widget.ImageView
import androidx.annotation.DrawableRes
import com.bumptech.glide.Glide
import com.jain.ullas.cats.R

fun ImageView.loadImageUrl(url : String?, @DrawableRes placeHolder : Int = R.drawable.img_loading_placeholder){
    Glide.with(this.context)
        .load(url)
        .placeholder(placeHolder)
        .into(this)
}