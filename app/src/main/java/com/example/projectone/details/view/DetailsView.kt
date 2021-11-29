package com.example.projectone.subcategory.view

import DetailsResponse


interface DetailsView {
    fun onSuccess(response: DetailsResponse)
    fun onError(error:String)
    fun onHaveData(response: DetailsResponse)

}