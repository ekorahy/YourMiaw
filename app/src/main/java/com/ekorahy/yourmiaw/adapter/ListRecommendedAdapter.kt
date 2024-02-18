package com.ekorahy.yourmiaw.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ekorahy.yourmiaw.model.cat.Cat
import com.ekorahy.yourmiaw.databinding.ItemCatBinding
import com.ekorahy.yourmiaw.view.detail.cat.DetailCatActivity
import com.ekorahy.yourmiaw.view.detail.cat.DetailCatActivity.Companion.EXTRA_DATA_CAT

class ListRecommendedAdapter(private val listRecommended: ArrayList<Cat>) :
    RecyclerView.Adapter<ListRecommendedAdapter.ListViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ListViewHolder {
        val binding = ItemCatBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val (name, photo, price, category, desc) = listRecommended[position]
        holder.binding.tvName.text = name
        holder.binding.ivPhoto.setImageResource(photo)
        holder.binding.tvPrice.text = price.toString()
        holder.itemView.setOnClickListener {
            val cat = Cat(
                name,
                photo,
                price,
                category,
                desc
            )
            val intent = Intent(holder.itemView.context, DetailCatActivity::class.java)
            intent.putExtra(EXTRA_DATA_CAT, cat)
            holder.itemView.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int = listRecommended.size

    class ListViewHolder(var binding: ItemCatBinding) : RecyclerView.ViewHolder(binding.root)
}