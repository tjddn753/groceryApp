package com.example.projectone

import android.annotation.SuppressLint
import android.content.Context
import com.example.projectone.homescreen.Communicator


class Util {
    companion object{
      const val Tag="First"
     var catId:String="0"

      var subCatId:String="0"
      lateinit var catImage:String
 //      var communicator: Communicator =activity as Communicator
      @SuppressLint("StaticFieldLeak")
      private lateinit var homeContext: Context


      fun setHomeContext(context: Context){
          homeContext =context
      }

        fun getHomeContext():Context = homeContext



    }
}
