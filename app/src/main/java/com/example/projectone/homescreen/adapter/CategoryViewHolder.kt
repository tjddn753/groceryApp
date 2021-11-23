package com.example.projectone.homescreen.adapter

import androidx.recyclerview.widget.RecyclerView
import com.example.projectone.Category
import com.example.projectone.R
import com.example.projectone.databinding.ActivityCategoryViewHolderBinding
import com.squareup.picasso.Picasso

class CategoryViewHolder(val binding:ActivityCategoryViewHolderBinding):RecyclerView.ViewHolder(binding.root){

    fun bindData(category: Category){
        binding.tvCategoryId.text=category.catId.toString()
        binding.tvCategoryName.text=category.catName
        val url="https://rjtmobile.com/grocery/images/"+category.catImage
        Picasso.get()
            .load(url)
            .placeholder(R.drawable.rocket)
            .into(binding.ivCategory)

    }




}