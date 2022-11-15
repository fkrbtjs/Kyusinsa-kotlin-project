package com.yong.chap12appbar

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.yong.chap12appbar.databinding.FragmentThreeBinding

class ThreeFragment : Fragment() {
    lateinit var binding: FragmentThreeBinding
    lateinit var customAdapter: CustomAdapter
    var dataList = mutableListOf<DataVO>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentThreeBinding.inflate(inflater, container, false)

        //이벤트 처리부분
        //리사이클러뷰에 데이타리스트 제작한다
        for (i in 0..29) {
            dataList.add(DataVO(randomName(1), randomName(2),randomName(3),randomPicture()))
        }
        
        //리사이클러뷰에 보여줄 레이아웃 결정
        val linearLayoutManager : LinearLayoutManager = selectLayoutManager(1)
       
        //리사이클러뷰에 제공할 어댑터
        customAdapter = CustomAdapter(dataList)
        
        //리사이클러뷰에 연결
        binding.recyclerView3.layoutManager = linearLayoutManager
        binding.recyclerView3.adapter = customAdapter
       
        //데코레이션 여기서 연결
//        binding.recyclerView.addItemDecoration(MyDecoration(binding.root.context))

        binding.f3Floating.setOnClickListener {
            binding.recyclerView3.smoothScrollToPosition(0)
        }
        return binding.root
    }
    fun refreshRecyclerViewAdd(dataVO: DataVO) {
        dataList.add(dataVO)
        customAdapter.notifyDataSetChanged()
    }

    fun refreshRecyclerViewDrop(dataVO: DataVO) {
        dataList.remove(dataVO)
        customAdapter.notifyDataSetChanged()
    }

    private fun selectLayoutManager(i: Int): LinearLayoutManager {
        lateinit var layoutManager:LinearLayoutManager
        if(i==1){
            layoutManager = GridLayoutManager(context, 2)
        }else if(i==2){
            layoutManager = LinearLayoutManager(context)
            layoutManager.orientation = LinearLayoutManager.HORIZONTAL
        }
        return layoutManager
    }

    fun randomName(i:Int): String {
        if(i == 1){
            val stores = arrayOf("토피","스파오","라퍼지스토어","규신사 스탠다드","도프제이슨","커버낫","예일","그루브라임","제멋","노스페이스","파르티멘토")
            val storeName : String = stores[(Math.random() * stores.size).toInt()]
            return storeName
        }else if (i==2){
            val items = arrayOf("스웻 후드집업","베이직 퍼플리스 집업","베이직 푸퍼","오버핏 블루종","베이식 블레이저","오리지널 피쉬테일","비건레더 싱글 자켓","플리스 집업자켓","퀄팅블랙자켓",
                "리치오버자켓 블랙","오버사이즈 보머 재킷","파스텔 푸퍼","브이넥 라이트 다운 베스트","필드자켓 블랙","스탠다드 후드 스웨트 집업")
            val iteminfo :String = items[(Math.random() * items.size).toInt()]
            return iteminfo
        }else if (i==3){
            val prices = arrayOf("39,000원","32,000원","49,000원","69,000원","82,000원","129,000원","35,000원","66,000원","75,000원","42,000원","25,000원","99,000원"
                ,"77,000원","55,000원","46,000원","33,000원","73,000원","155,000원")
            val ranPrices :String = prices[(Math.random() * prices.size).toInt()]
            return ranPrices
        }else{
            return ""
        }
    }

    fun randomPicture(): Int{
        val clothes = arrayOf(R.drawable.outer1,R.drawable.outer2,R.drawable.outer3,R.drawable.outer4,R.drawable.outer5,R.drawable.outer6,R.drawable.outer7
            ,R.drawable.outer8,R.drawable.outer9,R.drawable.outer10)
        val ranPicture = clothes[(Math.random() * clothes.size).toInt()]
        return ranPicture
    }
}