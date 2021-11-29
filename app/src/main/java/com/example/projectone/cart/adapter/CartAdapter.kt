package com.example.projectone.subcategory.adapter

import Details
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.projectone.databinding.ActivityDetailsViewHolderBinding
import com.example.projectone.databinding.FragmentCartBinding
import com.example.projectone.databinding.FragmentCartViewHolderBinding
import com.example.projectone.homescreen.Communicator

class CartAdapter(val details:List<Details>,val communicator: Communicator):RecyclerView.Adapter<CartViewHolder>() {

    private lateinit var onSubCategorySelected: (Details, Int) -> Unit
    fun setOnSubCategorySelectedListener(listener: (Details, Int) -> Unit) {
        this.onSubCategorySelected = listener
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
       val layoutInflater=LayoutInflater.from(parent.context)
        val binding=FragmentCartViewHolderBinding.inflate(layoutInflater)
        return CartViewHolder(binding,communicator)
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
       holder.bindData(details[position])
        holder.itemView.setOnClickListener {
            if(this::onSubCategorySelected.isInitialized)
            {
                onSubCategorySelected(details[position],position)
            }
        }
    }

    override fun getItemCount()=details.size

}