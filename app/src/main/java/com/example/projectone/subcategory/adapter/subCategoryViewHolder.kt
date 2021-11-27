package com.example.projectone.subcategory.adapter

import androidx.recyclerview.widget.RecyclerView
import com.example.projectone.R
import com.example.projectone.databinding.ActivityCategoryViewHolderBinding
import com.example.projectone.homescreen.Util
import com.example.projectone.subcategory.view.SubCategory
import com.example.projectone.subcategory.view.SubCategoryView
import com.squareup.picasso.Picasso

class subCategoryViewHolder(val binding:ActivityCategoryViewHolderBinding):RecyclerView.ViewHolder(binding.root) {


    fun bindData(subCategory: SubCategory){
        binding.tvCategoryId.text=subCategory.subId.toString()
        binding.tvCategoryName.text=subCategory.subName
        val url= Util.catImage
        Picasso.get()
            .load(url)
            .placeholder(R.drawable.rocket)
            .into(binding.ivCategory)


    }
}