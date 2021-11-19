package com.example.projectone.homescreen.presenter

import com.android.volley.RequestQueue
import com.example.projectone.homescreen.view.Homescreen
import com.example.projectone.homescreen.model.HomescreenModel

class HomescreenPresenter(val homeScreenView: Homescreen, val queue: RequestQueue) {

    val homescreenModel = HomescreenModel()
    fun getdataFromAPI() {
        homescreenModel.getDataFromAPI(
            {
                homeScreenView.onSuccess(it)

            },
            {
                homeScreenView.onError(it)
            },
            {
                homeScreenView.onHaveData(it)
            },
            queue
        )
    }
}