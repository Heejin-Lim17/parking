package kr.ac.gachon.parking

import android.os.Bundle
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.UiThread
import androidx.fragment.app.FragmentActivity
import com.naver.maps.geometry.LatLng
import com.naver.maps.map.*
import com.naver.maps.map.overlay.Marker
import com.naver.maps.map.util.FusedLocationSource

//MapFragment
class MapFragmentActivity : FragmentActivity(), OnMapReadyCallback {

    private lateinit var locationSource: FusedLocationSource

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.content_main)

        locationSource=FusedLocationSource(this,LOCATION_PERMISSION_REQUEST_CODE)

        NaverMapSdk.getInstance(this).client = NaverMapSdk.NaverCloudPlatformClient("cc86tt11qz")

        val mapView = MapView(this)
        val mapViewContainer = findViewById(R.id.map) as ViewGroup
        mapViewContainer.addView(mapView)



        val fm = supportFragmentManager
        val mapFragment = fm.findFragmentById(R.id.map) as MapFragment?
            ?: MapFragment.newInstance().also {
                fm.beginTransaction().add(R.id.map, it).commit()
            }

        mapFragment.getMapAsync(this)
    }

    @UiThread
    override fun onMapReady(naverMap: NaverMap) {

        val marker= Marker()
        marker.position = LatLng(37.451001, 127.128837)
        marker.map = naverMap

//        naverMap.locationSource=locationSource
//        naverMap.locationTrackingMode=LocationTrackingMode.Follow
//        naverMap.addOnLocationChangeListener { location->
//        }
//
//        naverMap.mapType = NaverMap.MapType.Navi
//        naverMap.setLayerGroupEnabled(NaverMap.LAYER_GROUP_TRAFFIC, true)
//        val cameraPosition = CameraPosition(
//            LatLng(37.5666102, 126.9783881), // 대상 지점
//            16.0, // 줌 레벨
//            20.0, // 기울임 각도
//            180.0 // 베어링 각도
//        )
//
//        Toast.makeText(this,
//            "대상 지점 위도: ${cameraPosition.target.latitude}, " +
//                    "대상 지점 경도: ${cameraPosition.target.longitude}, " +
//                    "줌 레벨: ${cameraPosition.zoom}, " +
//                    "기울임 각도: ${cameraPosition.tilt}, " +
//                    "베어링 각도: ${cameraPosition.bearing}",
//            Toast.LENGTH_SHORT).show()

        //marker


//        val school = LatLng(37.451001, 127.128837)
//        naverMap?.let{
//            marker.map=naverMap
//        }
        onStart()
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
    }

}
