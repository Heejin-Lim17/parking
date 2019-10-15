


package kr.ac.gachon.parking

import android.content.Intent
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.annotation.NonNull
import androidx.annotation.UiThread
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.navigation.NavigationView
import androidx.core.view.GravityCompat
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat.startActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.google.android.gms.location.FusedLocationProviderClient
import com.naver.maps.geometry.LatLng
import com.naver.maps.map.*
import com.naver.maps.map.overlay.Marker
import com.naver.maps.map.overlay.OverlayImage
import com.naver.maps.map.util.FusedLocationSource
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import kr.ac.gachon.parking.Group.GroupActivity
import kr.ac.gachon.parking.Customer.LoginActivity
import kr.ac.gachon.parking.Customer.MyInfoActivity
import kr.ac.gachon.parking.Group.MyGroupActivity
import kr.ac.gachon.parking.Info.DisabledInfo
import kr.ac.gachon.parking.Info.HolidayInfo
import kr.ac.gachon.parking.ParkingFunction.ParkingFunction
import com.naver.maps.map.CameraUpdate.zoomTo





class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener{

    //private lateinit var locationSource: FusedLocationSource

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

//        locationSource=FusedLocationSource(this,LOCATION_PERMISSION_REQUEST_CODE)


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

//        NaverMapSdk.getInstance(this).client = NaverMapSdk.NaverCloudPlatformClient("cc86tt11qz")


        //지도 띄우기
//        val mapView = MapView(this)
//        val mapViewContainer = findViewById(R.id.map) as ViewGroup
//        mapViewContainer.addView(mapView)


    }

//    //Locationing
//    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
//        if(locationSource.onRequestPermissionsResult(
//                requestCode,permissions,grantResults)){
//            return;
//        }
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
//    }

//    override fun onMapReady(naverMap: NaverMap) {
//        naverMap.locationSource=locationSource
//        naverMap.locationTrackingMode=LocationTrackingMode.Follow
//        naverMap.addOnLocationChangeListener { location->
//        }
//    }

//    companion object {
//        private const val LOCATION_PERMISSION_REQUEST_CODE=1000
//    }


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
                val my_group_intent=Intent(this, MyGroupActivity::class.java)
                startActivity(my_group_intent)
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





