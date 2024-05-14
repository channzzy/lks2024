package com.example.lksmarket

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.lksmarket.api.ApiConfig
import com.example.lksmarket.api.ApiService
import com.example.lksmarket.request.RegisterRequest
import com.example.lksmarket.response.RegisterResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterActivity : AppCompatActivity() {
    var nama : EditText? = null
    var Alamat : EditText? = null
    var Username : EditText? = null
    var password : EditText? = null
    var kfPassword : EditText? =null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        var btnRegister = findViewById<Button>(R.id.btn_daftar)
        var btnHaveAccount = findViewById<TextView>(R.id.txt_haveacoount)
        nama = findViewById(R.id.txt_name_regist)
        Alamat = findViewById(R.id.txt_alamat_regist)
        Username = findViewById(R.id.txt_username_regist)
        password = findViewById(R.id.txt_password_regist)
        kfPassword = findViewById(R.id.txt_kf_password_regist)
        btnHaveAccount.setOnClickListener {
            var intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        btnRegister.setOnClickListener {
            register();
        }
    }
    fun register(){
        if(nama?.text.toString() == "") {
            Toast.makeText(this, "Nama tidak boleh kosong", Toast.LENGTH_SHORT).show()
            return
        }
        if(Alamat?.text.toString() == ""){
            Toast.makeText(this, "Alamat tidak boleh kosong", Toast.LENGTH_SHORT).show()
            return
        }
        if(Username?.text.toString() == ""){
            Toast.makeText(this, "Username tidak boleh kosong", Toast.LENGTH_SHORT).show()
            return
        }
        if(password?.text.toString() == ""){
            Toast.makeText(this, "Password tidak boleh kosong", Toast.LENGTH_SHORT).show()
            return
        }
        if(kfPassword?.text.toString() == ""){
            Toast.makeText(this, "Konfirmasi password tidak boleh kosong", Toast.LENGTH_SHORT).show()
            return
        }
        if(kfPassword?.text.toString() != password?.text.toString()){
            Toast.makeText(this, "Password tidak sesuai", Toast.LENGTH_SHORT).show()
            return
        }
        var registerRequest = RegisterRequest(nama?.text.toString(),Alamat?.text.toString(),Username?.text.toString(),password?.text.toString(),kfPassword?.text.toString())
        ApiConfig.getService().register(registerRequest).enqueue(object : Callback<RegisterResponse>{
            override fun onResponse(
                call: Call<RegisterResponse>,
                response: Response<RegisterResponse>
            ) {
                println(response.code())
                if(response.code() == 201){
                    Toast.makeText(this@RegisterActivity, "Register berhasil dilakukan", Toast.LENGTH_SHORT).show()
                    var intent = Intent(this@RegisterActivity,MainActivity::class.java)
                    startActivity(intent)
                }else if(response.code() == 422){
                    Toast.makeText(this@RegisterActivity, "Register gagal dilakukan", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<RegisterResponse>, t: Throwable) {
                println(t.localizedMessage)
            }

        })
    }
}