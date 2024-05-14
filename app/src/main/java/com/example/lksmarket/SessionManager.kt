package com.example.lksmarket

import android.content.Context
import android.content.SharedPreferences

class SessionManager(context : Context){
    var sharedPreferences : SharedPreferences? = null;
    var editor = sharedPreferences?.edit()

    var keyPref = "token"

    init{
        sharedPreferences = context.getSharedPreferences(keyPref, Context.MODE_PRIVATE)
        editor = sharedPreferences?.edit()
    }

    fun setToken(token : String){
        editor?.putString("auth",token)?.apply()
    }

    fun getToken() : String?{
        return sharedPreferences?.getString("auth",null)
    }
}