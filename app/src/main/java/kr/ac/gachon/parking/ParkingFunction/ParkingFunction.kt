package kr.ac.gachon.parking.ParkingFunction

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import kotlinx.android.synthetic.main.activity_parking_function.*
import kr.ac.gachon.parking.MainActivity
import kr.ac.gachon.parking.R
import java.util.*


class ParkingFunction : AppCompatActivity() {

    internal lateinit var alarm_manager: AlarmManager
    internal lateinit var alarm_timepicker: TimePicker
    internal lateinit var context: Context
    internal lateinit var pendingIntent: PendingIntent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_parking_function)

        this.context = this

        // 알람매니저 설정
        alarm_manager = getSystemService(ALARM_SERVICE) as AlarmManager

        // 타임피커 설정
        alarm_timepicker = findViewById(R.id.time_picker)

        // Calendar 객체 생성
        val calendar = Calendar.getInstance()

        // 알람리시버 intent 생성
        val my_intent = Intent(this.context, AlarmReceiver::class.java)

        // 알람 시작 버튼
        val alarm_on = findViewById<Button>(R.id.btn_start)
        alarm_on.setOnClickListener(View.OnClickListener {
            // calendar에 시간 셋팅
            calendar.set(Calendar.HOUR_OF_DAY, alarm_timepicker.hour)
            calendar.set(Calendar.MINUTE, alarm_timepicker.minute)

            // 시간 가져옴
            val hour = alarm_timepicker.hour
            val minute = alarm_timepicker.minute
            Toast.makeText(this, "Alarm 예정 " + hour + "시 " + minute + "분", Toast.LENGTH_SHORT).show()

            // reveiver에 string 값 넘겨주기
            my_intent.putExtra("state", "alarm on")
            Log.d("alarm", "알람시작")

            pendingIntent = PendingIntent.getBroadcast(
                this, 0, my_intent,
                PendingIntent.FLAG_UPDATE_CURRENT
            )

            // 알람셋팅
            alarm_manager.set(
                AlarmManager.RTC_WAKEUP, calendar.timeInMillis,
                pendingIntent
            )
        })

        // 알람 정지 버튼
        val alarm_off = findViewById<Button>(R.id.btn_finish)
        alarm_off.setOnClickListener(View.OnClickListener {
            Toast.makeText(this, "Alarm 종료", Toast.LENGTH_SHORT).show()
            Log.d("alarm", "알람취소")
            // 알람매니저 취소
            alarm_manager.cancel(pendingIntent)

            my_intent.putExtra("state", "alarm off")

            // 알람취소
            sendBroadcast(my_intent)
        })


    }
}
