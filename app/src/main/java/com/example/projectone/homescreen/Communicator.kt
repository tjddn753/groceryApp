package com.example.projectone.homescreen

import android.content.SharedPreferences
import androidx.fragment.app.Fragment

interface Communicator {
     fun toHome()

     fun toSubCategory()
  //   fun getMVP()
     fun toDetails()

     fun toCart()

     fun toShipping()

     fun toOrders()

}