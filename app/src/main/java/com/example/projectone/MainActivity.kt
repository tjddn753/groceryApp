package com.example.projectone


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.RequestQueue
import com.android.volley.toolbox.Volley
import com.example.projectone.databinding.ActivityMainBinding
import com.example.projectone.homescreen.adapter.CategoryAdapter
import com.example.projectone.homescreen.presenter.HomescreenPresenter
import com.example.projectone.homescreen.view.Homescreen


class MainActivity : AppCompatActivity(),Homescreen {
    lateinit var  binding: ActivityMainBinding
    lateinit var homescreenPresenter: HomescreenPresenter
    lateinit var queue: RequestQueue
    lateinit var categoryList:List<Category>
    lateinit var adapter: CategoryAdapter
    //    lateinit var actionBarToggle: ActionBarDrawerToggle
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //   actionBarToggle = ActionBarDrawerToggle(this.binding.drawerLayout,0,binding.toolbar)
//        binding.drawerLayout.addDrawerListener(actionBarToggle)
        //      actionBarToggle.setHomeAsUpIndicator(R.drawable.ic_baseline_home_24)
        queue = Volley.newRequestQueue(this)
        homescreenPresenter = HomescreenPresenter(this, queue)

        homescreenPresenter.getdataFromAPI()

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
        Toast.makeText(this,response.error.toString(), Toast.LENGTH_LONG).show()
    }

    override fun onError(error: String) {
        Toast.makeText(this,"Error is : ${error.toString()}", Toast.LENGTH_LONG).show()
    }

    override fun onHaveData(response: CategoryResponse) {
        categoryList=response.data
        adapter= CategoryAdapter(categoryList)
        adapter.setOnCategorySelectedListener {
                category, position ->
        //    communicator = activity as Communicator
          //  communicator.toCategory()   //trasition to fragment category


            Util.catId= category.catId.toString()
            Toast.makeText(this,"Selected+${Util.catId}", Toast.LENGTH_LONG).show()

        }
        binding.rvCategory.layoutManager= LinearLayoutManager(this)
        binding.rvCategory.adapter=adapter

    }
}
/*

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import com.example.projectone.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    private lateinit var actionBarToggle: ActionBarDrawerToggle

   // @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

     */
/*   actionBarToggle = ActionBarDrawerToggle(this, binding.drawerLayout, 0, 0)
        binding.drawerLayout.addDrawerListener(actionBarToggle)*//*


        */
/*actionBarToggle.isDrawerIndicatorEnabled = false
        binding.toolbar.setNavigationIcon(R.drawable.ic_baseline_home_24)
        binding.toolbar.setNavigationOnClickListener {
            if (binding.drawerLayout.isDrawerOpen(GravityCompat.START)) {
                binding.drawerLayout.closeDrawer(GravityCompat.START)
            } else {
                binding.drawerLayout.openDrawer(GravityCompat.START)
            }
        }*//*


     //   actionBarToggle.syncState()

        binding.navView.setNavigationItemSelectedListener {
                menuItem ->
            menuItem.isCheckable

            binding.drawerLayout.closeDrawers()
            when (menuItem.itemId) {
                R.id.profile -> {
                    Toast.makeText(this, "I am Profile", Toast.LENGTH_SHORT).show()
                }
                R.id.setting -> {
                    Toast.makeText(this, "I am Setting", Toast.LENGTH_SHORT).show()
                }
                R.id.logout -> {
                    Toast.makeText(this, "I am Logout", Toast.LENGTH_SHORT).show()
                }
            }
        //
            true
        }
    }
}*/
