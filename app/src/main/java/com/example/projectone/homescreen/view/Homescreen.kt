package com.example.projectone.homescreen.view

import com.example.projectone.CategoryResponse

interface Homescreen {
    fun onSuccess(response: CategoryResponse)
    fun onError(error:String)
    fun onHaveData(response: CategoryResponse)

}