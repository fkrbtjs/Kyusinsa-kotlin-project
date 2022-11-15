package com.yong.chap12appbar

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.yong.chap12appbar.databinding.ItemMainBinding

class CustomAdapter(val dataList: MutableList<DataVO>): RecyclerView.Adapter<CustomAdapter.CustomViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val binding = ItemMainBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        val customViewHolder = CustomViewHolder(binding)

        //아이템 항목에 롱클릭 했을때 삭제
        customViewHolder.itemView.setOnLongClickListener {
            val position: Int = customViewHolder.bindingAdapterPosition
            val dataVO = dataList.get(position)
            (parent.context as MainActivity).oneFragment.refreshRecyclerViewDrop(dataVO)
            true
        }
        return customViewHolder
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        val binding = (holder as CustomViewHolder).binding
        val dataVO = dataList.get(position)
        binding.ivPicture.setImageResource(dataVO.picture)
        binding.tvName.text = dataVO.storeName
        binding.tvAge.text = dataVO.itemInfo
        binding.tvPrice.text = dataVO.price

        holder.itemView.setOnClickListener {
            val intent= Intent(holder.itemView?.context,ItemInformation::class.java)
            intent.putExtra("storeName",dataVO.storeName)
            intent.putExtra("itemInfo",dataVO.itemInfo)
            intent.putExtra("price",dataVO.price)
            intent.putExtra("picture",dataVO.picture.toString())
            ContextCompat.startActivity(holder.itemView.context,intent,null)
        }
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    class CustomViewHolder(val binding : ItemMainBinding): RecyclerView.ViewHolder(binding.root)
}