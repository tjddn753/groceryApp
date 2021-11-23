package com.example.projectone.homescreen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.view.GravityCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.projectone.Category
import com.example.projectone.CategoryResponse
import com.example.projectone.R
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

        binding.btnMenu.setOnClickListener {
            if (binding.drawerLayout.isDrawerOpen(GravityCompat.START)) {
                binding.drawerLayout.closeDrawer(GravityCompat.START)
            } else {
                binding.drawerLayout.openDrawer(GravityCompat.START)
            }
        }

        binding.navView.setNavigationItemSelectedListener {
                menuItem->
            menuItem.isCheckable
            binding.drawerLayout.closeDrawers()
            when(menuItem.itemId){
                R.id.profile->{
                    Toast.makeText(this,"I am Profile",Toast.LENGTH_SHORT).show()

                }
                R.id.setting->{
                    Toast.makeText(this,"I am Setting",Toast.LENGTH_SHORT).show()

                }
                R.id.logout->{
                    Toast.makeText(this,"I am Logout",Toast.LENGTH_SHORT).show()

                }

            }
            //       binding.drawerLayout.closeDrawer(binding.navView)
            true
        }


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