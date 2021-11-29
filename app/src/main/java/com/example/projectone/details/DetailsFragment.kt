package com.example.projectone.subcategory

import Details
import DetailsResponse
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
import com.example.projectone.database.DatabaseHandler
import com.example.projectone.subcategory.adapter.CartAdapter
import com.example.projectone.subcategory.adapter.DetailsAdapter
import com.example.projectone.subcategory.presenter.DetailsPresenter
import com.example.projectone.subcategory.view.DetailsView


class DetailsFragment : Fragment(),DetailsView {


    lateinit var binding: FragmentSubCategoryBinding
    lateinit var detailsPresenter:DetailsPresenter
    lateinit var queue: RequestQueue
    lateinit var DetailsList:List<Details>
    lateinit var adapter: DetailsAdapter
    private lateinit var communicator: Communicator
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSubCategoryBinding.inflate(inflater, container, false)
        queue = Volley.newRequestQueue(Util.getHomeContext())
        detailsPresenter = DetailsPresenter(this, queue)
        detailsPresenter.getdataFromAPI()
        //     inflater.inflate(R.layout.fragment_category, container, false)
        return  binding.root
    }

    override fun onSuccess(response: DetailsResponse) {
        Toast.makeText(Util.getHomeContext(),response.error.toString(), Toast.LENGTH_LONG).show()
    }

    override fun onError(error: String) {
        Toast.makeText(Util.getHomeContext(),"Error is : ${error.toString()}", Toast.LENGTH_LONG).show()
    }

    override fun onHaveData(response: DetailsResponse) {
        DetailsList= response.data
        var communicator:Communicator= activity as Communicator
        adapter= DetailsAdapter(DetailsList,communicator)

        adapter.setOnSubCategorySelectedListener  {
                details, position ->



     //       Toast.makeText(Util.getHomeContext(),"Selected+${Util.catId}", Toast.LENGTH_LONG).show()

        }
        binding.rvCategory.layoutManager= LinearLayoutManager(Util.getHomeContext())
        binding.rvCategory.adapter=adapter


    }
}