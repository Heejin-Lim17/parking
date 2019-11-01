package kr.ac.gachon.parking

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import kr.ac.gachon.parking.Customer.LoginActivity
import kr.ac.gachon.parking.Customer.MyInfoActivity
import kr.ac.gachon.parking.Group.MainGroup.GroupActivity
import kr.ac.gachon.parking.Group.MyGroup.MyGroupActivity
import kr.ac.gachon.parking.Info.DisabledInfo
import kr.ac.gachon.parking.Info.HolidayInfo

class NavigationDrawer:AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener{

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setSupportActionBar(toolbar)
    }

    //뒤로가기 눌렀을 때
    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }
    //메뉴 입력해놓은거 가져오기
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }
    //메뉴 설정
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        when (item.itemId) {
            R.id.action_settings -> return true
            else -> return super.onOptionsItemSelected(item)
        }
    }
    //네비게이션 드로어 클릭했을 때 페이지 넘어가는 것
    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.nav_groups-> {
                val group_intent= Intent(this, GroupActivity::class.java)
                startActivity(group_intent)
            }
            R.id.nav_my_group -> {
                val my_group_intent= Intent(this, MyGroupActivity::class.java)
                startActivity(my_group_intent)
            }
            R.id.nav_dis_info -> {
                val dis_info_intent= Intent(this, DisabledInfo::class.java)
                startActivity(dis_info_intent)

            }
            R.id.nav_off_info -> {
                val holiday_info_intent= Intent(this, HolidayInfo::class.java)
                startActivity(holiday_info_intent)

            }
            R.id.nav_mem_info -> {
                val myinfo_intent= Intent(this, MyInfoActivity::class.java)
                startActivity(myinfo_intent)
            }
            R.id.nav_login -> {
                val login_intent= Intent(this, LoginActivity::class.java)
                startActivity(login_intent)
            }
        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }
}