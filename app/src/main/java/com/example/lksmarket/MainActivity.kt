package com.example.lksmarket

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.lksmarket.api.ApiConfig
import com.example.lksmarket.request.LoginRequest
import com.example.lksmarket.response.LoginResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    var username : EditText? = null
    var password : EditText? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val btnRegister = findViewById<TextView>(R.id.btn_register)
        val btnLogin = findViewById<Button>(R.id.btn_login)
        username = findViewById(R.id.txt_username)
        password = findViewById(R.id.txt_password)
        var sessionManager = SessionManager(this)
        btnRegister.setOnClickListener {
            var intent = Intent(this,RegisterActivity::class.java)
            startActivity(intent)
        }
        btnLogin.setOnClickListener {
            login(sessionManager)
        }

    }
    fun login(sessionManager: SessionManager){
        if(username?.text.toString() == ""){
            Toast.makeText(this, "Username harap diisi", Toast.LENGTH_SHORT).show()
            return
        }
        if(password?.text.toString() == ""){
            Toast.makeText(this, "Password harap diisi", Toast.LENGTH_SHORT).show()
            return
        }
        var loginRequest = LoginRequest(username?.text.toString(),password?.text.toString())
        ApiConfig.getService().login(loginRequest).enqueue(object : Callback<LoginResponse>{
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                println(response.code())
                if(response.code() == 200){
                    if(response.body()?.data != null){
                        var token = response.body()?.data
                        sessionManager.setToken("Bearer ${token!!}")
                    }
                    Toast.makeText(this@MainActivity, "Login berhasil dilakukan", Toast.LENGTH_SHORT).show()
                    var intent = Intent(this@MainActivity,Navigation::class.java)
                    startActivity(intent)
                }else if(response.code() == 422){
                    Toast.makeText(this@MainActivity, "Login gagal dilakukan", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                println(t.localizedMessage)
            }

        })
    }
}