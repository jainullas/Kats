package com.jain.ullas.cats.extentions

import android.view.View

fun View.show() {
    this.visibility = View.VISIBLE
}

fun View.hide() {
    this.visibility = View.GONE
}


fun show(vararg views: View) {
    views.forEach {
        it.visibility = View.VISIBLE
    }
}

fun hide(vararg views: View) {
    views.forEach {
        it.visibility = View.GONE
    }
}