package com.yong.chap12appbar

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.yong.chap12appbar.databinding.ActivityPurchaseBinding

class PurchaseActivity : AppCompatActivity() {
    
    var purchaseList = mutableListOf<DataPurchase>()
    lateinit var purchaseAdapter: PurchaseAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityPurchaseBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var date : String = intent.getStringExtra("purdate2").toString()
        var storeName : String =intent.getStringExtra("purStoreName2").toString()
        var itemInfo : String = intent.getStringExtra("purItemInfo2").toString()
        var price : String = intent.getStringExtra("purPrice2").toString()
        var picture = intent.getIntExtra("purPicture2",0)

        purchaseList.add(DataPurchase(date, storeName , itemInfo, price,picture))
        
        val linearLayoutManager = LinearLayoutManager(this)
        purchaseAdapter = PurchaseAdapter(purchaseList)
        binding.purRecyclerview.layoutManager = linearLayoutManager
        binding.purRecyclerview.adapter = purchaseAdapter
    }

    fun refreshRecyclerViewDrop(dataPurchase: DataPurchase) {
        purchaseList.remove(dataPurchase)
        purchaseAdapter.notifyDataSetChanged()
    }
}