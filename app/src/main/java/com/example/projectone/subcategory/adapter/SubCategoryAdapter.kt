package com.example.projectone.subcategory.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.projectone.databinding.ActivityCategoryViewHolderBinding
import com.example.projectone.subcategory.view.SubCategory
import com.example.projectone.subcategory.view.SubCategoryView

class SubCategoryAdapter(val subCategory:List<SubCategory>):RecyclerView.Adapter<subCategoryViewHolder>() {

    private lateinit var onSubCategorySelected: (SubCategory, Int) -> Unit
    fun setOnSubCategorySelectedListener(listener: (SubCategory, Int) -> Unit) {
        this.onSubCategorySelected = listener
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): subCategoryViewHolder {
       val layoutInflater=LayoutInflater.from(parent.context)
        val binding=ActivityCategoryViewHolderBinding.inflate(layoutInflater)
        return subCategoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: subCategoryViewHolder, position: Int) {
       holder.bindData(subCategory[position])
        holder.itemView.setOnClickListener {
            if(this::onSubCategorySelected.isInitialized)
            {
                onSubCategorySelected(subCategory[position],position)
            }
        }
    }

    override fun getItemCount()=subCategory.size

}