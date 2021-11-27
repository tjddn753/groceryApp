package com.example.projectone.subcategory.model

import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.example.projectone.CategoryResponse
import com.example.projectone.homescreen.Util
import com.example.projectone.subcategory.view.SubCategoryReseponse
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class SubCategoryModel {
    fun getDataFromAPI(
        onSuccess: (SubCategoryReseponse) -> Unit,
        onError: (String) -> Unit,
        onHaveData:(SubCategoryReseponse)->Unit,
        queue: RequestQueue
    ){
        val request = StringRequest(
            Request.Method.GET,
            "https://grocery-second-app.herokuapp.com/api/subcategory/"+ Util.catId,
            { response:String->

                val gson= Gson()
                val typeInfo=object: TypeToken<SubCategoryReseponse>(){}
                val result: SubCategoryReseponse =gson.fromJson(response,typeInfo.type)
                if(result.error==false){
                    onHaveData(result)

                }else{
                    onSuccess(result)
                //
                }

            },{
                    onError(it.toString())
            //
            }
        )
        queue.add(request)
    }
}