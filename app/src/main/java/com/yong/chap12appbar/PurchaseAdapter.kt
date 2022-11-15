package com.yong.chap12appbar

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.graphics.drawable.toDrawable
import androidx.recyclerview.widget.RecyclerView
import com.yong.chap12appbar.databinding.ItemPurchaseBinding

class PurchaseAdapter(val purchaseList: MutableList<DataPurchase>): RecyclerView.Adapter<PurchaseAdapter.PurchaseViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PurchaseViewHolder {
       
        val binding = ItemPurchaseBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        val purchaseViewHolder = PurchaseViewHolder(binding)

        //아이템 항목에 롱클릭 했을때 삭제
        purchaseViewHolder.itemView.setOnLongClickListener {
            val position: Int = purchaseViewHolder.bindingAdapterPosition
            val dataPurchase = purchaseList.get(position)
            (parent.context as PurchaseActivity).refreshRecyclerViewDrop(dataPurchase)
            true
        }
        return purchaseViewHolder
    }

    override fun onBindViewHolder(holder: PurchaseViewHolder, position: Int) {
        val binding = (holder as PurchaseViewHolder).binding
        val dataPurchase = purchaseList.get(position)

        binding.purDate.text = dataPurchase.date.toString()
        binding.purStoreName.text = dataPurchase.storeName
        binding.purIteminfo.text = dataPurchase.itemInfo
        binding.purPrice.text = dataPurchase.price
        binding.purPicture.setImageResource(dataPurchase.picture.toInt())
    }

    override fun getItemCount(): Int {
        return purchaseList.size
    }

    class PurchaseViewHolder(val binding : ItemPurchaseBinding): RecyclerView.ViewHolder(binding.root)
}