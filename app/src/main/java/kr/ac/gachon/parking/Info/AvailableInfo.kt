package kr.ac.gachon.parking.Info

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.naver.maps.geometry.LatLng
import com.naver.maps.map.*
import com.naver.maps.map.overlay.InfoWindow
import com.naver.maps.map.overlay.Marker
import com.naver.maps.map.overlay.Overlay
import com.naver.maps.map.util.FusedLocationSource
import com.naver.maps.map.util.MarkerIcons
import kr.ac.gachon.parking.Data.GetData
import kr.ac.gachon.parking.R

class AvailableInfo: AppCompatActivity(), OnMapReadyCallback {

    private lateinit var locationSource2: FusedLocationSource
    private var mapView2: MapView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        NaverMapSdk.getInstance(this).client = NaverMapSdk.NaverCloudPlatformClient("cc86tt11qz")
        setContentView(R.layout.activity_disabled_info)

        mapView2 = findViewById(R.id.dis_map)
        mapView2!!.getMapAsync(this)

        locationSource2= FusedLocationSource(this, LOCATION_PERMISSION_REQUEST_CODE)

    }

    //Locationing
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        if(locationSource2.onRequestPermissionsResult(
                requestCode,permissions,grantResults)){
            return;
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    companion object {
        private const val LOCATION_PERMISSION_REQUEST_CODE=1000
    }

    /* MapView */
    override fun onMapReady(naverMap: NaverMap) {
        for (i in GetData.mArrayList.indices) {
            var lat = GetData.mArrayList.get(i).get_lat()
            var lng = GetData.mArrayList.get(i).get_lng()

            var marker1 = Marker()
            marker1.position = LatLng(lat!!.toDouble(), lng!!.toDouble())
            marker1.map = naverMap

            marker1.icon = MarkerIcons.BLACK
            marker1.iconTintColor = Color.LTGRAY

            //정보창 설정
            val infoWindow = InfoWindow()
            infoWindow.adapter = object : InfoWindow.DefaultTextAdapter(baseContext) {

                var gava = GetData.mArrayList.get(i).cars


                val getinfo="주차 구역 수"+"\n"+gava


                override fun getText(infoWindow: InfoWindow):CharSequence {
                    Log.d("check",getinfo)
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


        naverMap.locationSource=locationSource2
        naverMap.locationTrackingMode= LocationTrackingMode.Follow
        naverMap.addOnLocationChangeListener { location->
        }


    }

}