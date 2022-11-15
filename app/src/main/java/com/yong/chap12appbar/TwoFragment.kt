package com.yong.chap12appbar

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.yong.chap12appbar.databinding.FragmentTwoBinding

class TwoFragment : Fragment() {
    lateinit var binding: FragmentTwoBinding
    lateinit var customAdapter: CustomAdapter
    var dataList = mutableListOf<DataVO>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTwoBinding.inflate(inflater, container, false)

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
        binding.recyclerView2.layoutManager = linearLayoutManager
        binding.recyclerView2.adapter = customAdapter
       
        //데코레이션 여기서 연결
//        binding.recyclerView.addItemDecoration(MyDecoration(binding.root.context))

        binding.f2Floating.setOnClickListener {
            binding.recyclerView2.smoothScrollToPosition(0)
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
            val stores = arrayOf("제로","유니언즈","브렌슨","규신사 스탠다드","후러브스아트","토피","굿라이프웍스","낫포너드","어널러코드","브랜디드","오더아카이브")
            val storeName : String = stores[(Math.random() * stores.size).toInt()]
            return storeName
        }else if (i==2){
            val items = arrayOf("원턱 와이드 스웨트 팬츠","와이드핏 트레이닝 스웨트 팬츠","테이퍼드 히든 밴딩 슬랙스","세미 와이드 밴딩 슬랙스","와이드 데님 팬츠","루즈핏 트레이닝 팬츠",
            "레플리카 퍼티그 팬츠","이지 와이드 데님 팬츠","Classic Sweat Pants","사계절 스트링 트레이닝팬츠","1931 Object Jeans")
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
        val clothes = arrayOf(R.drawable.bottom1,R.drawable.bottom2,R.drawable.bottom3,R.drawable.bottom4,R.drawable.bottom5,R.drawable.bottom6,R.drawable.bottom7
            ,R.drawable.bottom8,R.drawable.bottom9,R.drawable.bottom10)
        val ranPicture = clothes[(Math.random() * clothes.size).toInt()]
        return ranPicture
    }
}