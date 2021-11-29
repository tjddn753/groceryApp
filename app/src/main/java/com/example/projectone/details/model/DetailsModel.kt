package com.example.projectone.subcategory.model

import DetailsResponse
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.example.projectone.Util
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class DetailsModel {
    fun getDataFromAPI(
        onSuccess: (DetailsResponse) -> Unit,
        onError: (String) -> Unit,
        onHaveData:(DetailsResponse)->Unit,
        queue: RequestQueue
    ){
        val request = StringRequest(
            Request.Method.GET,
            "https://grocery-second-app.herokuapp.com/api/products/sub/"+ Util.subCatId,
            { response:String->

                val gson= Gson()
                val typeInfo=object: TypeToken<DetailsResponse>(){}
                val result: DetailsResponse =gson.fromJson(response,typeInfo.type)
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