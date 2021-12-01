package com.example.projectone.orders

import Product
import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.android.volley.RequestQueue
import com.example.projectone.R
import com.example.projectone.Util
import com.example.projectone.databinding.FragmentOrdersBinding
import org.json.JSONObject


class OrdersFragment() : Fragment() {

  lateinit var binding:FragmentOrdersBinding
    lateinit var queue: RequestQueue
    lateinit var product: ArrayList<Product>
    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        binding= FragmentOrdersBinding.inflate(inflater,container,false)


        val sharedPreferences:SharedPreferences?= this.activity?.getSharedPreferences("ProjectOne",
            Context.MODE_PRIVATE)

        val id=sharedPreferences?.getString("id","")
        val date=sharedPreferences?.getString("date","")
        val streetName=sharedPreferences?.getString("streetName","")
        val houseNo=sharedPreferences?.getString("houseNo","")
        val city=sharedPreferences?.getString("city","")
        val pincode=sharedPreferences?.getInt("pincode",0)

        val paymentMode=sharedPreferences?.getString("paymentMode","")
        val totalAmount=sharedPreferences?.getInt("totalAmount",0)

        binding.tvId.text="ID:"+id
        binding.tvDate.text=date
        binding.tvAddress.text= streetName+" "+houseNo+" "+city+" "+pincode.toString()

         binding.tvTotalPrice.text="Total Price  $"+totalAmount.toString()
             binding.tvYouPay.text="You Pay      $"+totalAmount.toString()
        binding.tvPaymentType.text="Payment Type"+paymentMode


        return binding.root
    }




}