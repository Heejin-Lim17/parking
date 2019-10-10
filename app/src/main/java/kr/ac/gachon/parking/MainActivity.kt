package kr.ac.gachon.parking

import android.content.Intent
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.navigation.NavigationView
import androidx.core.view.GravityCompat
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.ViewGroup
import com.naver.maps.map.MapView
import com.naver.maps.map.NaverMap
import com.naver.maps.map.NaverMapSdk
import com.naver.maps.map.overlay.Marker
import com.naver.maps.map.overlay.OverlayImage
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import kr.ac.gachon.parking.Group.GroupActivity
import kr.ac.gachon.parking.Customer.LoginActivity
import kr.ac.gachon.parking.Customer.MyInfoActivity
import kr.ac.gachon.parking.Info.DisabledInfo
import kr.ac.gachon.parking.Info.HolidayInfo
import kr.ac.gachon.parking.ParkingFunction.ParkingFunction


class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        NaverMapSdk.getInstance(this).client = NaverMapSdk.NaverCloudPlatformClient("cc86tt11qz")

        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        //floating action button
        fab.setOnClickListener {
            val func_intent=Intent(this, ParkingFunction::class.java)
            startActivity(func_intent)
        }

        val toggle = ActionBarDrawerToggle(
            this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close
        )
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)

        //지도 띄우기
        val mapView = MapView(this)
        val mapViewContainer = findViewById(R.id.map_view) as ViewGroup
        mapViewContainer.addView(mapView)

        //지도에 현재 위치 표시
//        val marker = Marker()
//        val naverMap=new NaverMap(this)
//        marker.map = naverMap
//        val locationOverlay = naverMap.locationOverlay
//        locationOverlay.isVisible = true
//        locationOverlay.icon = OverlayImage.fromResource(R.drawable.ic_my_location_black_24dp)


    }



    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        when (item.itemId) {
            R.id.action_settings -> return true
            else -> return super.onOptionsItemSelected(item)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.nav_groups-> {
                val group_intent=Intent(this, GroupActivity::class.java)
                startActivity(group_intent)
            }
            R.id.nav_my_group -> {

            }
            R.id.nav_dis_info -> {
                val dis_info_intent=Intent(this, DisabledInfo::class.java)
                startActivity(dis_info_intent)

            }
            R.id.nav_off_info -> {
                val holiday_info_intent=Intent(this, HolidayInfo::class.java)
                startActivity(holiday_info_intent)

            }
            R.id.nav_mem_info -> {
                val myinfo_intent=Intent(this, MyInfoActivity::class.java)
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

