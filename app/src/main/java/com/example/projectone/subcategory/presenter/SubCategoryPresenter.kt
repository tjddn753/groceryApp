package com.example.projectone.subcategory.presenter

import com.android.volley.RequestQueue
import com.example.projectone.homescreen.view.Homescreen
import com.example.projectone.homescreen.model.HomescreenModel
import com.example.projectone.homescreen.view.CategoryFragment
import com.example.projectone.subcategory.view.SubCategoryView

class SubCategoryPresenter(val view: SubCategoryView, val queue: RequestQueue) {

    val SubCategoryModel = com.example.projectone.subcategory.model.SubCategoryModel()
    fun getdataFromAPI() {
        SubCategoryModel.getDataFromAPI(
            {
                view.onSuccess(it)

            },
            {
                view.onError(it)
            },
            {
                view.onHaveData(it)
            },
            queue
        )
    }
}