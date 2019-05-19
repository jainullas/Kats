package com.jain.ullas.cats.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.jain.ullas.cats.R
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity(), MainView {

    private val viewModel by viewModel<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel.uiData.observe(this, Observer {
            onViewModelUpdated(it)
        })

        refresh.setOnClickListener { onRefreshButtonClicked() }
        viewModel.loadRandomCat()
    }

    private fun onViewModelUpdated(randomCatViewModel: RandomCatViewModel) {
        if (randomCatViewModel.isLoading) {
            showProgress()
            hideRandomCat()
        } else {
            hideProgress()
        }

        randomCatViewModel.randomCat?.url?.let {
            showRandomCat(it)
        }
    }

    override fun showRandomCat(url: String) {
        with(imageView) {
            visibility = View.VISIBLE
            Glide.with(this)
                .load(url)
                .placeholder(R.drawable.img_loading_placeholder)
                .into(this)
        }
    }

    override fun hideRandomCat() {
        imageView.visibility = View.INVISIBLE
    }

    override fun onRefreshButtonClicked() {
        viewModel.loadRandomCat()
    }

    override fun showProgress() {
        progress.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        progress.visibility = View.GONE
    }
}
