package com.yong.chap12appbar

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.yong.chap12appbar.databinding.ActivityItemInformationBinding
import java.text.SimpleDateFormat

class ItemInformation : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityItemInformationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.tvStoreName.text = intent.getStringExtra("storeName")
        binding.tvItemInfo.text = intent.getStringExtra("itemInfo")
        binding.tvPrice.text = intent.getStringExtra("price")
        binding.ivCloth.setImageResource(intent.getStringExtra("picture")!!.toInt())
        val picture = intent.getStringExtra("picture")!!.toInt()
        var favor = false

        binding.ivFavor.setOnClickListener {
            if (!favor) {
                binding.ivFavor.setImageResource(R.drawable.fillheart)
                Toast.makeText(this,"즐겨찾기에 추가하였습니다.",Toast.LENGTH_SHORT).show()
                favor = true
            }else{
                binding.ivFavor.setImageResource(R.drawable.heart)
                Toast.makeText(this,"즐겨찾기에 삭제하였습니다.",Toast.LENGTH_SHORT).show()
                favor = false
            }
        }

        binding.btnPurchase.setOnClickListener {
            Toast.makeText(this,"상품을 구매하였습니다.",Toast.LENGTH_SHORT).show()
            val intent = Intent(this,MainActivity::class.java)
            val currentTime : Long = System.currentTimeMillis()
            val dataFormat = SimpleDateFormat("yyyy.MM.dd")
            var now = dataFormat.format(currentTime)

            intent.putExtra("purdate",now.toString())
            intent.putExtra("purStoreName",binding.tvStoreName.text.toString())
            intent.putExtra("purItemInfo",binding.tvItemInfo.text.toString())
            intent.putExtra("purPrice", binding.tvPrice.text.toString())
            intent.putExtra("purPicture",picture)
            startActivity(intent)
            finish()
        }
    }
}