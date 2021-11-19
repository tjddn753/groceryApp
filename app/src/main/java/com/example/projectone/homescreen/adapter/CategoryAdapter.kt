package com.example.projectone.homescreen.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.projectone.Category
import com.example.projectone.databinding.ActivityCategoryViewHolderBinding

class CategoryAdapter(val category: List<Category>):RecyclerView.Adapter<CategoryViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
         val layoutInflater=LayoutInflater.from(parent.context)
        val binding=ActivityCategoryViewHolderBinding.inflate(layoutInflater)
        return CategoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {

          holder.bindData(category[position])
    }

    override fun getItemCount()=category.size

}