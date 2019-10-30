package kr.ac.gachon.parking.Info

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.naver.maps.geometry.LatLng
import com.naver.maps.map.*
import kr.ac.gachon.parking.R
import com.naver.maps.map.overlay.Marker
import com.naver.maps.map.util.FusedLocationSource
import kr.ac.gachon.parking.MainActivity


class DisabledInfo : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var locationSource2: FusedLocationSource
    private var mapView2: MapView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        NaverMapSdk.getInstance(this).client = NaverMapSdk.NaverCloudPlatformClient("cc86tt11qz")
        setContentView(R.layout.activity_disabled_info)

        mapView2 = findViewById(R.id.dis_map)
        mapView2!!.getMapAsync(this)

        locationSource2=FusedLocationSource(this, DisabledInfo.LOCATION_PERMISSION_REQUEST_CODE)

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
        val marker = Marker()
        marker.position = LatLng(37.5670135, 126.9783740)
        marker.map = naverMap

        naverMap.locationSource=locationSource2
        naverMap.locationTrackingMode= LocationTrackingMode.Follow
        naverMap.addOnLocationChangeListener { location->
        }
    }


//    override fun onStart() {
//        super.onStart()
//        mapView2?.onStart()
//    }
//
//    override fun onResume() {
//        super.onResume()
//        mapView2?.onResume()
//    }
//
//    override fun onPause() {
//        super.onPause()
//        mapView2?.onPause()
//    }
//
//    override fun onSaveInstanceState(outState: Bundle) {
//        super.onSaveInstanceState(outState)
//        mapView2?.onSaveInstanceState(outState)
//    }
//
//    override fun onStop() {
//        super.onStop()
//        mapView2?.onStop()
//    }
//
//    override fun onDestroy() {
//        super.onDestroy()
//        mapView2?.onDestroy()
//    }
//
//    override fun onLowMemory() {
//        super.onLowMemory()
//        mapView2?.onLowMemory()
//    }
}
