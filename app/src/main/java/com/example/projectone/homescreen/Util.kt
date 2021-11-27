package com.example.projectone.homescreen

import android.annotation.SuppressLint
import android.content.Context


class Util {
    companion object{
      const val Tag="First"
      lateinit var catId:String
      lateinit var catImage:String

      @SuppressLint("StaticFieldLeak")
      private lateinit var homeContext: Context


      fun setHomeContext(context: Context){
          homeContext=context
      }

        fun getHomeContext():Context = homeContext



    }
}
