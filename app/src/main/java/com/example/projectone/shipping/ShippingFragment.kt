package com.example.projectone.shipping

import ShippingAddress
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.projectone.Util
import com.example.projectone.databinding.FragmentShippingBinding
import com.example.projectone.homescreen.Communicator


class ShippingFragment : Fragment() {


lateinit var binding:FragmentShippingBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        binding=FragmentShippingBinding.inflate(inflater,container,false)
        binding.btnConfirm.setOnClickListener {
            var communicator: Communicator = activity as Communicator
            communicator.toCart()

        val streetName =binding.etStreetName.text.toString()
        var pinCode =binding.etPincode.text.toString()
        val IntPincode =Integer.parseInt(pinCode)?:1

        val city=binding.etCity.text.toString()
        val houseNo=binding.etHouseNo.text.toString()
        val type =binding.etType.text.toString()

        Util.shippingAddress=ShippingAddress(city,houseNo,IntPincode,streetName,type)


    }

        return binding.root
    }


}