package com.example.projectone.subcategory.presenter

import com.android.volley.RequestQueue
import com.example.projectone.subcategory.model.DetailsModel
import com.example.projectone.subcategory.view.DetailsView


class DetailsPresenter(val view: DetailsView, val queue: RequestQueue) {

    val SubCategoryModel = DetailsModel()
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