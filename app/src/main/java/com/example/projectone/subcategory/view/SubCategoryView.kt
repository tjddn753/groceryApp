package com.example.projectone.subcategory.view


interface SubCategoryView {
    fun onSuccess(response: SubCategoryReseponse)
    fun onError(error:String)
    fun onHaveData(response: SubCategoryReseponse)

}