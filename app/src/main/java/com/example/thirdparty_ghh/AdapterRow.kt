package com.example.thirdparty_ghh

import android.app.Activity
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.thirdparty_ghh.databinding.ItemBinding


class AdapterRow(var list: ArrayList<Games>, private val activity: Activity): RecyclerView.Adapter<AdapterRow.HolderAdapter>(){
    class HolderAdapter(val binding: ItemBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HolderAdapter {
        return HolderAdapter(ItemBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: HolderAdapter, position: Int) {
        val item = list[position]
        holder.binding.apply {
            Glide.with(activity).load(item.URL).into(mainIv)
        }

        holder.binding.mainIv.setOnClickListener {
            (activity as MainActivity).toDetails(item)
        }
    }

    override fun getItemCount() = list.size

    fun updateRecyclerView(newList: ArrayList<Games>){
        this.list = newList
        notifyDataSetChanged()
    }


}