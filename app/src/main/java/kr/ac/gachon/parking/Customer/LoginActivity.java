package kr.ac.gachon.parking.Customer;

import android.app.Activity;
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

public class LoginActivity  extends AppCompatActivity {

    private static String TAG = "login";
    public static String ingid;
    private EditText cid;
    private EditText cpw;
    // private TextView cresult;
    String id, pw;


    String a;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);




        cid = (EditText) findViewById(R.id.login_id);
        cpw = (EditText) findViewById(R.id.login_pw);
        // cresult = (TextView) findViewById(R.id.cresult);



        //  cresult.setMovementMethod(new ScrollingMovementMethod());
        Button btnlogin = (Button) findViewById(R.id.btn_login); // 로그인 버튼
        Button btnsign =(Button)findViewById(R.id.btn_signup); //회원가입 버튼
        //Button check = (Button)findViewById(R.id.check);




        //로그인 버튼 눌렀을 때
        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {




                id = cid.getText().toString();
                pw = cpw.getText().toString();


                InsertData task = new InsertData();
                task.execute("http://" + MainActivity.IP_ADDRESS + "/user.php", id, pw);
                //사용자 로그인하는 PHP

                //cid.setText("");
                //cpw.setText("");

            }
        });  // 로그인버튼 눌렀을때 수행문


        //회원가입 버튼 눌렀을때
        btnsign.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent8 = new Intent(getApplicationContext(), signActivity.class); //회원가입 액티비티로 연결
                startActivity(intent8);
            }

        }); //회원가입 버튼 눌렀을때 수행문



    } //oncreate 수행문

    class  InsertData extends AsyncTask<String, Void, String> {
        ProgressDialog progressDialog;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = ProgressDialog.show(LoginActivity.this,
                    "Please Wait", null, true, true);
        }


        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            progressDialog.dismiss();
            a=result;

            Log.d(TAG, "POST response  - " + result);
            AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
            builder.setTitle(result); //타이틀에다가 로그인 성공인지 로그인 실패인지 뜨게 하기
            // cresult.setText(a); //cresult안에 로그인성공했습니다. or 로그인을 실패하였습니다.가 저장됨

            if((result.compareTo("로그인성공"))==0) //로그인을 성공했을때
            {
                builder.setMessage("로그인을 성공했습니다.");
                // 로그인 성공했을때 RbPreference 클래스에 id, pw값 저장하기
//                Intent intent10 = new Intent();
//                intent10.putExtra("using",id);
//                startActivity(intent10);
                //클래스에 로그인 성공한 id, pw값 넣기!!!!
                LoginActivity.ingid=id;

            }
            else //로그인을 실패하였을때
            {   //cresult.setText("왜 안될까");
                builder.setMessage("다시 시도해주세요 ");
            }

            builder.setPositiveButton("확인",new DialogInterface.OnClickListener(){
                @Override
                public  void onClick(DialogInterface dialog, int which) {
                    if((a.compareTo("로그인성공"))==0) //로그인 성공했을 때 id, pw 값 RbPreference클래스에 저장하기

                    {
                        Intent intent7 = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent7);
                    } //로그인 성공 일때 확인 버튼 누르면 메인액티비티로 이동
                    else
                    {
                        Intent intent6 = new Intent(getApplicationContext(), LoginActivity.class);
                        startActivity(intent6);

                    }//로그인 실패일때 확인 버튼 누르면 로그인액티비티로 이동

                }

            } );
            builder.show();
        } //onpost 닫는 문




        @Override
        protected String doInBackground(String... params) {

            String id = (String) params[1];
            String pw = (String) params[2];
            String serverURL = (String) params[0];
            String postParameters = "id=" + id + "&pw=" + pw;
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
    }
}