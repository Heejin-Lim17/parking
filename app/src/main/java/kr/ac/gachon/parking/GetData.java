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

        try {
            JSONObject jsonObject = new JSONObject(mJsonString);
            JSONArray jsonArray = jsonObject.getJSONArray(TAG_JSON);

            for(int i=0;i<jsonArray.length();i++){

                JSONObject item = jsonArray.getJSONObject(i);

                String lat = item.getString(TAG_LAT);
                String lng = item.getString(TAG_LNG);

                //System.out.println(lat+ "    " + lng);

                SeoulData seoulData = new SeoulData();

                seoulData.set_lat(lat);
                seoulData.set_lng(lng);

                mArrayList.add(seoulData);
                System.out.println("--------------------------------");
                System.out.println(mArrayList.get(0).get_lat());

            }

        } catch (JSONException e) {
            Log.d("location", "showResult : ", e);
        }
    }
}