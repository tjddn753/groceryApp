package com.example.projectone.subcategory.adapter

import Details
import Product
import android.annotation.SuppressLint
import android.text.Editable
import android.util.Log
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
      //  lateinit var products:ArrayList<Product>
      //


        //   binding.tvDetailsId.text=details.subId.toString()
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


        binding.btnDelete.setOnClickListener {
      //      val databaseHandler = DatabaseHandler(Util.getHomeContext())
            databaseHandler.deleteItem(details)

          communicator.toCart()
        }

        binding.btnPlus.setOnClickListener {
            var input = binding.etQuantity.text.toString()
            var value=Integer.parseInt(input)

            value+=1
            binding.etQuantity.setText(value.toString())
            details.QuantityMine=Integer.parseInt(value.toString())
            Log.i("Data", details.QuantityMine.toString())
            databaseHandler.updateEItem(details)
           communicator.toCart()
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
            databaseHandler.updateEItem(details)
            communicator.toCart()
        }

    }
}