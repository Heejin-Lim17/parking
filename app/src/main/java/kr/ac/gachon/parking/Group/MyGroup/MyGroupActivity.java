package kr.ac.gachon.parking.Group.MyGroup;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import kr.ac.gachon.parking.MainActivity;
import kr.ac.gachon.parking.R;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class MyGroupActivity extends AppCompatActivity {
    private static String TAG = "사용자 그룹 조회";
    String[] garray;
    EditText userid;
    TextView glist1,glist2,glist3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_group);

        userid = (EditText)findViewById(R.id.id);
        glist1 = (TextView)findViewById(R.id.list1);
        glist2 = (TextView)findViewById(R.id.list2);
        glist3 = (TextView)findViewById(R.id.list3);
        Button button = (Button)findViewById(R.id.button);

        glist1.setMovementMethod(new ScrollingMovementMethod());
        glist2.setMovementMethod(new ScrollingMovementMethod());
        glist3.setMovementMethod(new ScrollingMovementMethod());

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String id = userid.getText().toString(); //입력한 그룹
                InsertData task = new InsertData();
                task.execute("http://" + MainActivity.IP_ADDRESS + "/us.php", id);
                //////해당 그룹 텍스트뷰 눌렀을때 이벤트 처리하기 //////
                ////////////////////////////////////////////////////
                glist1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(MyGroupActivity.this);
                        builder.setTitle(glist1.getText().toString());
                        builder.setMessage("사용한 금액을 입력해주세요");
                        final EditText et = new EditText(MyGroupActivity.this);
                        builder.setView(et);
                        builder.setPositiveButton("추가하기", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                PostData task1 = new PostData();
                                String gnm = glist1.getText().toString(); //그룹 이름
                                String bi = et.getText().toString(); //사용한 금액

                                task1.execute("http://" + MainActivity.IP_ADDRESS + "/gb.php", gnm, bi); //그룹 이름과 더할 돈을 같이 줘야함
                            }
                        }); //확인 닫는 문
                        builder.setNegativeButton("취소", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss(); //비밀번호 입력하는 확인창에서 취소 눌렀을 경우 사라짐
                            }
                        }); //취소 닫는 문
                        builder.show();
                    }
                }); //glist1처리
                glist2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(MyGroupActivity.this);
                        builder.setTitle(glist2.getText().toString());
                        builder.setMessage("사용한 금액을 입력해주세요");
                        final EditText et = new EditText(MyGroupActivity.this);
                        builder.setView(et);
                        builder.setPositiveButton("추가하기", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                PostData task1 = new PostData();
                                task1.execute("http://" + MainActivity.IP_ADDRESS + "/gb.php", glist2.getText().toString(), et.getText().toString()); //그룹 이름과 더할 돈을 같이 줘야함
                            }
                        }); //확인 닫는 문
                        builder.setNegativeButton("취소", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss(); //비밀번호 입력하는 확인창에서 취소 눌렀을 경우 사라짐
                            }
                        });  //취소 닫는 문
                        builder.show();
                    }
                }); //glist2처리
                glist3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(MyGroupActivity.this);
                        builder.setTitle(glist3.getText().toString());
                        builder.setMessage("사용한 금액을 입력해주세요");
                        final EditText et = new EditText(MyGroupActivity.this);
                        builder.setView(et);
                        builder.setPositiveButton("추가하기", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                PostData task1 = new PostData();
                                task1.execute("http://" + MainActivity.IP_ADDRESS + "/gb.php", glist3.getText().toString(), et.getText().toString()); //그룹 이름과 더할 돈을 같이 줘야함
                            }
                        }); //확인 닫는 문
                        builder.setNegativeButton("취소", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss(); //비밀번호 입력하는 확인창에서 취소 눌렀을 경우 사라짐
                            }
                        });  //취소 닫는 문
                        builder.show();
                    }
                }); //glist3처리
            }
        });
    }

    class InsertData extends AsyncTask<String, Void, String> {
        ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = ProgressDialog.show(MyGroupActivity.this,
                    "Please Wait", null, true, true);
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            progressDialog.dismiss();
            garray =result.split(","); //php에서 받은 값을 ,기준으로 조깨기
            glist1.setText(garray[0]);
            glist2.setText(garray[1]);
            glist3.setText(garray[2]);

        }

        @Override
        protected String doInBackground(String... params) {

            String userid = (String) params[1];
            String serverURL = (String) params[0];

            String postParameters = "userid=" + userid;
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
    }
    //그룹에게 돈 더하는 함수
    class PostData extends AsyncTask<String, Void, String> {
        ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = ProgressDialog.show(MyGroupActivity.this,
                    "Please Wait", null, true, true);
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            progressDialog.dismiss();
            AlertDialog.Builder builder1 = new AlertDialog.Builder(MyGroupActivity.this);
            builder1.setTitle(result);

            builder1.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss(); //일단은 사라지게

                }
            }); //확인 닫는 문

            builder1.show();


        }

        @Override
        protected String doInBackground(String... params) {

            String gnm = (String) params[1]; //그룹 이름
            String bill= (String) params[2]; //사용한 금액


            String serverURL = (String) params[0];

            String postParameters = "name=" + gnm +"&bill="+bill ;


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
