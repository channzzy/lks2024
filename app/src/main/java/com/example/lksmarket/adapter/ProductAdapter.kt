package com.example.lksmarket.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.lksmarket.MainActivity
import com.example.lksmarket.R
import com.example.lksmarket.response.ProductsItem

class ProductAdapter(val products : List<ProductsItem?>?,  val listener: OnProductChangedListener) : RecyclerView.Adapter<ProductAdapter.MyViewHolder>() {
    interface OnProductChangedListener {
        fun onProductAdded(price: Double)
        fun onProductRemoved(price: Double)
    }

    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var image = view.findViewById<ImageView>(R.id.imgv)
        var name = view.findViewById<TextView>(R.id.txt_name)
        var price = view.findViewById<TextView>(R.id.txt_price);
        var rating = view.findViewById<TextView>(R.id.txt_rating)
        var addButton = view.findViewById<ImageButton>(R.id.increment_qty)
        var minusButton = view.findViewById<ImageButton>(R.id.decrement_qty)
        var qty = view.findViewById<TextView>(R.id.txt_qty)
        var cartButton = view.findViewById<ImageButton>(R.id.btn_cart)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.item_product, parent, false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        if (products != null) {
            return products.size
        }
        return 0
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.name.text = products?.get(position)?.title
        holder.price.text = "Rp ${products?.get(position)?.price.toString()}"
        holder.rating.text = products?.get(position)?.rating.toString()

        Glide.with(holder.itemView.context)
            .load(products?.get(position)?.thumbnail)
            .error(R.drawable.ic_launcher_background)
            .into(holder.image)

        holder.addButton.setOnClickListener {
            val qty = holder.qty.text.toString().toInt() + 1
            holder.qty.text = qty.toString()
            products?.get(position)?.price?.let { price ->
                listener.onProductAdded(price)
            }
        }

        holder.minusButton.setOnClickListener {
            val currentQty = holder.qty.text.toString().toInt()
            if (currentQty > 0) {
                holder.qty.text = (currentQty - 1).toString()
                products?.get(position)?.price?.let { price ->
                    listener.onProductRemoved(price)
                }
            }
        }
    }
}