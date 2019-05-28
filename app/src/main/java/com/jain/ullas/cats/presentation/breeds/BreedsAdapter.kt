package com.jain.ullas.cats.presentation.breeds

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jain.ullas.cats.R
import com.jain.ullas.cats.data.Breed
import com.jain.ullas.cats.extentions.loadImageUrl
import com.jain.ullas.cats.extentions.show
import kotlinx.android.synthetic.main.item_breed.view.*

class BreedsAdapter : RecyclerView.Adapter<BreedsAdapter.BreedsViewHolder>() {

    private val breedsList = mutableListOf<Breed>()

    fun setData(breeds: List<Breed>) {
        breedsList.apply {
            clear()
            addAll(breeds)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        BreedsViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_breed, parent,false))

    override fun getItemCount() = breedsList.size

    override fun onBindViewHolder(holder: BreedsViewHolder, position: Int) {
        holder.bind(breedsList[position])
    }

    inner class BreedsViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {

        fun bind(breed: Breed) {
            itemView.breedItemImage.apply {
                show()
                loadImageUrl(breed.wikipediaUrl?.substringAfterLast("/"))
            }
        }

    }

}