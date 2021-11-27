package com.example.projectone.homescreen.presenter

import com.android.volley.RequestQueue
import com.example.projectone.homescreen.view.Homescreen
import com.example.projectone.homescreen.model.HomescreenModel
import com.example.projectone.homescreen.view.CategoryFragment

class HomescreenPresenter(val view: Homescreen, val queue: RequestQueue) {

    val homescreenModel = HomescreenModel()
    fun getdataFromAPI() {
        homescreenModel.getDataFromAPI(
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