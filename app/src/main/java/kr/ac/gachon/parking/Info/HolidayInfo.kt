package kr.ac.gachon.parking.Info

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.ActionBarDrawerToggle
import com.naver.maps.geometry.LatLng
import com.naver.maps.map.*
import com.naver.maps.map.overlay.InfoWindow
import com.naver.maps.map.overlay.Marker
import com.naver.maps.map.overlay.Overlay
import com.naver.maps.map.util.FusedLocationSource
import com.naver.maps.map.util.MarkerIcons
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import kr.ac.gachon.parking.Data.GetData
import kr.ac.gachon.parking.R

class HolidayInfo : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var locationSource3: FusedLocationSource
    private var mapView3: MapView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        NaverMapSdk.getInstance(this).client = NaverMapSdk.NaverCloudPlatformClient("cc86tt11qz")
        setContentView(R.layout.activity_holiday_info)

        mapView3 = findViewById(R.id.holiday_map)
        mapView3!!.getMapAsync(this)

        locationSource3 = FusedLocationSource(this, HolidayInfo.LOCATION_PERMISSION_REQUEST_CODE)


    }

    //Locationing
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        if (locationSource3.onRequestPermissionsResult(
                requestCode, permissions, grantResults
            )
        ) {
            return;
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    companion object {
        private const val LOCATION_PERMISSION_REQUEST_CODE = 1000
    }

    /* MapView */
    override fun onMapReady(naverMap: NaverMap) {
        for (i in GetData.mArrayList.indices) {
            var lat = GetData.mArrayList.get(i).get_lat()
            var lng = GetData.mArrayList.get(i).get_lng()

            if(GetData.mArrayList.get(i).saturday_fee_devision.equals("무료")) {
                var markera = Marker()
                markera.position = LatLng(lat!!.toDouble(), lng!!.toDouble())
                markera.map = naverMap
                markera.icon = MarkerIcons.BLACK
                markera.iconTintColor = Color.MAGENTA

                naverMap.mapType = NaverMap.MapType.Navi
                naverMap.setLayerGroupEnabled(NaverMap.LAYER_GROUP_TRAFFIC, true)

                //정보창 설정
                val infoWindow = InfoWindow()
                infoWindow.adapter = object : InfoWindow.DefaultTextAdapter(baseContext) {

                    var gadd = GetData.mArrayList.get(i).addr
                    var gclosetime=GetData.mArrayList.get(i).holiday_close_time


                    val getinfo="토요일/공휴일 무료 주차장입니다."+"\n"+"닫는 시간: "+gclosetime+'\n'+"주소:"+gadd


                    override fun getText(infoWindow: InfoWindow):CharSequence {
                        Log.d("check",getinfo)
                        return getinfo
                    }
                }

                markera.tag = "마커 1"
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
                markera.onClickListener = listener

            }

        }
        naverMap.locationSource = locationSource3
        naverMap.locationTrackingMode = LocationTrackingMode.Follow
        naverMap.addOnLocationChangeListener { location ->
        }
    }

}