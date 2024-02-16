package com.ekorahy.yourmiaw.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ekorahy.yourmiaw.model.cat.Cat
import com.ekorahy.yourmiaw.R
import com.ekorahy.yourmiaw.view.detail.cat.DetailCatActivity

class ListAllMiawAdapter(private val listAllMiaw: ArrayList<Cat>) :
    RecyclerView.Adapter<ListAllMiawAdapter.ListViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ListViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_cat, parent, false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val (name, photo, price, category, desc) = listAllMiaw[position]
        holder.tvName.text = name
        holder.ivPhoto.setImageResource(photo)
        holder.tvPrice.text = price.toString()
        holder.itemView.setOnClickListener {
            val cat = Cat(
                name,
                photo,
                price,
                category,
                desc
            )
            val intent = Intent(holder.itemView.context, DetailCatActivity::class.java)
            intent.putExtra(DetailCatActivity.EXTRA_DATA_CAT, cat)
            holder.itemView.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int = listAllMiaw.size

    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val ivPhoto: ImageView = itemView.findViewById(R.id.iv_photo)
        val tvName: TextView = itemView.findViewById(R.id.tv_name)
        val tvPrice: TextView = itemView.findViewById(R.id.tv_price)
    }
}