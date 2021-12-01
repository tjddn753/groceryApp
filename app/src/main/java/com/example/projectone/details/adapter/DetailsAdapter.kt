package com.example.projectone.subcategory.adapter

import Details
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.projectone.databinding.ActivityDetailsViewHolderBinding
import com.example.projectone.homescreen.Communicator

class DetailsAdapter(val details:List<Details>,val communicator: Communicator):RecyclerView.Adapter<DetailsViewHolder>() {

    private lateinit var onSubCategorySelected: (Details, Int) -> Unit
    fun setOnSubCategorySelectedListener(listener: (Details, Int) -> Unit) {
        this.onSubCategorySelected = listener
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailsViewHolder {
       val layoutInflater=LayoutInflater.from(parent.context)
        val binding=ActivityDetailsViewHolderBinding.inflate(layoutInflater)
        return DetailsViewHolder(binding, communicator)
    }

    override fun onBindViewHolder(holder: DetailsViewHolder, position: Int) {
       holder.bindData(details[position])
        holder.binding.etQuantity.text=details[position].QuantityMine.toString()
        holder.itemView.setOnClickListener {
            if(this::onSubCategorySelected.isInitialized)
            {
                onSubCategorySelected(details[position],position)
            }
        }
    }

    override fun getItemCount()=details.size

}