package com.example.projectone.homescreen


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import com.android.volley.RequestQueue
import com.example.projectone.R
import com.example.projectone.Util
import com.example.projectone.cart.CartFragment
import com.example.projectone.databinding.ActivityHomescreenBinding
import com.example.projectone.databinding.FragmentCategoryBinding
import com.example.projectone.homescreen.presenter.HomescreenPresenter
import com.example.projectone.homescreen.view.CategoryFragment
import com.example.projectone.subcategory.DetailsFragment
import com.example.projectone.subcategory.SubCategoryFragment


class HomescreenActivity : AppCompatActivity() ,Communicator {
    lateinit var binding: ActivityHomescreenBinding
    lateinit var bindingFrament:FragmentCategoryBinding
    lateinit var homescreenPresenter: HomescreenPresenter
    lateinit var queue:RequestQueue

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomescreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
    //    queue = Volley.newRequestQueue(this)
        Util.setHomeContext(this)
  //      homescreenPresenter = HomescreenPresenter(this, queue)
        addFragment(CategoryFragment())
    //    homescreenPresenter.getdataFromAPI()

        //      loadCategoryInfo()

        binding.btnMenu.setOnClickListener {
            if (binding.drawerLayout.isDrawerOpen(GravityCompat.START)) {
                binding.drawerLayout.closeDrawer(GravityCompat.START)
            } else {
                binding.drawerLayout.openDrawer(GravityCompat.START)
            }
        }
        binding.btnCart.setOnClickListener{
            toCart()
        }
        binding.btnHome.setOnClickListener {
            toHome()
        }

        binding.navView.setNavigationItemSelectedListener { menuItem ->
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
            //       binding.drawerLayout.closeDrawer(binding.navView)
            true
        }

    }
    private fun AppCompatActivity.replaceFragment(fragment: Fragment) {
            val manager = supportFragmentManager
            val transaction = manager.beginTransaction()
            transaction.replace(R.id.frame_container, fragment)
            transaction.addToBackStack(Util.Tag)
            transaction.commit()
        }
    private fun AppCompatActivity.addFragment(fragment: Fragment) {
        val manager = supportFragmentManager
        val transaction = manager.beginTransaction()
        transaction.add(R.id.frame_container, fragment)
        transaction.addToBackStack(Util.Tag)
        transaction.commit()
    }
    override fun toHome() {
        val manager = supportFragmentManager
        val transaction = manager.beginTransaction()
        transaction.replace(R.id.frame_container,CategoryFragment() )
        transaction.addToBackStack(Util.Tag)
        transaction.commit()
    }

    override fun toSubCategory() {
        val manager = supportFragmentManager
        val transaction = manager.beginTransaction()
        transaction.replace(R.id.frame_container,SubCategoryFragment() )
        transaction.addToBackStack(Util.Tag)
        transaction.commit()
    }

    override fun toDetails() {
        val manager = supportFragmentManager
        val transaction = manager.beginTransaction()
        transaction.replace(R.id.frame_container,DetailsFragment() )
        transaction.addToBackStack(Util.Tag)
        transaction.commit()
    }
    override fun toCart() {
        val manager = supportFragmentManager
        val transaction = manager.beginTransaction()
        transaction.replace(R.id.frame_container,CartFragment() )
        transaction.addToBackStack(Util.Tag)
        transaction.commit()
    }


}