package com.example.projectone.homescreen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.projectone.Category
import com.example.projectone.CategoryResponse
import com.example.projectone.databinding.ActivityHomescreenBinding
import com.example.projectone.homescreen.adapter.CategoryAdapter
import com.example.projectone.homescreen.view.Homescreen
import com.example.projectone.homescreen.presenter.HomescreenPresenter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class HomescreenActivity : AppCompatActivity() , Homescreen {
    lateinit var binding: ActivityHomescreenBinding
    lateinit var homescreenPresenter: HomescreenPresenter
    lateinit var queue:RequestQueue
    lateinit var categoryList:List<Category>
    lateinit var adapter: CategoryAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityHomescreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
        queue= Volley.newRequestQueue(this)
        homescreenPresenter= HomescreenPresenter(this,queue)

        homescreenPresenter.getdataFromAPI()

  //      loadCategoryInfo()


    }


    override fun onSuccess(response: CategoryResponse) {
        Toast.makeText(baseContext,response.error.toString(), Toast.LENGTH_LONG).show()
    }

    override fun onError(error: String) {
        Toast.makeText(baseContext,"Error is : ${error.toString()}", Toast.LENGTH_LONG).show()
    }

    override fun onHaveData(response: CategoryResponse) {
        categoryList=response.data
        adapter= CategoryAdapter(categoryList)
        binding.rvCategory.layoutManager=LinearLayoutManager(baseContext)
        binding.rvCategory.adapter=adapter

    }


}