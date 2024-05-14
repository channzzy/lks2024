package com.example.lksmarket.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.lksmarket.MainActivity
import com.example.lksmarket.R
import com.example.lksmarket.response.DataItem

class InvoiceAdapter(val data : List<DataItem?>?) : RecyclerView.Adapter<InvoiceAdapter.MyViewHolder>() {
    class MyViewHolder(view : View) : RecyclerView.ViewHolder(view) {
        var number = view.findViewById<TextView>(R.id.txt_number)
        var qty = view.findViewById<TextView>(R.id.txt_qty);
        var total = view.findViewById<TextView>(R.id.txt_total)
        var button = view.findViewById<Button>(R.id.btn_invoice_detail)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.item_invoice,parent,false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        if(data != null){
            return data.size
        }
        return 0
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.number.text = data?.get(position)?.invoiceNum.toString()
        holder.qty.text = data?.get(position)?.qtyTotal.toString()
        holder.total.text = data?.get(position)?.priceTotal.toString()
        holder.button.setOnClickListener {
            if(data?.get(position)?.invoiceNum != null){
                val intent = Intent(holder.itemView.context,MainActivity::class.java)
                intent.putExtra("numInvoice", data?.get(position)?.invoiceNum )
                holder.itemView.context.startActivity(intent)
            }
        }
    }
}