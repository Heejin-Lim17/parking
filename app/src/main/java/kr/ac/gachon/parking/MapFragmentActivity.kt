package kr.ac.gachon.parking

import android.os.Bundle
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import com.google.android.gms.location.FusedLocationProviderClient
import com.naver.maps.geometry.LatLng
import com.naver.maps.map.*
import com.naver.maps.map.overlay.Marker
import com.naver.maps.map.util.FusedLocationSource
import kotlinx.android.synthetic.main.content_main.*

class MapFragmentActivity : FragmentActivity(), OnMapReadyCallback {
    private lateinit var locationSource: FusedLocationSource //

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.content_main)

        locationSource = FusedLocationSource(this, LOCATION_PERMISSION_REQUEST_CODE) //

        NaverMapSdk.getInstance(this).client = NaverMapSdk.NaverCloudPlatformClient("cc86tt11qz") //

        val mapView = MapView(this) //
        val mapViewContainer = findViewById<ViewGroup>(R.id.map) //
        mapViewContainer.addView(mapView) //

        val fm = supportFragmentManager
        val mapFragment = fm.findFragmentById(R.id.map) as MapFragment?
            ?: MapFragment.newInstance().also {
                fm.beginTransaction().add(R.id.map, it).commit()
            }
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(p0: NaverMap) {
        val marker = Marker()
        marker.position = LatLng(37.45001, 127.128837)
        marker.map = p0
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if(locationSource.onRequestPermissionsResult(requestCode,permissions,grantResults)) {
            return
        }
    }

    companion object {
        private const val LOCATION_PERMISSION_REQUEST_CODE=1000
    }
}