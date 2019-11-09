package kr.ac.gachon.parking.Customer;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import kr.ac.gachon.parking.MainActivity;
import kr.ac.gachon.parking.R;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class signActivity  extends AppCompatActivity {

    private static String TAG = "Signupphp";
    private EditText Textname;
    private EditText Textid;
    private EditText Textpw;
    private EditText Textcarno;
    private EditText Textphoneno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_2);



        Textname = (EditText) findViewById(R.id.signup_name);
        Textid = (EditText) findViewById(R.id.signup_id);
        Textpw = (EditText) findViewById(R.id.signup_pw);
        Textcarno = (EditText) findViewById(R.id.signup_car_no);
        Textphoneno = (EditText) findViewById(R.id.signup_phone_no);
        Button btsignup = (Button) findViewById(R.id.btn_signup_ok);


        ///회원가입 버튼 눌렀을 경우
        btsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = Textname.getText().toString();
                String id = Textid.getText().toString();
                String pw = Textpw.getText().toString();
                String carno = Textcarno.getText().toString();
                String phoneno = Textphoneno.getText().toString();
                InsertData task = new InsertData();
                task.execute("http://" + MainActivity.IP_ADDRESS + "/insert.php", name, id, pw, carno, phoneno);
                //회원가입한 정보 디비에 저장하는 php
                Textname.setText("");
                Textid.setText("");
                Textpw.setText("");
                Textcarno.setText("");
                Textphoneno.setText("");

            }}); //회원가입 버튼 닫는 문

    }//oncreate닫는 문

    class InsertData extends AsyncTask<String, Void, String> {
        ProgressDialog progressDialog;


        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            progressDialog = ProgressDialog.show(signActivity.this,
                    "Please Wait", null, true, true);

        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            progressDialog.dismiss();
            //  TextResult.setText(result); //확인을 누르는데 회원가입이 무조건 가능하쥬
            Log.d(TAG, "POST response  - " + result);
            AlertDialog.Builder builder = new AlertDialog.Builder(signActivity.this);
            builder.setMessage("회원가입을 완료했습니다.");
            builder.setPositiveButton("확인",new DialogInterface.OnClickListener(){
                @Override
                public  void onClick(DialogInterface dialog, int which){
                    Intent intent10 = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent10);
                }
            });

            builder.show();

        } //insert결과값 처리
        @Override
        protected String doInBackground(String... params) {

            String name = (String) params[1];
            String id = (String) params[2];
            String pw = (String) params[3];
            String car = (String)params[4];
            String phone = (String)params[5];

            String serverURL = (String) params[0];
            String postParameters = "name=" + name + "&id=" + id + "&pw=" + pw + "&car=" + car + "&phone=" + phone;


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

        }


    }//insertdata닫는 문

} //main닫는 문
