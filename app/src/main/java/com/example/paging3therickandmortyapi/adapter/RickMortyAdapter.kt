package com.example.paging3therickandmortyapi.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.paging3therickandmortyapi.database.RickMortyEntity
import com.example.paging3therickandmortyapi.databinding.ItemRvBinding
import com.squareup.picasso.Picasso

class RickMortyAdapter:ListAdapter<RickMortyEntity,RickMortyAdapter.ViewHolder>(MyDiffUtil()) {

    class MyDiffUtil:DiffUtil.ItemCallback<RickMortyEntity>(){
        override fun areItemsTheSame(oldItem: RickMortyEntity, newItem: RickMortyEntity): Boolean {
            return oldItem.id == newItem.id
        }

        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(oldItem: RickMortyEntity, newItem: RickMortyEntity): Boolean {
            return oldItem == newItem
        }
    }

    inner class ViewHolder(val itemRvBinding: ItemRvBinding):RecyclerView.ViewHolder(itemRvBinding.root){
        fun onBind(rickMortyEntity:RickMortyEntity){
            Picasso.get().load(rickMortyEntity.image).into(itemRvBinding.itemImage)
            itemRvBinding.apply {
                itemName.text = rickMortyEntity.name
                itemStatus.text = rickMortyEntity.status
                itemSpecies.text = rickMortyEntity.species
                locationNameTv.text = rickMortyEntity.location
                firstSeenInTv.text = rickMortyEntity.episode

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemRvBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(getItem(position))
    }
}