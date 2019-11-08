package kr.ac.gachon.parking.Group.GroupSet;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import kr.ac.gachon.parking.Group.MainGroup.GroupActivity;
import kr.ac.gachon.parking.MainActivity;
import kr.ac.gachon.parking.R;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class GroupSet  extends AppCompatActivity {
    private String gnm; //intent받기위해서 필요함
    private static String TAG = "그룹비용확인";
    private TextView gname,gbill;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_set);

        Intent intent = getIntent();
        gnm = intent.getStringExtra("gnm");

        gname = (TextView)findViewById(R.id.gnm); //textview find
        gbill = (TextView)findViewById(R.id.gbill); //비용이 들어가야 함

        gbill.setMovementMethod(new ScrollingMovementMethod());

        gname.setText(gnm); //불러오는 그룹 이름 넣기
        Button btn_finish=(Button)findViewById(R.id.btn_g_set_finish);

        btn_finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InsertDat task1 = new InsertDat(); //그룹 비용 확인하기//
                task1.execute("http://" + MainActivity.IP_ADDRESS + "/gbs.php", gnm); //그룹이름 전달하기
                Intent intent = new Intent(getApplicationContext(), GroupActivity.class);
                startActivity(intent);
            }
        });


    } //onCreate 닫는 문

    class InsertDat extends AsyncTask<String, Void, String> {
        ProgressDialog progressDialog;


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = ProgressDialog.show(GroupSet.this,
                    "Please Wait", null, true, true);
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            progressDialog.dismiss();
            gbill.setText(result);


        }

        @Override
        protected String doInBackground(String... params) {

            String name = (String) params[1]; //그룹 이름

            String serverURL = (String) params[0];

            String postParameters = "name="+name; //그룹 이름 전달해주기

            //name이 사용자 이름 , pw가 그룹이름

            try {

                URL url = new URL(serverURL);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();

                httpURLConnection.setReadTimeout(5000);
                httpURLConnection.setConnectTimeout(5000);
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.connect();

                OutputStream outputStream = httpURLConnection.getOutputStream();
                outputStream.write(postParameters.getBytes("UTF-8"));
                outputStream.flush();
                outputStream.close();

                int responseStatusCode = httpURLConnection.getResponseCode();
                Log.d(TAG, "POST response code - " + responseStatusCode);

                InputStream inputStream;
                if (responseStatusCode == HttpURLConnection.HTTP_OK) {
                    inputStream = httpURLConnection.getInputStream();
                } else {
                    inputStream = httpURLConnection.getErrorStream();
                }
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

                StringBuilder sb = new StringBuilder();
                String line = null;

                while ((line = bufferedReader.readLine()) != null) {
                    sb.append(line);
                }
                bufferedReader.close();


                return sb.toString();


            } catch (Exception e) {

                Log.d(TAG, "InsertData: Error ", e);

                return new String("Error: " + e.getMessage());
            }


        } //doinBackgroud



    } //insertData닫는 문

}
