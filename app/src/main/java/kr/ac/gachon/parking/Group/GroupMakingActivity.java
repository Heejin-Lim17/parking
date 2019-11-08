package kr.ac.gachon.parking.Group;

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
import kr.ac.gachon.parking.Group.MainGroup.GroupActivity;
import kr.ac.gachon.parking.MainActivity;
import kr.ac.gachon.parking.R;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class GroupMakingActivity extends AppCompatActivity {
    private static String TAG = "group";

    private EditText Textgname;
    private EditText Textgpw;
    private EditText Textapw;
    private EditText Textaemail;
    private TextView TextResult;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_making);

        Textgname = (EditText) findViewById(R.id.groupname);
        Textgpw = (EditText) findViewById(R.id.grouppw);
        Textapw = (EditText) findViewById(R.id.adminpw);
        Textaemail = (EditText) findViewById(R.id.adminemail);
        TextResult = (TextView) findViewById(R.id.result);

        TextResult.setMovementMethod(new ScrollingMovementMethod());
        Button btsignup = (Button) findViewById(R.id.btn_signup_ok); //가입 버튼
        Button btcancel = (Button) findViewById(R.id.btn_signup_cancel);  //취소 버튼

        btsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String gname = Textgname.getText().toString();
                String gpw = Textgpw.getText().toString();
                String apw = Textapw.getText().toString();
                String aemail = Textaemail.getText().toString();

                InsertData task = new InsertData();
                task.execute("http://" + MainActivity.IP_ADDRESS + "/make.php", gname, gpw, apw, aemail);

                Textgname.setText("");
                Textgpw.setText("");
                Textapw.setText("");
                Textaemail.setText("");

            }
        }); //가입 버튼을 눌렀을 경우에

        btcancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cancel = new Intent(getApplicationContext(), GroupActivity.class); // 그룹 가입하면 어디로 갈지 정하기
                startActivity(cancel);

            }
        }); //취소 버튼을 눌렀을 경우 그룹액티비티로 이동
    } //oncreate 닫는 문

    class InsertData extends AsyncTask<String, Void, String> {
        ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = ProgressDialog.show(GroupMakingActivity.this,
                    "Please Wait", null, true, true);

        }//preExecute 닫는 문


        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            progressDialog.dismiss();
            //TextResult.setText(result);
            Log.d(TAG, "POST response  - " + result);


            AlertDialog.Builder builder = new AlertDialog.Builder(GroupMakingActivity.this);
            builder.setMessage("그룹가입을 축하합니다.");
            builder.setPositiveButton("확인",new DialogInterface.OnClickListener(){
                @Override
                public  void onClick(DialogInterface dialog, int which){
                    Intent intent9 = new Intent(getApplicationContext(), GroupActivity.class);
                    startActivity(intent9);
                }
            });
            builder.show();
        } //onpostexectue닫는 문  결과 처리문
        @Override
        protected String doInBackground(String... params) {

            String gname = (String) params[1];
            String gpw = (String) params[2];
            String apw = (String)params[3];
            String aemail = (String)params[4];


            String serverURL = (String) params[0];
            String postParameters = "gname=" + gname + "&gpw=" + gpw + "&apw=" + apw + "&aemail=" + aemail ;


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

