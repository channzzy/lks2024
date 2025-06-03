package com.example.lksmarket

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lksmarket.adapter.InvoiceAdapter
import com.example.lksmarket.adapter.ProductAdapter
import com.example.lksmarket.api.ApiConfig
import com.example.lksmarket.response.InvoiceResponse
import com.example.lksmarket.response.ProductResponse
import com.example.lksmarket.response.ProfileResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * A simple [Fragment] subclass.
 * Use the [MenuFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MenuFragment : Fragment(), ProductAdapter.OnProductChangedListener {
    private var total : Double = 0.0
    private lateinit var totalTextView: TextView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var root : View =  inflater.inflate(R.layout.fragment_menu, container, false)
        var recyleView = root.findViewById<RecyclerView>(R.id.rv_product)
        var sessionManager = SessionManager(root.context)
        var token = sessionManager.getToken()
        totalTextView = root.findViewById(R.id.tv_total_belanja)

        root.findViewById<Button>(R.id.btn_bayar).setOnClickListener {
            Toast.makeText(requireContext(), "Total bayar: Rp$total", Toast.LENGTH_SHORT).show()
        }

        ApiConfig.getService().getProducts(token!!).enqueue(object : Callback<ProductResponse> {
            override fun onResponse(
                call: Call<ProductResponse>,
                response: Response<ProductResponse>
            ) {
                println(response.code())
                println(response.body())
                if(response.code() == 200){
                    var response = response.body()
                    var dataInvoice = response?.products
                    var productAdapter = ProductAdapter(dataInvoice, this@MenuFragment)
                    recyleView.apply {
                        layoutManager = LinearLayoutManager(root.context)
                        adapter = productAdapter;
                        setHasFixedSize(true)
                    }
                }
            }

            override fun onFailure(call: Call<ProductResponse>, t: Throwable) {
                print(t.localizedMessage)
            }

        })
        return root
    }
    override fun onProductAdded(price: Double) {
        total += price
        updateTotal()
    }

    override fun onProductRemoved(price: Double) {
        total -= price
        if (total < 0) total = 0.0
        updateTotal()
    }

    private fun updateTotal() {
        totalTextView.text = "Total: Rp$total"
    }
}
