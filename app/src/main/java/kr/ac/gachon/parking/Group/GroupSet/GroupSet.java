package kr.ac.gachon.parking.Group.GroupSet;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import kr.ac.gachon.parking.Customer.signActivity;
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
    private TextView groupname;
    private TextView groupbill;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_set);
        Intent intent = getIntent();
        gnm = intent.getStringExtra("gnm");

        groupname = (TextView) findViewById(R.id.ignm);
        groupbill= (TextView) findViewById(R.id.gbill);
        groupbill.setMovementMethod(new ScrollingMovementMethod());
        groupname.setText(gnm);

        Button button = (Button)findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String gnm = groupname.getText().toString(); //edittext 그룹이름 string으로 만들기

                InsertData task = new InsertData();
                task.execute("http://" + MainActivity.IP_ADDRESS + "/css.php", gnm);
                //그룹이름과 관리자 비밀번호 입력해서 관리자 로그인하는 php연결

            }
        }); //button 닫는 문
    } // onCreate  닫는 문

    class InsertData extends AsyncTask<String, Void, String> {
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
            groupbill.setText(result);



        }//onpost닫는 문

        @Override
        protected String doInBackground(String... params) {

            String name = (String) params[1]; //그룹 이름

            String serverURL = (String) params[0];

            String postParameters = "name="+name;

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



    } //insetdata  닫는 문

}
