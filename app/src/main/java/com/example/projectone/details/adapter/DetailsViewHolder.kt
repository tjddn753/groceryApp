package com.example.projectone.subcategory.adapter

import Details
import android.annotation.SuppressLint
import android.util.Log
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
        var databaseHandler = DatabaseHandler(Util.getHomeContext())

        binding.btnAdd.setOnClickListener {
            val databaseHandler = DatabaseHandler(Util.getHomeContext())
            //        val emp: List<Details> = databaseHandler.getItems()
            details.IDMine=++Util.index

            val value=binding.etQuantity.text
            details.QuantityMine=Integer.parseInt(value.toString())
            val insertId=databaseHandler.addItem(details)
            if(insertId > 0) {
                Toast.makeText(Util.getHomeContext(), "Item added with id : $insertId", Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(Util.getHomeContext(), "Failed to add Item. Please retry.", Toast.LENGTH_LONG).show()
            }
            communicator.toDetails()

    //        Toast.makeText(Util.getHomeContext(),"${details.price}",Toast.LENGTH_LONG).show()
        }
        binding.btnPlus.setOnClickListener {
            var input = binding.etQuantity.text.toString()
            var value=Integer.parseInt(input)

            value+=1
            binding.etQuantity.setText(value.toString())
            details.QuantityMine=Integer.parseInt(value.toString())
            Log.i("Data", details.QuantityMine.toString())
      //      databaseHandler.updateEItem(details)
        //    communicator.toDetails()
        }
        binding.btnMinus.setOnClickListener {
            var input = binding.etQuantity.text.toString()
            var value=Integer.parseInt(input)

            if(value>=1)
                value-=1
            else
                value=0
            binding.etQuantity.setText(value.toString())

            details.QuantityMine=Integer.parseInt(value.toString())
            Log.i("Data", details.QuantityMine.toString())
         //   databaseHandler.updateEItem(details)
         //   communicator.toDetails()
        }

    }
}