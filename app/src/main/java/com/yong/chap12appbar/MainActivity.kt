package com.yong.chap12appbar

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.tabs.TabLayoutMediator
import com.yong.chap12appbar.databinding.ActivityMainBinding
import com.yong.chap12appbar.databinding.UsertabButtonBinding

class MainActivity : AppCompatActivity() {

    lateinit var toggle: ActionBarDrawerToggle
    lateinit var binding: ActivityMainBinding
    lateinit var oneFragment: OneFragment
    lateinit var twoFragment: TwoFragment
    lateinit var threeFragment: ThreeFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        /** navigation view */
        //액션바 대신에 툴바를 넣는다
        
        setSupportActionBar(binding.toolbar)
        //액션바 드로우 토글 버튼 적용
        toggle = ActionBarDrawerToggle(this,binding.drawerLayout,R.string.drawer_open,R.string.drawer_close)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        
        //토글 동기화 처리
        toggle.syncState()
        /** drawerLayout- fragment */
        val pageAdapter = PageAdapter(this)
        val title = mutableListOf<String>("Top", "Bottom", "Outer")

        oneFragment = OneFragment()
        twoFragment = TwoFragment()
        threeFragment = ThreeFragment()

        pageAdapter.addFragment(oneFragment, title[0])
        pageAdapter.addFragment(twoFragment, title[1])
        pageAdapter.addFragment(threeFragment, title[2])

        binding.viewPager.adapter = pageAdapter
        //탭 레이아웃과 뷰페이저 연결!
        TabLayoutMediator(binding.tabLayout, binding.viewPager) {tab,position ->
            tab.setCustomView(createTabView(title[position]))
        }.attach()

        binding.navigationview.setNavigationItemSelectedListener {
            when(it.itemId){
                R.id.navi_menu_purchase->{
                    var date = intent.getStringExtra("purdate")
                    var storeName =intent.getStringExtra("purStoreName")
                    var itemInfo = intent.getStringExtra("purItemInfo")
                    var price = intent.getStringExtra("purPrice")
                    var picture = intent.getIntExtra("purPicture",0)
                    Log.d("data","${picture}")
                    val intent = Intent(this,PurchaseActivity::class.java)
                    intent.putExtra("purdate2",date)
                    intent.putExtra("purStoreName2",storeName)
                    intent.putExtra("purItemInfo2",itemInfo)
                    intent.putExtra("purPrice2", price)
                    intent.putExtra("purPicture2",picture)
                    startActivity(intent)
                    finish()
                    }
                R.id.navi_menu_favor->{Toast.makeText(applicationContext,"즐겨찾기",Toast.LENGTH_SHORT).show()}
                R.id.navi_menu_review->{Toast.makeText(applicationContext,"My리뷰",Toast.LENGTH_SHORT).show()}
                R.id.navi_menu_serviceCenter->{Toast.makeText(applicationContext,"고객센터",Toast.LENGTH_SHORT).show()}
            }
            true
        }
    }

    private fun createTabView(title: String): View {
        val useTabBinding = UsertabButtonBinding.inflate(layoutInflater)
        useTabBinding.tvTabName.text = title
        when(title) {
            "Top" ->{ useTabBinding.ivTabLogo.setImageResource(R.drawable.topcloth) }
            "Bottom" ->{ useTabBinding.ivTabLogo.setImageResource(R.drawable.bottmcloth) }
            "Outer" ->{ useTabBinding.ivTabLogo.setImageResource(R.drawable.outercloth) }
        }
        return useTabBinding.root
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
       
        //토글버튼에서 이벤트가 발생하면 처리
        if (toggle.onOptionsItemSelected(item)) return true
        when(item.itemId){
            R.id.menu_save->{Toast.makeText(applicationContext,"저장",Toast.LENGTH_SHORT).show()}
            R.id.menu_search->{Toast.makeText(applicationContext,"검색",Toast.LENGTH_SHORT).show()}
            R.id.menu_load->{Toast.makeText(applicationContext,"불러오기",Toast.LENGTH_SHORT).show()}
            R.id.menu_open->{Toast.makeText(applicationContext,"열기",Toast.LENGTH_SHORT).show()}
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        menuInflater.inflate(R.menu.menu_main,menu)
        val menuItem = menu?.findItem(R.id.menu_search)
        val searchView = menuItem?.actionView as SearchView
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(searchText: String?): Boolean {
                Log.d("chap12appBar","${searchText}")
                return true
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                return true
            }
        })
        return super.onCreateOptionsMenu(menu)
    }
}