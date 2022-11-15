package com.yong.chap12appbar

import android.app.Dialog
import android.content.Context
import android.view.LayoutInflater
import android.view.WindowManager
import com.yong.chap12appbar.databinding.DialogCustomBinding
import com.yong.chap12appbar.databinding.FragmentOneBinding

class CustomDialog(val context: Context, val oneFragmentBinding: FragmentOneBinding){
    
    val dialog = Dialog(context)

    fun showDialog(){
        val binding = DialogCustomBinding.inflate(LayoutInflater.from(context))
        dialog.setContentView(binding.root)
        dialog.window!!.setLayout(WindowManager.LayoutParams.WRAP_CONTENT,WindowManager.LayoutParams.WRAP_CONTENT)
        dialog.setCanceledOnTouchOutside(false)
        dialog.setCancelable(false)
        dialog.show()

        //취소버튼 이벤트
        binding.btnCancle.setOnClickListener {
            dialog.dismiss()
        }
    }
}