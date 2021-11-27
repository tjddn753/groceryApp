package com.example.projectone.homescreen.view

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.RequestQueue
import com.android.volley.toolbox.Volley
import com.example.projectone.Category
import com.example.projectone.CategoryResponse
import com.example.projectone.databinding.FragmentCategoryBinding
import com.example.projectone.homescreen.Communicator
import com.example.projectone.homescreen.Util
import com.example.projectone.homescreen.Util.Companion.getHomeContext
import com.example.projectone.homescreen.adapter.CategoryAdapter
import com.example.projectone.homescreen.presenter.HomescreenPresenter


class CategoryFragment : Fragment(),Homescreen {


    lateinit var binding:FragmentCategoryBinding
    lateinit var homescreenPresenter: HomescreenPresenter
    lateinit var queue: RequestQueue
    lateinit var categoryList:List<Category>
    lateinit var adapter: CategoryAdapter
    private lateinit var communicator: Communicator

    //val context = activity as AppCompatActivity
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentCategoryBinding.inflate(inflater, container, false)
        queue = Volley.newRequestQueue(getHomeContext())
        homescreenPresenter = HomescreenPresenter(this, queue)
        homescreenPresenter.getdataFromAPI()
   //     inflater.inflate(R.layout.fragment_category, container, false)
        return  binding.root
    }


    override fun onSuccess(response: CategoryResponse) {
        Toast.makeText(getHomeContext(),response.error.toString(), Toast.LENGTH_LONG).show()
    }

    override fun onError(error: String) {
        Toast.makeText(getHomeContext(),"Error is : ${error.toString()}", Toast.LENGTH_LONG).show()
    }

    override fun onHaveData(response: CategoryResponse) {
        categoryList=response.data
        adapter= CategoryAdapter(categoryList)
        adapter.setOnCategorySelectedListener {
                category, position ->
            communicator = activity as Communicator
            communicator.toSubCategory()   //trasition to fragment category

            Util.catId= category.catId.toString()
            Util.catImage="https://rjtmobile.com/grocery/images/"+category.catImage

            Toast.makeText(getHomeContext(),"Selected+${Util.catId}", Toast.LENGTH_LONG).show()

        }
        binding.rvCategory.layoutManager= LinearLayoutManager(getHomeContext())
        binding.rvCategory.adapter=adapter

    }
}