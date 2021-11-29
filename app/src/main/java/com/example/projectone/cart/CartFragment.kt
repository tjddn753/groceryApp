package com.example.projectone.cart

import Details
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.projectone.R
import com.example.projectone.Util
import com.example.projectone.database.DatabaseHandler
import com.example.projectone.databinding.FragmentCartBinding
import com.example.projectone.databinding.FragmentSubCategoryBinding
import com.example.projectone.homescreen.Communicator
import com.example.projectone.subcategory.adapter.CartAdapter


class CartFragment : Fragment() {

    lateinit var binding: FragmentCartBinding
    lateinit var adapter: CartAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentCartBinding.inflate(inflater, container, false)

        val databaseHandler = DatabaseHandler(Util.getHomeContext())
        val item: List<Details> = databaseHandler.getItems()
        var communicator:Communicator= activity as Communicator


        adapter= CartAdapter(item,communicator)
        binding.rvCategory.layoutManager= LinearLayoutManager(Util.getHomeContext())
        binding.rvCategory.adapter=adapter

        return binding.root
    }



}