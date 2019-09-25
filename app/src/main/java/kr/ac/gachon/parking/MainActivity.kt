package kr.ac.gachon.parking

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import kotlinx.android.synthetic.main.app_bar_navigation_drawer.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.app_bar_navigation_drawer)

        setSupportActionBar(toolbar)

// 2. 툴바 왼쪽 버튼 설정
        supportActionBar!!.setDisplayHomeAsUpEnabled(true) // 왼쪽 버튼 사용 여부 true
        supportActionBar!!.setHomeAsUpIndicator(R.drawable.ic_toc_black_24dp) // 왼쪽 버튼 아이콘 설정
        supportActionBar!!.setDisplayShowTitleEnabled(false) // 타이틀 안보이게 하기

    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.activity_navigation_drawer_drawer, menu) // main_menu 메뉴를 toolbar 메뉴 버튼으로 설정
        return true
    }
}
