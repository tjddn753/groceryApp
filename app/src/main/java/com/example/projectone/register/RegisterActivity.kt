package com.example.projectone.register

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.VolleyError
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.projectone.databinding.ActivityRegisterBinding
import org.json.JSONObject
import java.net.URLEncoder

class RegisterActivity : AppCompatActivity() {
    lateinit var binding: ActivityRegisterBinding
    lateinit var queue: RequestQueue

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        queue= Volley.newRequestQueue(this)

        binding.btnSignup.setOnClickListener {
            register()

        }
    }

    private fun register() {
        val firstName =URLEncoder.encode(binding.etFirstname.text.toString(),"UTF-8")
  //      val lastName =URLEncoder.encode(binding.etLastname.text.toString(),"UTF-8")
  //      val address =URLEncoder.encode(binding.etAddress.text.toString(),"UTF-8")
        val mobile =URLEncoder.encode(binding.etMobileNumber.text.toString(),"UTF-8")
        val email =URLEncoder.encode(binding.etEmail.text.toString(),"UTF-8")
        val password =URLEncoder.encode(binding.etPassword.text.toString(),"UTF-8")

     //   val requestData ="firstName=$firstName&mobile=$mobile&password=$password&email=$email"
        val url ="https://grocery-second-app.herokuapp.com/api/auth/register"


        val regiterData=JSONObject().apply {
            put("firstName",firstName)
            put("mobile",mobile)
            put("password",password)
            put("email",email)

        }

        val request = JsonObjectRequest(
           Request.Method.POST,
            url,
            regiterData,
            { response: JSONObject ->
          //
                val status = response.getInt("status")
                val message = response.getString("message")
                Toast.makeText(baseContext, message, Toast.LENGTH_LONG).show()
            },
            { error: VolleyError ->
                Log.e("error",error.toString())
                Toast.makeText(baseContext, "Error is ${error.toString()}", Toast.LENGTH_LONG)
                    .show()
            }
        )
        /* val sharedPreferences: SharedPreferences = this.getSharedPreferences(
                   "B33shareference",
                   Context.MODE_PRIVATE
               )
               val editor: SharedPreferences.Editor = sharedPreferences.edit()
               editor.putString("name", name)
               editor.putString("password", password)
               editor.commit()
               Toast.makeText(this,"Saved successfully",Toast.LENGTH_SHORT).show()*/
       /* val request =object:StringRequest(
            Method.POST,
            url,
            {
            response: String->
                binding.pbProcessing.visibility= View.GONE
                val json=JSONObject(response)
                val status =json.getInt("error")
                val message =json.getString("message")
                Toast.makeText(baseContext,message,Toast.LENGTH_LONG).show()
                startActivity(Intent(baseContext,MainActivity::class.java))
            },
            {
                error: VolleyError->
                error.printStackTrace()
                binding.pbProcessing.visibility= View.GONE
                Log.e("error",error.toString())
                Toast.makeText(baseContext,"Error is ${error}",Toast.LENGTH_LONG).show()

            }
        )*/
     /*   {
            override fun getBody(): ByteArray {
                return requestData.toByteArray()
            }
        }*/
        queue.add(request)
      //  binding.pbProcessing.visibility=View.VISIBLE
    }
}