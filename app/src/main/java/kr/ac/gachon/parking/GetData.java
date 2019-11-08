package kr.ac.gachon.parking;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.Log;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class GetData extends AsyncTask<String, Void, String> {

    public static String mJsonString;
    public static ArrayList<SeoulData> mArrayList = new ArrayList<>(); // 위도, 경도 저장할 배열

    String errorString = null;

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        //
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
        Log.d("location", "response - " + result);
        if (result == null){
            Log.d("location", errorString);
        }
        else {
            mJsonString = result;
            showResult();
        }
    }


    @Override
    protected String doInBackground(String... params) {

        String serverURL = params[0];
        String postParameters = params[1];

        try {
            URL url = new URL(serverURL);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();

            httpURLConnection.setReadTimeout(5000);
            httpURLConnection.setConnectTimeout(5000);
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoInput(true);
            httpURLConnection.connect();

            OutputStream outputStream = httpURLConnection.getOutputStream();
            outputStream.write(postParameters.getBytes("UTF-8"));
            outputStream.flush();
            outputStream.close();

            int responseStatusCode = httpURLConnection.getResponseCode();
            Log.d("location", "response code - " + responseStatusCode);

            InputStream inputStream;
            if(responseStatusCode == HttpURLConnection.HTTP_OK) {
                inputStream = httpURLConnection.getInputStream();
            }
            else{
                inputStream = httpURLConnection.getErrorStream();
            }

            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            StringBuilder sb = new StringBuilder();
            String line;

            while((line = bufferedReader.readLine()) != null){
                sb.append(line);
            }

            bufferedReader.close();

            return sb.toString().trim();

        } catch (Exception e) {

            Log.d("location", "GetData : Error ", e);
            errorString = e.toString();

            return null;
        }
    }


    private void showResult(){
        String TAG_JSON="location";
        String TAG_LAT = "pkl_Latitude";
        String TAG_LNG = "pkl_longitude";
        String TAG_ADDR = "pkl_address"; // 주소
        String TAG_BASICTIME = "pk_basic_time"; // 주차기본시간
        String TAG_BASICFEE = "pk_basic_fee"; //주차기본요금
        String TAG_ADDTIME = "add_time"; // 추가단위시간
        String TAG_ADDFEE = "add_fee"; // 추가단위요금

        String TAG_FEE_TF = "fee_division";
        String TAG_SAT_TF = "saturdat_fee_devision";
        String TAG_HOL_TF = "holiday_fee_division";
        String TAG_DAY_CLOSE = "weekday_close_time";
        String TAG_WEEKEND_CLOSE = "weekend_close_time";
        String TAG_HOL_CLOSE = "holiday_close_time";

        try {
            JSONObject jsonObject = new JSONObject(mJsonString);
            JSONArray jsonArray = jsonObject.getJSONArray(TAG_JSON);

            for(int i=0;i<jsonArray.length();i++){

                JSONObject item = jsonArray.getJSONObject(i);

                String lat = item.getString(TAG_LAT);
                String lng = item.getString(TAG_LNG);
                String addr = item.getString(TAG_ADDR);
                String basic_time = item.getString(TAG_BASICTIME);
                Integer basic_fee = item.getInt(TAG_BASICFEE);
                Integer add_time = item.getInt(TAG_ADDTIME);
                Integer add_fee = item.getInt(TAG_ADDFEE);
                String fee_division = item.getString(TAG_FEE_TF);
                String saturday_fee_devision = item.getString(TAG_SAT_TF);
                String holiday_fee_division = item.getString(TAG_HOL_TF);
                String weekday_close_time = item.getString(TAG_DAY_CLOSE);
                String weekend_close_time = item.getString(TAG_WEEKEND_CLOSE);
                String holiday_close_time = item.getString(TAG_HOL_CLOSE);

                SeoulData seoulData = new SeoulData();
                seoulData.set_lat(lat);
                seoulData.set_lng(lng);
                seoulData.set_addr(addr);
                seoulData.set_basictime(basic_time);
                seoulData.set_basicfee(basic_fee);
                seoulData.set_addtime(add_time);
                seoulData.set_addfee(add_fee);
                seoulData.set_fee_division(fee_division);
                seoulData.set_saturday_fee_devision(saturday_fee_devision);
                seoulData.set_holiday_fee_division(holiday_fee_division);
                seoulData.set_weekday_close_time(weekday_close_time);
                seoulData.set_weekend_close_time(weekend_close_time);
                seoulData.set_holiday_close_time(holiday_close_time);
                mArrayList.add(seoulData);

//                System.out.println("--------------------------------");
//                System.out.println(mArrayList.get(0).get_holiday_close_time());
               // System.out.println(mArrayList.get(0).get_addr());

            }
//            System.out.println(mArrayList.get(0).get_holiday_close_time());

        } catch (JSONException e) {
            Log.d("location", "showResult : ", e);
        }
    }
}