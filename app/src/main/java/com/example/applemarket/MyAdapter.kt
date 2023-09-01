package com.example.applemarket

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.applemarket.databinding.ItemBinding
import java.text.DecimalFormat

class MyAdapter (val mItems: MutableList<MyItem>): RecyclerView.Adapter<MyAdapter.Holder>(){

    interface ItemClick{
        fun onClick(view: View, position: Int)
    }
    interface ItemLongClick {
        fun onLongClick(view: View, position: Int)
    }

    var itemClick : ItemClick? = null
    var itemLongClick :ItemLongClick? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType:Int): Holder{
        val binding = ItemBinding.inflate(LayoutInflater.from(parent.context), parent,false)
        return  Holder(binding)
    }

    override fun onBindViewHolder(holder:Holder, position:Int){
        holder.itemView.setOnClickListener{
            itemClick?.onClick(it, position)
        }
        holder.itemView.setOnLongClickListener() OnLongClickListener@{
            itemLongClick?.onLongClick(it, position)
            return@OnLongClickListener true
        }
        holder.itemImage.setImageResource(mItems[position].aImage)
        holder.name.text = mItems[position].aName
        holder.address.text = mItems[position].aAddress
        val price = mItems[position].aPrice
        holder.tvPrice.text = DecimalFormat("#,###").format(price)+"원"
        holder.tvComment.text = mItems[position].aComment.toString()
        holder.tvLike.text = mItems[position].aLike.toString()

        if(mItems[position].likeCount)
            holder.heart.setImageResource(R.drawable.heart2)
        else
            holder.heart.setImageResource(R.drawable.heart)
    }

    override  fun getItemId(position: Int): Long{
        return position.toLong()
    }

    override fun getItemCount():Int {
        return mItems.size
    }

    inner class Holder(binding: ItemBinding) : RecyclerView.ViewHolder(binding.root){
        val itemImage = binding.image
        val name = binding.name
        val address = binding.address
        val tvPrice = binding.price
        val tvComment = binding.count1
        val tvLike = binding.count2
        val heart = binding.like
    }
}