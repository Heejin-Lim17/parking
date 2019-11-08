//package kr.ac.gachon.parking;
//
//import android.content.Context;
//import android.location.Address;
//import android.location.Geocoder;
//import android.os.AsyncTask;
//import android.util.Log;
//import org.json.JSONArray;
//import org.json.JSONException;
//import org.json.JSONObject;
//import java.io.*;
//import java.net.HttpURLConnection;
//import java.net.URL;
//import java.util.ArrayList;
//import java.util.List;
//
//public class GetDataSeongnam extends AsyncTask<String, Void, String> {
//
//    public static String AddrJsonString;
//    public static ArrayList<SeongnamData> AddrArrayList = new ArrayList<>(); // 주차장정보를 저장할 SeongnamData형 배열
//
//    String errorString = null;
//
//    @Override
//    protected void onPreExecute() {
//        super.onPreExecute();
//        //
//    }
//
//    @Override
//    protected void onPostExecute(String result) {
//        super.onPostExecute(result);
//        Log.d("address", "response - " + result);
//        if (result == null){
//            Log.d("address", errorString);
//        }
//        else {
//            AddrJsonString = result;
//            showResult();
//            toLocation();
//        }
//    }
//
//
//    @Override
//    protected String doInBackground(String... params) {
//
//        String serverURL = params[0];
//        String postParameters = params[1];
//
//        try {
//            URL url = new URL(serverURL);
//            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
//
//            httpURLConnection.setReadTimeout(5000);
//            httpURLConnection.setConnectTimeout(5000);
//            httpURLConnection.setRequestMethod("POST");
//            httpURLConnection.setDoInput(true);
//            httpURLConnection.connect();
//
//            OutputStream outputStream = httpURLConnection.getOutputStream();
//            outputStream.write(postParameters.getBytes("UTF-8"));
//            outputStream.flush();
//            outputStream.close();
//
//            int responseStatusCode = httpURLConnection.getResponseCode();
//            Log.d("address", "response code - " + responseStatusCode);
//
//            InputStream inputStream;
//            if(responseStatusCode == HttpURLConnection.HTTP_OK) {
//                inputStream = httpURLConnection.getInputStream();
//            }
//            else{
//                inputStream = httpURLConnection.getErrorStream();
//            }
//
//            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
//            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
//
//            StringBuilder sb = new StringBuilder();
//            String line;
//
//            while((line = bufferedReader.readLine()) != null){
//                sb.append(line);
//            }
//
//            bufferedReader.close();
//
//            return sb.toString().trim();
//
//        } catch (Exception e) {
//
//            Log.d("address", "GetData : Error ", e);
//            errorString = e.toString();
//
//            return null;
//        }
//    }
//
//
//    private void showResult(){
//        String TAG_JSON="address";
//        String TAG_ADDR = "pkl_address";
//        String TAG_BASICTIME = "pk_basic_time"; // 주차기본시간
//        String TAG_BASICFEE = "pk_basic_fee"; //주차기본요금
//        String TAG_ADDTIME = "add_time"; // 추가단위시간
//        String TAG_ADDFEE = "add_fee"; // 추가단위요금
//
//        try {
//            JSONObject jsonObject = new JSONObject(AddrJsonString);
//            JSONArray jsonArray = jsonObject.getJSONArray(TAG_JSON);
//
//            for(int i=0;i<jsonArray.length();i++){
//
//                JSONObject item = jsonArray.getJSONObject(i);
//
//                String addr = item.getString(TAG_ADDR);
//                String basic_time = item.getString(TAG_BASICTIME);
//                Integer basic_fee = item.getInt(TAG_BASICFEE);
//                Integer add_time = item.getInt(TAG_ADDTIME);
//                Integer add_fee = item.getInt(TAG_ADDFEE);
//
//                SeongnamData seongnamData = new SeongnamData();
//                seongnamData.set_addr(addr);
//                seongnamData.set_basictime(basic_time);
//                seongnamData.set_basicfee(basic_fee);
//                seongnamData.set_addtime(add_time);
//                seongnamData.set_addfee(add_fee);
//
//                AddrArrayList.add(seongnamData);
//                System.out.println("--------------------------------");
//                System.out.println(AddrArrayList.get(0).get_basictime());
//
//            }
//
//        } catch (JSONException e) {
//            Log.d("address", "showResult : ", e);
//        }
//    }
//
//    // onCreate
////    Context context;
////    static Geocoder geocoder = new Geocoder(MainActivity.this);
//
//    Geocoder geo = new Geocoder(MainActivity.context);
//
//    public void toLocation() {
//        String TAG_ ="seong_location";
//        List<Address> list = null; // 국가명, 위도, 경도 등의 정보
//        for (int i = 0; i < AddrArrayList.size(); i++) {
//            try {
//                list = geo.getFromLocationName(AddrArrayList.get(i).get_addr(), // 주소지
//                        10); // 읽을 개수
//                System.out.println("---------------list--------------------");
//                //System.out.println(list);
//                System.out.println(list.get(0).getLatitude());
//
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//        System.out.println(list);
//        if(list != null) {
//            if(list.size() == 0) {
//                Log.d(TAG_, "해당되는 주소가 없습니다.");
//            }
//            else {
//                for(int i=0; i<list.size(); i++) {
//                    System.out.println("----------------BEFORE ERROR-------------------");
//                    System.out.println(AddrArrayList.get(i).get_addr());
//                    String lat;
//                    String lng;
//                    lat = String.valueOf(list.get(i).getLatitude());
//                    lng = String.valueOf(list.get(i).getLongitude());
//                    AddrArrayList.get(i).set_lat(lat);
//                    AddrArrayList.get(i).set_lng(lng);
//                    //System.out.println("-----------------------------------");
//                    //System.out.println(AddrArrayList.get(0).get_lat());
//                    Log.d(TAG_, AddrArrayList.get(0).get_lat());
//                }
//            }
//        }
//
//    }
//}