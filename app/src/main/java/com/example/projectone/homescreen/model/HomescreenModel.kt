package com.example.projectone.homescreen.model

import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.example.projectone.CategoryResponse
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class HomescreenModel {
    fun getDataFromAPI(
        onSuccess: (CategoryResponse) -> Unit,
        onError: (String) -> Unit,
        onHaveData:(CategoryResponse)->Unit,
        queue: RequestQueue
    ){
        val request = StringRequest(
            Request.Method.GET,
            "https://grocery-second-app.herokuapp.com/api/category",
            { response:String->

                val gson= Gson()
                val typeInfo=object: TypeToken<CategoryResponse>(){}
                val result: CategoryResponse =gson.fromJson(response,typeInfo.type)
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