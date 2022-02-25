package com.example.paging3therickandmortyapi.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.paging3therickandmortyapi.R
import com.example.paging3therickandmortyapi.databinding.ItemRvBinding
import com.example.paging3therickandmortyapi.model.Result
import com.squareup.picasso.Picasso

class PagingRvAdapter:
    PagingDataAdapter<Result,PagingRvAdapter.MyViewHolder>(MyDiffUtil()) {

    class MyDiffUtil:DiffUtil.ItemCallback<Result>(){
        override fun areItemsTheSame(oldItem: Result, newItem: Result): Boolean {
            return oldItem.id== newItem.id

        }

        override fun areContentsTheSame(oldItem: Result, newItem: Result): Boolean {
            return oldItem == newItem
        }
    }


    inner class MyViewHolder(var itemRvBinding: ItemRvBinding):RecyclerView.ViewHolder(itemRvBinding.root){
        fun onBind(result: Result){
            Picasso.get().load(result.image)
                .placeholder(R.drawable.ic_launcher_background)
                .into(itemRvBinding.itemImage)
            itemRvBinding.apply {
                itemName.text = result.name
                itemStatus.text = result.status
                itemSpecies.text = result.species
                locationNameTv.text = result.location.name
                firstSeenInTv.text = result.episode[0]

            }

        }
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        getItem(position)?.let {
            holder.onBind(it)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(ItemRvBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }
}