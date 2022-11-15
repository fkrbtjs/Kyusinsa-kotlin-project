package com.yong.chap12appbar

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.yong.chap12appbar.databinding.FragmentOneBinding

class OneFragment : Fragment() {
    lateinit var binding: FragmentOneBinding
    lateinit var customAdapter: CustomAdapter
    var dataList = mutableListOf<DataVO>()
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentOneBinding.inflate(inflater, container, false)

        //이벤트 처리부분
        //리사이클러뷰에 데이타리스트 제작한다
        for (i in 0..29) {
            dataList.add(DataVO(randomName(1), randomName(2),randomName(3), randomPicture()))
        }
        
        //리사이클러뷰에 보여줄 레이아웃 결정
        val linearLayoutManager : LinearLayoutManager = selectLayoutManager(1)
        
        //리사이클러뷰에 제공할 어댑터
        customAdapter = CustomAdapter(dataList)
       
        //리사이클러뷰에 연결
        binding.recyclerView.layoutManager = linearLayoutManager
        binding.recyclerView.adapter = customAdapter
       
        //데코레이션 여기서 연결
//        binding.recyclerView.addItemDecoration(MyDecoration(binding.root.context))

        //플로팅탭을 누르면 사용자 다이얼로그 보여줘서 입력한 데이터를 dataList에 추가한다.
        //사용자 다이얼로그 창을 만들어서 처리해야한다! dataList 추가해야한다
       binding.f1Floating.setOnClickListener {
           binding.recyclerView.smoothScrollToPosition(0)
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
            val stores = arrayOf("토피","스파오","라퍼지스토어","규신사 스탠다드","드로우핏","커버낫","예일","그루브라임","제멋","노스페이스","파르티멘토","수아레"
            ,"엘무드")
            val storeName : String = stores[(Math.random() * stores.size).toInt()]
            return storeName
        }else if (i==2){
            val items = arrayOf("워셔블 하찌 니트","베이식 긴팔 티셔츠","모크넥 니트 티셔츠","릴렉스 핏 크루넥 반팔티","하프 터틀넥 니트","헤비코튼 오버 럭비 맨투맨","에센셜 후드 스웻셔츠"
            ,"TONE ARCH HOODIE","SIGN LOGO HOODIE","RETRIEVER CREWNECK","옥스포드 셔츠","램스울 크루텍 오버니트")
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
        val clothes = arrayOf(R.drawable.top1,R.drawable.top2,R.drawable.top3,R.drawable.top4,R.drawable.top5,R.drawable.top6,R.drawable.top7
            ,R.drawable.top8,R.drawable.top9,R.drawable.top10)
        val ranPicture = clothes[(Math.random() * clothes.size).toInt()]
        return ranPicture
    }
}