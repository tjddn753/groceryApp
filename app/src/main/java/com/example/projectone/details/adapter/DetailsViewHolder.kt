package com.example.projectone.subcategory.adapter

import Details
import android.annotation.SuppressLint
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.projectone.R
import com.example.projectone.Util
import com.example.projectone.database.DatabaseHandler
import com.example.projectone.databinding.ActivityDetailsViewHolderBinding
import com.example.projectone.homescreen.Communicator
import com.squareup.picasso.Picasso

class DetailsViewHolder(val binding:ActivityDetailsViewHolderBinding,val communicator: Communicator):RecyclerView.ViewHolder(binding.root) {



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

        binding.btnAdd.setOnClickListener {
            val databaseHandler = DatabaseHandler(Util.getHomeContext())
            //        val emp: List<Details> = databaseHandler.getItems()
            val insertId=databaseHandler.addItem(details)
            if(insertId > 0) {
                Toast.makeText(Util.getHomeContext(), "Employee added with id : $insertId", Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(Util.getHomeContext(), "Failed to add employee. Please retry.", Toast.LENGTH_LONG).show()
            }
            communicator.toDetails()

    //        Toast.makeText(Util.getHomeContext(),"${details.price}",Toast.LENGTH_LONG).show()
        }

    }
}