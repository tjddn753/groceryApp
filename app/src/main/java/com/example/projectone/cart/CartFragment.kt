package com.example.projectone.cart

import Details
import OrderSummary
import Orders
import Payment
import Product
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.VolleyError
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.projectone.R
import com.example.projectone.Util
import com.example.projectone.database.DatabaseHandler
import com.example.projectone.databinding.FragmentCartBinding
import com.example.projectone.databinding.FragmentSubCategoryBinding
import com.example.projectone.homescreen.Communicator
import com.example.projectone.orders.OrderView
import com.example.projectone.subcategory.adapter.CartAdapter
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import org.json.JSONArray
import org.json.JSONObject


class CartFragment : Fragment() {
    lateinit var queue: RequestQueue
    lateinit var binding: FragmentCartBinding
    lateinit var adapter: CartAdapter
    var products = ArrayList<Product>()
    var orderSummary = OrderSummary(0, 0, 0, 0)
    var payment = Payment("null")

    // var order=Orders(orderSummary,payment,products, Util.shippingAddress,Util.userId)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentCartBinding.inflate(inflater, container, false)

        val databaseHandler = DatabaseHandler(Util.getHomeContext())
        val item: ArrayList<Details> = databaseHandler.getItems()
        var product: Product
        for (i in item.indices) {

            product = Product(item[i]._id, item[i].price, item[i].productName, item[i].quantity)
            //  if (this::products.isInitialized)
            //    {
            products.add(product)
            //     }

        }
        queue = Volley.newRequestQueue(Util.getHomeContext())
        var communicator: Communicator = activity as Communicator


        adapter = CartAdapter(item, communicator)
        binding.rvCategory.layoutManager = LinearLayoutManager(Util.getHomeContext())
        binding.rvCategory.adapter = adapter
        val total = databaseHandler.getTotal()
        binding.tvShowPrice.text = total.toString()
        //    if (this::orderSummary.isInitialized) {
        orderSummary = OrderSummary(0, 0, total, total)
        //   }

        // order=Orders(orderSummary,payment,products, Util.shippingAddress,Util.userId)

        binding.cbCard.setOnClickListener {
            payment = Payment("card")
        }
        binding.cbCash.setOnClickListener {
            payment = Payment("cash")
        }
        binding.btnCheckout.setOnClickListener {

            postAPI()
            //
        }
        return binding.root
    }


    private fun postAPI() {

        var communicator: Communicator = activity as Communicator
        val url = "https://grocery-second-app.herokuapp.com/api/orders"

        val mapper = jacksonObjectMapper()
        //      val morders = JSONObject(mapper.writeValueAsString(order))
        val morderSummary = JSONObject(mapper.writeValueAsString(orderSummary))
        val mpayment = JSONObject(mapper.writeValueAsString(payment))

        var mproducts: JSONObject

        val mshippingAddress = JSONObject(mapper.writeValueAsString(Util.shippingAddress))

        var array = JSONArray()

        for (i in products.indices) {
            mproducts = JSONObject(mapper.writeValueAsString(products[i]))
            array.put(mproducts)
            //

        }

        val Data = JSONObject().apply {

            //     put ("order")
            put("shippingAddress", mshippingAddress)
            put("payment", mpayment)
            put("userId", Util.userId)
            put("products", array)
            put("orderSummary", morderSummary)

        }

        /*    val Data1 = JSONObject().apply {
                put("orderSummary", orderSummary)
                put("payment", payment)
                put("products", products)
                put("shippingAddress", Util.shippingAddress)
                put("userId", Util.userId)

                put("deliveryCharges", orderSummary.deliveryCharges)
                put("discount", orderSummary.discount)
                put("ourPrice", orderSummary.ourPrice)
                put("totalAmount", orderSummary.totalAmount)

                put("paymentMode", payment.paymentMode)
                for (i in products.indices) {
                    put("id", products[i].id)
                    put("price", products[i].price)
                    put("productName", products[i].productName)
                    put("quantity", products[i].quantity)

                }
                put("city", Util.shippingAddress.city)
                put("houseNo", Util.shippingAddress.houseNo)
                put("pincode", Util.shippingAddress.pincode)
                put("streetName", Util.shippingAddress.streetName)
                put("type", Util.shippingAddress.type)




            }*/
        Log.i("Data", Data.toString())
        //   val orders=Orders(orderSummary,payment,products,Util.shippingAddress,Util.userId)
        val sharedPreferences: SharedPreferences? = this.activity?.getSharedPreferences(
            "ProjectOne",
            Context.MODE_PRIVATE
        )
        val sp = sharedPreferences?.edit()


        val request = JsonObjectRequest(
            Request.Method.POST,
            url,
            Data,
            {
                //
                //  val status = response.getInt("status")
                val error = it.getBoolean("error")
                Log.i("Data", error.toString())
                val message = it.getString("message")
                Log.i("Data", message)
                //
                val id = it.getJSONObject("data").getString("_id");
                val date = it.getJSONObject("data").getString("date");
                val streetName = it.getJSONObject("data").getJSONObject("shippingAddress")
                    .getString("streetName");
                val houseNo =
                    it.getJSONObject("data").getJSONObject("shippingAddress").getString("houseNo");
                val city =
                    it.getJSONObject("data").getJSONObject("shippingAddress").getString("city");

                val pincode =
                    it.getJSONObject("data").getJSONObject("shippingAddress").getInt("pincode");

                val totalAmount =
                    it.getJSONObject("data").getJSONObject("orderSummary").getInt("totalAmount");
                val paymentMode =
                    it.getJSONObject("data").getJSONObject("payment").getString("paymentMode");

                //
                sp?.putString("id", id)
                sp?.putString("date", date)
                sp?.putString("streetName", streetName)
                sp?.putString("houseNo", houseNo)
                sp?.putString("city", city)
                sp?.putInt("pincode", pincode)
                sp?.putInt("totalAmount", totalAmount)
                sp?.putString("paymentMode", paymentMode)
                sp?.commit()

                if (error) {
                    Toast.makeText(Util.getHomeContext(), message, Toast.LENGTH_LONG).show()

                } else {
                    Toast.makeText(Util.getHomeContext(), message, Toast.LENGTH_LONG).show()

                    communicator.toOrders()
                }


            },
            { error: VolleyError ->
                Log.e("error", error.toString())
                Toast.makeText(Util.getHomeContext(), "Error is ${error}", Toast.LENGTH_LONG)
                    .show()
            }
        )
        queue.add(request)
    }


}