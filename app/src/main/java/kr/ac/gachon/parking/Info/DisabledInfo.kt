package kr.ac.gachon.parking.Info

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kr.ac.gachon.parking.R
import com.naver.maps.map.NaverMapSdk




class DisabledInfo : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        NaverMapSdk.getInstance(this).client = NaverMapSdk.NaverCloudPlatformClient("cc86tt11qz")
        setContentView(R.layout.activity_disabled_info)
    }
}
