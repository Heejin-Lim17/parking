package kr.ac.gachon.parking.Info

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.ActionBarDrawerToggle
import com.naver.maps.geometry.LatLng
import com.naver.maps.map.*
import com.naver.maps.map.overlay.Marker
import com.naver.maps.map.util.FusedLocationSource
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
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
        val marker = Marker()
        marker.position = LatLng(37.5670135, 126.9783740)
        marker.map = naverMap

        naverMap.locationSource = locationSource3
        naverMap.locationTrackingMode = LocationTrackingMode.Follow
        naverMap.addOnLocationChangeListener { location ->
        }
    }

}