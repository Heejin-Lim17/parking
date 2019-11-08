package kr.ac.gachon.parking

import android.content.Context
import android.content.Intent
import android.location.Address
import android.location.Geocoder
import android.os.Bundle
import android.util.Log
import android.view.*
import com.google.android.material.navigation.NavigationView
import androidx.core.view.GravityCompat
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat.startActivity
import com.naver.maps.geometry.LatLng
import com.naver.maps.map.*
import com.naver.maps.map.overlay.InfoWindow
import com.naver.maps.map.overlay.Marker
import com.naver.maps.map.overlay.Overlay
import com.naver.maps.map.util.FusedLocationSource
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import kr.ac.gachon.parking.Group.MainGroup.GroupActivity
import kr.ac.gachon.parking.Customer.LoginActivity
import kr.ac.gachon.parking.Customer.MyInfoActivity
//import kr.ac.gachon.parking.GetDataSeongnam.AddrArrayList
import kr.ac.gachon.parking.Group.MyGroup.MyGroupActivity
import kr.ac.gachon.parking.Info.DisabledInfo
import kr.ac.gachon.parking.Info.HolidayInfo
import kr.ac.gachon.parking.ParkingFunction.ParkingFunction
import java.io.IOException
import java.util.ArrayList

//internal var mJsonString: String = "" // static
//internal var mArrayList: ArrayList<SeoulData>? = ArrayList<SeoulData>() // 위도, 경도 저장할 배열

class MainActivity : AppCompatActivity(), OnMapReadyCallback, NavigationView.OnNavigationItemSelectedListener{

    //지도 변수
    private lateinit var locationSource: FusedLocationSource
    private var mapView: MapView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        //context = this // Geocoder에 넣을 context

        /* MapView 설정 */
        mapView = findViewById(R.id.map_view)
        mapView!!.getMapAsync(this)
        locationSource=FusedLocationSource(this,LOCATION_PERMISSION_REQUEST_CODE)


        //floating action button
        fab.setOnClickListener {
            val func_intent=Intent(this, ParkingFunction::class.java)
            startActivity(func_intent)
        }

        //toggle 버튼
        val toggle = ActionBarDrawerToggle(
            this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close
        )
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)

        /* DB에서 lat, lng 받아오기 */
        var task1 :GetData = GetData()
        task1.execute("http://$IP_ADDRESS/getjson_location0.php", "")

//        var task2 :GetDataSeongnam = GetDataSeongnam()
//        task2.execute("http://$IP_ADDRESS/getjson_location1.php", "")
//        //GetDataSeongnam.toLocation()
    }

    //Locationing
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        if(locationSource.onRequestPermissionsResult(
                requestCode,permissions,grantResults)){
            return;
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    companion object {
        private const val LOCATION_PERMISSION_REQUEST_CODE=1000
        private const val IP_ADDRESS = "192.168.43.65"
//        lateinit var geocoder :Geocoder
        lateinit var context : Context
    }


    /* MapView */
    override fun onMapReady(naverMap: NaverMap) {
        //복정 4호
        var B16 = Marker()
        B16.position = LatLng(37.454771, 127.129062)
        B16.map = naverMap

        //복정 3호
        var B98 = Marker()
        B98.position = LatLng(37.461919, 127.127245)
        B98.map = naverMap

        //태평4동
        var T47 = Marker()
        T47.position = LatLng(37.453306, 127.139095)
        T47.map = naverMap

        //중상3
        var N112 = Marker()
        N112.position = LatLng(37.447586, 127.140599)
        N112.map = naverMap

        //신흥 제7
        var S85= Marker()
        S85.position = LatLng(37.442021, 127.137034)
        S85.map = naverMap

        //신흥 제8
        var S255= Marker()
        S255.position = LatLng(37.451899, 127.149659)
        S255.map = naverMap

        //수상16
        var J190= Marker()
        J190.position = LatLng(37.443430, 127.130869)
        J190.map = naverMap

        //중상68,69
        var D69= Marker()
        D69.position = LatLng(37.428929, 127.134209)
        D69.map = naverMap

        //중상41
        var D474= Marker()
        D474.position = LatLng(37.434574, 127.168748)
        D474.map = naverMap

        //야탑동 제1
        var Y337= Marker()
        Y337.position = LatLng(37.408235, 127.154790)
        Y337.map = naverMap

        //중앙동 제1
        var J2= Marker()
        J2.position = LatLng(37.444491, 127.157181)
        J2.map = naverMap

        //중앙동 제3
        var H38= Marker()
        H38.position = LatLng(37.438898, 127.154279)
        H38.map = naverMap

        //중상60,63
        var D352= Marker()
        D352.position = LatLng(37.431869, 127.157261)
        D352.map = naverMap

//        class data {
//            private var addr:String? = null
//            private var basic_time:String? = null
//            private var basic_fee:String? = null
//            private var add_time:String? = null
//            private var add_fee:String? = null
//            fun set_addr(addr:String?) {this.addr = addr;}
//            fun set_basictime(basictime:String?) {this.basic_time = basictime;}
//            fun set_basicfee(basicfee:String?) {this.basic_fee = basicfee;}
//            fun set_addtime(addtime:String?) {this.add_time = addtime;}
//            fun set_addfee(addfee:String?) {this.add_fee = addfee;}
//        }
//
//        var sndata = data()
//        var snArrayList = ArrayList<data>()
//        sndata.set_addr("경기도 성남시 수정구 복정로20번길 16")
//        sndata.set_basictime("30")
//        sndata.set_basicfee("400")
//        sndata.set_addtime("10")
//        sndata.set_addfee("200")
//        snArrayList.add(sndata)



        val infoWindowB16=InfoWindow()
        infoWindowB16.adapter=object :InfoWindow.DefaultTextAdapter(baseContext){
            val getinfo="기본시간(분): "+ "30"+"    "+"기본요금(원): "+"400" +'\n'+"추가시간(분): "+"10"+"    "+"추가요금(원): "+"200"+'\n'+"주소: "+"경기도 성남시 수정구 복정로20번길 16"
            override fun getText(p0: InfoWindow): CharSequence {
                return getinfo
            }
        }
        val listener = Overlay.OnClickListener { overlay ->
            val marker = overlay as Marker
            if (marker.infoWindow == null) {
                // 현재 마커에 정보 창이 열려있지 않을 경우 엶
                infoWindowB16.open(marker)

            } else {
                // 이미 현재 마커에 정보 창이 열려있을 경우 닫음
                infoWindowB16.close()
            }
            true
        }

        B16.onClickListener=listener
        B98.onClickListener=listener
        N112.onClickListener=listener
        S85.onClickListener=listener
        S255.onClickListener=listener
        J190.onClickListener=listener
        D69.onClickListener=listener
        D474.onClickListener=listener
        Y337.onClickListener=listener
        J2.onClickListener=listener
        H38.onClickListener=listener
        D352.onClickListener=listener
        T47.onClickListener=listener

        /* 서울 마커 */
        for (i in GetData.mArrayList.indices) {
            var lat = GetData.mArrayList.get(i).get_lat()
            var lng = GetData.mArrayList.get(i).get_lng()
//            var info=GetData.mArrayList.get(i).

            var marker1 = Marker()
            marker1.position = LatLng(lat!!.toDouble(), lng!!.toDouble())
            marker1.map = naverMap

            naverMap.setLayerGroupEnabled(NaverMap.LAYER_GROUP_TRAFFIC, true)
            naverMap.locationSource=locationSource
            naverMap.locationTrackingMode=LocationTrackingMode.Follow
            naverMap.addOnLocationChangeListener { location->
            }

            val infoWindow = InfoWindow()
            infoWindow.adapter = object : InfoWindow.DefaultTextAdapter(baseContext) {
                var gadd = GetData.mArrayList.get(i).addr
                var gbasic_time= GetData.mArrayList.get(i).basic_time
                var gbasic_fee= GetData.mArrayList.get(i).basic_fee
                var gadd_time= GetData.mArrayList.get(i).add_time
                var gadd_fee= GetData.mArrayList.get(i).add_fee
                val getinfo="기본시간(분): "+ gbasic_time+"  "+"기본요금(원): "+gbasic_fee +'\n'+"추가시간(분): "+gadd_time+"  "+"추가요금(원): "+gadd_fee+'\n'+"주소: "+gadd

                override fun getText(infoWindow: InfoWindow):CharSequence {
                    Log.d("check", getinfo)
                    return getinfo
                }
            }
            marker1.tag = "마커 1"
            val listener = Overlay.OnClickListener { overlay ->
                val marker = overlay as Marker

                if (marker.infoWindow == null) {
                // 현재 마커에 정보 창이 열려있지 않을 경우 엶
                    infoWindow.open(marker)
                } else {
                    // 이미 현재 마커에 정보 창이 열려있을 경우 닫음
                    infoWindow.close()
                }
                true
            }
            marker1.onClickListener = listener
        }

////        /* 성남 마커 */
//        for (i in GetDataSeongnam.AddrArrayList.indices) {
//            var lat = GetDataSeongnam.AddrArrayList.get(i).get_lat()
//            var lng = GetDataSeongnam.AddrArrayList.get(i).get_lng()
////            var info=GetData.mArrayList.get(i).
//
//            var marker2 = Marker()
//            marker2.position = LatLng(lat!!.toDouble(), lng!!.toDouble())
//            marker2.map = naverMap
//
//            val infoWindow = InfoWindow()
//            marker2.tag = "마커 2"
//            val listener = Overlay.OnClickListener { overlay ->
//                val marker = overlay as Marker
//
//                if (marker.infoWindow == null) {
//                    // 현재 마커에 정보 창이 열려있지 않을 경우 엶
//                    infoWindow.open(marker)
//                } else {
//                    // 이미 현재 마커에 정보 창이 열려있을 경우 닫음
//                    infoWindow.close()
//                }
//                true
//            }
//            marker2.onClickListener = listener
//        }

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





