package com.jain.ullas.cats.presentation.breeds

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.jain.ullas.cats.R
import com.jain.ullas.cats.data.Breed
import com.jain.ullas.cats.extentions.hide
import com.jain.ullas.cats.extentions.show
import com.jain.ullas.cats.presentation.CameraActivity
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class BreedsActivity : AppCompatActivity(), BreedsView {

    private val viewModel by viewModel<BreedsViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel.liveData.observe(this, Observer {
            onViewModelUpdated(it)
        })

        upload.setOnClickListener { startActivity(Intent(this, CameraActivity::class.java)) }
        viewModel.loadBreeds()
    }

    private fun onViewModelUpdated(breedsData: BreedsViewModelData) {
        if (breedsData.isLoading) {
            showProgress()
            hideBreeds()
        } else {
            hideProgress()
        }

        if (breedsData.breeds?.isNotEmpty() == true) {
            showBreeds(breedsData.breeds)
        }
    }

    override fun showBreeds(breeds: List<Breed>) {
        breedsRecyclerView.apply {
            show()
            adapter = BreedsAdapter().apply { setData(breeds) }
        }
    }

    override fun hideBreeds() {
        breedsRecyclerView.hide()
    }

    override fun showProgress() {
        progress.show()
    }

    override fun hideProgress() {
        progress.hide()
    }
}
