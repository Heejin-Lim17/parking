package kr.ac.gachon.parking.Group.JoinGroup;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import kr.ac.gachon.parking.MainActivity;
import kr.ac.gachon.parking.R;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;


public class JoinGroup extends AppCompatActivity {

    private static String TAG = "그룹 조회하고 사용자 그룹에 가입하는 ";
    EditText cname;
    TextView cresult;

//    private TextView pwtext;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_searching);



        cname = (EditText) findViewById(R.id.inputgname);
//        pwtext =(TextView)findViewById(R.id.pwtext);
        cresult = (TextView) findViewById(R.id.search_res);
        cresult.setMovementMethod(new ScrollingMovementMethod());
//        pwtext.setMovementMethod(new ScrollingMovementMethod());

        ImageButton btn = (ImageButton) findViewById(R.id.btn_search);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String findname = cname.getText().toString();
                InsertData task = new InsertData();
                task.execute("http://" + MainActivity.IP_ADDRESS + "/gc.php", findname);

                cresult.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        AlertDialog.Builder builder = new AlertDialog.Builder(JoinGroup.this);
                        builder.setTitle("그룹가입");
                        builder.setMessage("그룹 가입하시겠습니까?");

                        builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                AlertDialog.Builder builder10 = new AlertDialog.Builder(JoinGroup.this);
                                builder10.setTitle("비밀번호입력");
                                builder10.setMessage("그룹 비밀번호를 입력해주세요");
                                final EditText et = new EditText(JoinGroup.this);

                                builder10.setView(et);



                                builder10.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {

                                        String name = cname.getText().toString();
                                        String pw = et.getText().toString(); //그룹 비밀번호
                                        ChData task20 =new ChData();
                                        task20.execute("http://"+MainActivity.IP_ADDRESS + "/gpw.php",name,pw);
                                    }
                                });
                                builder10.setNegativeButton("취소", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss(); //비밀번호 입력하는 확인창에서 취소 눌렀을 경우 사라짐
                                    }
                                });

                                builder10.show();

                            }
                        }); //builder의 setpostive 닫는 문

                        builder.setNegativeButton("취소", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        }); //취소 버튼 눌렀을경우 알림창만 사라지기

                        builder.show();


                    }
                }); //textview이벤트 닫기
            } //onClick

        }); //이미지 버튼 문닫기

    }

    class InsertData extends AsyncTask<String, Void, String> {
        ProgressDialog progressDialog;


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = ProgressDialog.show(JoinGroup.this,
                    "Please Wait", null, true, true);
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            System.out.println("---------result------------------");
            System.out.println(result);
            progressDialog.dismiss();
            cresult.setText(result);
        }

        @Override
        protected String doInBackground(String... params) {

            String name = (String) params[1];

            String serverURL = (String) params[0];

            String postParameters = "name=" + name;
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


        }
    } //insertdata 닫는 문

    class ChData extends AsyncTask<String, Void, String> {


        ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = ProgressDialog.show(JoinGroup.this,
                    "Please Wait", null, true, true);
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            progressDialog.dismiss();
            AlertDialog.Builder builder20 = new AlertDialog.Builder(JoinGroup.this);
            builder20.setTitle(result);

            if((result.compareTo("그룹로그인성공"))==0)
            {
                builder20.setMessage("그룹가입이 완료되었습니다.");
                builder20.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // 그룹 가입 완료시에 사용자db 에 추가하여야함
                        //    SignData task30 = new SignData();
                        // 사용자 이름과 추가하려는 그룹 명을 넣어줘야 한다.
                        //사용자 이름을 불러와야한다.
                        // 그룹은 cname에서 불러오기
                        SignData task30 = new SignData();
                        String unm ="d"; //사용자 아이디 입력하기
                        String gnm =cname.getText().toString(); //그룹 이름 넣어주기
                        task30.execute("http://"+MainActivity.IP_ADDRESS + "/gs.php",unm,gnm); //추가하는 php


                    }
                }); //확인 버튼

                builder20.setNegativeButton("취소", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

            }
            else
            {
                builder20.setMessage("다시 로그인해주세요");
                builder20.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();

                    }
                }); //확인 버튼


            }



            builder20.show();

        }

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


        }


    }//chdata닫는 문

    class SignData extends AsyncTask<String, Void, String> {
        ProgressDialog progressDialog;


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = ProgressDialog.show(JoinGroup.this,
                    "Please Wait", null, true, true);
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            progressDialog.dismiss();
            AlertDialog.Builder builder30 = new AlertDialog.Builder(JoinGroup.this);
            builder30.setTitle(result);
            if((result.compareTo("사용자에게 새로운 그룹을 추가했습니다."))==0)
            {
                builder30.setMessage("그룹 추가되었습니다. ");
                builder30.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //다음화면으로 연결할 intent 가 필요함
                    }
                });
            } //비밀번호 로그인 실패하였을때, 다른걸로 연결


            builder30.show();


        }
        @Override
        protected String doInBackground(String... params) {

            String name = (String) params[1]; //사용자 이름
            String pw = (String)params[2]; //추가하려는 그룹 이름

            String serverURL = (String) params[0];

            String postParameters = "name=" + name +"&pw="+pw;
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
        }





    } //SignData 닫는 문
}//main닫는 문


