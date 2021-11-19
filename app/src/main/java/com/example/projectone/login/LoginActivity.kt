package com.example.projectone.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.VolleyError
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.projectone.databinding.ActivityLoginBinding
import com.example.projectone.register.RegisterActivity
import org.json.JSONObject
import java.net.URLEncoder
import java.util.*

class LoginActivity : AppCompatActivity() {

    lateinit var binding:ActivityLoginBinding
    lateinit var queue: RequestQueue

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityLoginBinding.inflate(layoutInflater)

        setContentView(binding.root)
        queue= Volley.newRequestQueue(this)

        binding.btnSignin.setOnClickListener {
            Signin()

        }
        binding.btnBacktoSignup.setOnClickListener {
            startActivity(Intent(this,RegisterActivity::class.java))
        }
    }

    private fun Signin() {

        val email =binding.etEmail.text.toString()
        val password =binding.etPassword.text.toString()

        //   val requestData ="firstName=$firstName&mobile=$mobile&password=$password&email=$email"
        val url ="https://grocery-second-app.herokuapp.com/api/auth/login"


        val loginData= JSONObject().apply {

            put("password",password)
            put("email",email)

        }

        val request = JsonObjectRequest(
            Request.Method.POST,
            url,
            loginData,
            {
                //
                //  val status = response.getInt("status")
                val error=it.getBoolean("error")
                val message = it.getString("message")

                    Toast.makeText(baseContext, "Login Successfully", Toast.LENGTH_LONG).show()



            },
            { error: VolleyError ->
                Log.e("error",error.toString())
                Toast.makeText(baseContext, "Error is ${error}", Toast.LENGTH_LONG)
                    .show()
            }
        )
        queue.add(request)
    }
}