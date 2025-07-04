package com.example.lksmarket

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.lksmarket.api.ApiConfig
import com.example.lksmarket.response.ProfileResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProfileFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var root : View = inflater.inflate(R.layout.fragment_profile, container, false)
        var name = root.findViewById<TextView>(R.id.name_user)
        var gender = root.findViewById<TextView>(R.id.gender)
        var telpon = root.findViewById<TextView>(R.id.telpon_user)
        var img = root.findViewById<ImageView>(R.id.img_user)
        var sessionManager = SessionManager(root.context)
        var token = sessionManager.getToken()
        println(token)
        ApiConfig.getService().getProfile(token!!).enqueue(object : Callback<ProfileResponse>{
            override fun onResponse(
                call: Call<ProfileResponse>,
                response: Response<ProfileResponse>
            ) {
                println(response.code())
                if(response.code() == 200){
                    name.text = "${response.body()?.firstName} ${response.body()?.lastName}"
                    gender.text = response.body()?.gender.toString().uppercase()
                    telpon.text = "123456789"
                    Glide.with(img)
                        .load(response.body()?.image)
                        .error(R.drawable.ic_launcher_background)
                        .into(img)
                }else{
                    Toast.makeText(root.context, "Ada sesuatu yang salah", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<ProfileResponse>, t: Throwable) {
                println(t.localizedMessage)
            }

        })
        return root;
    }
}