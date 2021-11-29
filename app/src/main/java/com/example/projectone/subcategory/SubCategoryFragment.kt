package com.example.projectone.subcategory

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.RequestQueue
import com.android.volley.toolbox.Volley
import com.example.projectone.databinding.FragmentSubCategoryBinding
import com.example.projectone.homescreen.Communicator
import com.example.projectone.Util
import com.example.projectone.subcategory.adapter.SubCategoryAdapter
import com.example.projectone.subcategory.presenter.SubCategoryPresenter
import com.example.projectone.subcategory.view.SubCategory
import com.example.projectone.subcategory.view.SubCategoryReseponse
import com.example.projectone.subcategory.view.SubCategoryView


class SubCategoryFragment : Fragment(),SubCategoryView {


    lateinit var binding: FragmentSubCategoryBinding
    lateinit var subCategoryPresenter: SubCategoryPresenter
    lateinit var queue: RequestQueue
    lateinit var subCategoryList:List<SubCategory>
    lateinit var adapter: SubCategoryAdapter
    private lateinit var communicator: Communicator
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSubCategoryBinding.inflate(inflater, container, false)
        queue = Volley.newRequestQueue(Util.getHomeContext())
        subCategoryPresenter = SubCategoryPresenter(this, queue)
        subCategoryPresenter.getdataFromAPI()
        //     inflater.inflate(R.layout.fragment_category, container, false)
        return  binding.root
    }

    override fun onSuccess(response: SubCategoryReseponse) {
        Toast.makeText(Util.getHomeContext(),response.error.toString(), Toast.LENGTH_LONG).show()
    }

    override fun onError(error: String) {
        Toast.makeText(Util.getHomeContext(),"Error is : ${error.toString()}", Toast.LENGTH_LONG).show()
    }

    override fun onHaveData(response: SubCategoryReseponse) {
        subCategoryList= response.data
        adapter= SubCategoryAdapter(subCategoryList)
        adapter.setOnSubCategorySelectedListener  {
                category, position ->
            communicator = activity as Communicator

            communicator.toDetails()   //trasition to fragment category

            Util.subCatId= category.subId.toString()
      //      Util.catImage="https://rjtmobile.com/grocery/images/"+category.catImage

            Toast.makeText(Util.getHomeContext(),"Selected+${Util.catId}", Toast.LENGTH_LONG).show()

        }
        binding.rvCategory.layoutManager= LinearLayoutManager(Util.getHomeContext())
        binding.rvCategory.adapter=adapter

    }


}