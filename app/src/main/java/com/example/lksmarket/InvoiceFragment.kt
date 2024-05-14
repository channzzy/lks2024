package com.example.lksmarket

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lksmarket.adapter.InvoiceAdapter
import com.example.lksmarket.api.ApiConfig
import com.example.lksmarket.response.InvoiceResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class InvoiceFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var root : View =  inflater.inflate(R.layout.fragment_invoice, container, false)
        var recyleView = root.findViewById<RecyclerView>(R.id.rv_invoice)
        var sessionManager = SessionManager(root.context)
        var token = sessionManager.getToken()
        ApiConfig.getService().getInvoice(token!!).enqueue(object : Callback<InvoiceResponse>{
            override fun onResponse(
                call: Call<InvoiceResponse>,
                response: Response<InvoiceResponse>
            ) {
                println(response.code())
                if(response.code() == 200){
                    var response = response.body()
                    var dataInvoice = response?.data
                    var invoiceAdapter = InvoiceAdapter(dataInvoice)
                    recyleView.apply {
                        layoutManager = LinearLayoutManager(root.context)
                        adapter = invoiceAdapter;
                        setHasFixedSize(true)
                    }
                }
            }

            override fun onFailure(call: Call<InvoiceResponse>, t: Throwable) {
                print(t.localizedMessage)
            }

        })
        return root
    }

}