package kr.ac.gachon.parking.Group.GroupSet;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import kr.ac.gachon.parking.MainActivity;
import kr.ac.gachon.parking.R;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class GroupSetFirst extends AppCompatActivity {

    private EditText groupname,adminpw;
    //private static String IP_ADDRESS = "192.168.43.65";
    private static String TAG = "그룹관리";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_set_first);

        groupname = (EditText)findViewById(R.id.inputgname);
        adminpw = (EditText)findViewById(R.id.inputpw);


        Button button = (Button)findViewById(R.id.btn_g_set);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String gnm = groupname.getText().toString();
                final String apw = adminpw.getText().toString();

                InsertData task = new InsertData();
                task.execute("http://" + MainActivity.IP_ADDRESS + "/cb.php", gnm,apw);
                //그룹이름과 관리자 비밀번호 입력해서 관리자 로그인하는 php연결

            }
        }); //button 닫는 문
    } // onCreate  닫는 문

    class InsertData extends AsyncTask<String, Void, String> {
        ProgressDialog progressDialog;


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = ProgressDialog.show(GroupSetFirst.this,
                    "Please Wait", null, true, true);
        }


        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            progressDialog.dismiss();
            AlertDialog.Builder builder20 = new AlertDialog.Builder(GroupSetFirst.this);
            builder20.setTitle(result);
            if((result.compareTo("관리자로그인성공"))==0)
            {
                Log.d(TAG, "로그인 성공성공!!!");
                builder20.setMessage("관리자님 로그인을 성공했습니다.");
                builder20.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //관리자로 로그인했으니 그룹의 돈 확인할 수 있게 연결해야한다.
                        // 다른 액티비티로 이동해서 추출하기
                        Intent intent = new Intent(getApplicationContext(), GroupSet.class);
                        intent.putExtra("gnm",groupname.getText().toString()); //그룹의 이름을 들어갈 수 있는
                        startActivity(intent); //그룹의 이름을 포함해서 전달





                    }
                }); //확인 버튼

                builder20.setNegativeButton("취소", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

            } //관리자 로그인 성공했을때
            else
            {
                Log.d(TAG, "로그인 실패");
                builder20.setMessage("다시 로그인해주세요");
                builder20.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();

                    }
                }); //확인 버튼


            }



            builder20.show();

        }//onpost닫는 문

        @Override
        protected String doInBackground(String... params) {

            String name = (String) params[1]; //그룹 이름
            String pw = (String) params[2]; //그룹 비밀번호
            String serverURL = (String) params[0];

            String postParameters = "name="+name +"&pw="+pw;

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
}//총 닫는 문
