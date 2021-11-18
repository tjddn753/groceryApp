package com.example.projectone

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.android.volley.RequestQueue
import com.android.volley.VolleyError
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.day20application.databinding.ActivityRegister1Binding
import org.json.JSONObject
import java.net.URLEncoder

class RegisterActivity : AppCompatActivity() {
    lateinit var binding: ActivityRegister1Binding
    lateinit var queue: RequestQueue

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityRegister1Binding.inflate(layoutInflater)
        setContentView(binding.root)
        queue= Volley.newRequestQueue(this)

        binding.btnRegister.setOnClickListener {
            register()

        }
    }

    private fun register() {
        val name =URLEncoder.encode(binding.etFullname.text.toString(),"UTF-8")
        val password =URLEncoder.encode(binding.etPassword.text.toString(),"UTF-8")
        val confirmPassword =URLEncoder.encode(binding.etConfirmPassword.text.toString(),"UTF-8")
        val mobileNo =URLEncoder.encode(binding.etMobileNumber.text.toString(),"UTF-8")
        val requestData ="name=$name&password=$password&mobile_no=$mobileNo"
        val url ="https://psmobitech.com/fcmdemo/api/index.php/User/register"

       /* val sharedPreferences: SharedPreferences = this.getSharedPreferences(
            "B33shareference",
            Context.MODE_PRIVATE
        )
        val editor: SharedPreferences.Editor = sharedPreferences.edit()
        editor.putString("name", name)
        editor.putString("password", password)
        editor.commit()
        Toast.makeText(this,"Saved successfully",Toast.LENGTH_SHORT).show()*/


        val request =object:StringRequest(
            Method.POST,
            url,
            {
            response: String->
                binding.pbProcessing.visibility= View.GONE
                val json=JSONObject(response)
                val status =json.getInt("status")
                val message =json.getString("message")
                Toast.makeText(baseContext,message,Toast.LENGTH_LONG).show()
                startActivity(Intent(baseContext,MainActivity::class.java))
            },
            {
                error: VolleyError->
                error.printStackTrace()
                binding.pbProcessing.visibility= View.GONE
                Toast.makeText(baseContext,"Error is ${error.toString()}",Toast.LENGTH_LONG).show()

            }
        )
        {
            override fun getBody(): ByteArray {
                return requestData.toByteArray()
            }
        }
        queue.add(request)
        binding.pbProcessing.visibility=View.VISIBLE
    }
}