package com.example.projectone.subcategory.adapter

import Details
import android.annotation.SuppressLint
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.projectone.R
import com.example.projectone.Util
import com.example.projectone.database.DatabaseHandler
import com.example.projectone.databinding.ActivityDetailsViewHolderBinding
import com.example.projectone.databinding.FragmentCartViewHolderBinding
import com.example.projectone.homescreen.Communicator
import com.squareup.picasso.Picasso

class CartViewHolder(val binding:FragmentCartViewHolderBinding,val communicator: Communicator):RecyclerView.ViewHolder(binding.root) {



    fun bindData(details: Details){
        binding.tvDetailsId.text=details.subId.toString()
        binding.tvDetailsName.text=details.productName
        binding.tvDetailsDescription.text=details.description


   //     val quantity=binding.etQuantity.text as Int
        binding.tvPrice.text="$ ${details.price}"
        val url="https://rjtmobile.com/grocery/images/"+details.image
        Picasso.get()
            .load(url)
            .placeholder(R.drawable.rocket)
            .into(binding.ivDetails)

        binding.btnUpdate.setOnClickListener {
            val databaseHandler = DatabaseHandler(Util.getHomeContext())
    //        val emp: List<Details> = databaseHandler.getItems()
            databaseHandler.updateEItem(details)
          


            communicator.toCart()

   //       Toast.makeText(Util.getHomeContext(),"hello",Toast.LENGTH_LONG).show()
        }
        binding.btnDelete.setOnClickListener {
            val databaseHandler = DatabaseHandler(Util.getHomeContext())
            databaseHandler.deleteItem(details)

            communicator.toCart()
        }

    }
}