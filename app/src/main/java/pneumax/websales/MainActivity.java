package pneumax.websales;

import android.Manifest;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import pl.tajchert.nammu.Nammu;
import pl.tajchert.nammu.PermissionCallback;

//import org.apache.http.impl.client.HttpClientBuilder;

//import org.apache.http.client.HttpClient;

public class MainActivity extends AppCompatActivity {

    private EditText mUsernameEditText;
    private EditText mPasswordEditText;
    private Button mLoginBtn;
    private String _username;
    private String _password;

    //private Employee mEmployee;
    private Employees mEmployees;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Load Layout Activity_main
        setContentView(R.layout.activity_main);
        //bind ตัวแปร
        bindWidgets();
        setEvent();
        //Call Method RuntimePermission
        checkRuntimPermission();
    }

    // Check Runtime Permission -- BEGIN
    public void checkRuntimPermission() {
        Nammu.init(this);
        // Check Runtime Permission
        Nammu.askForPermission(this, Manifest.permission.INTERNET, new PermissionCallback() {
            @Override
            public void permissionGranted() {
//                Toast.makeText(MainActivity.this, "Manifest.permission.INTERNET - Granted", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void permissionRefused() {
                Toast.makeText(MainActivity.this, "Manifest.permission.INTERNET - Denied", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }

    private void bindWidgets() {
        mUsernameEditText = (EditText) findViewById(R.id.usernameEditText);
        mPasswordEditText = (EditText) findViewById(R.id.passwordEditText);
        mLoginBtn = (Button) findViewById(R.id.LoginButton);
    }

    private void setEvent() {
        mLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (android.os.Build.VERSION.SDK_INT > 9) {
                    StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                    StrictMode.setThreadPolicy(policy);
                }

                _username = mUsernameEditText.getText().toString();
                _password = mPasswordEditText.getText().toString();

                if (GlobalVar.getInstance().isEmptyString(_username)) {
                    Toast.makeText(MainActivity.this, "กรุณาป้อน Username ด้วย !!!", Toast.LENGTH_SHORT).show();
                } else if (GlobalVar.getInstance().isEmptyString(_password)) {
                    Toast.makeText(MainActivity.this, "กรุณาป้อน Password ด้วย !!!", Toast.LENGTH_SHORT).show();
                } else {
                    String url = "http://58.181.171.23/webservice/Service.asmx/getLogin";
                    OkHttpHandler okHttpHandler = new OkHttpHandler();
                    okHttpHandler.execute(url);
                }
            }
        });
    }

    public class OkHttpHandler extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {

            RequestBody data = new FormBody.Builder()
                    .add("UserName", _username)
                    .add("Password", _password)
                    .build();
            Request.Builder builder = new Request.Builder();
            builder.url(params[0]).post(data).build();
            Request request = builder.build();

            try {
                OkHttpClient client = new OkHttpClient.Builder()
                        .addInterceptor(new BasicAuthInterceptor())
                        .build();
                Response response = client.newCall(request).execute();
                String result = response.body().string();
                return result;
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;

        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            try {
                s = GlobalVar.getInstance().JsonXmlToJsonString(s);
                Gson gson = new Gson();
                //Employee resultEmp = gson.fromJson(s.toString(), Employee.class);

                Employees resultEmps = gson.fromJson(s.toString(), Employees.class);

                if (resultEmps == null) {
                    Toast.makeText(MainActivity.this, "Username หรือ Password ไม่ถูกต้อง !!!", Toast.LENGTH_SHORT).show();
                } else {
                    mEmployees = resultEmps;
                    if (mEmployees.STFcode == null) {
                        Toast.makeText(MainActivity.this, "Username หรือ Password ไม่ถูกต้อง !!!", Toast.LENGTH_SHORT).show();
                    } else {
                        //Toast.makeText(MainActivity.this, mEmployee.getSTFfullname(), Toast.LENGTH_SHORT).show();
                        //เหมือนกับ ซองจดหมาย แล้วข้างในซองจดหมายอาจมีอะไรหลายอย่าง ใช้สำหรับสื่อสารข้าม object

//                        Intent i = new Intent(getApplicationContext(), SuccessActivity.class);


                        Intent i = new Intent(getApplicationContext(), ChooseSalesActivity.class);


                        //ต้องการ Put Intent ข้อมูล ทั้ง result แต่ต้องไปทำที่หน้า UserBean ก่อน ไม่งั้น Error
                        i.putExtra(Employees.TABLE_NAME, resultEmps);

                        startActivity(i);
                    }

//                    //เหมือนกับ ซองจดหมาย แล้วข้างในซองจดหมายอาจมีอะไรหลายอย่าง ใช้สำหรับสื่อสารข้าม object
//                    Intent i = new Intent(getApplicationContext(), SuccessActivity.class);
//                    //ต้องการ Put Intent ข้อมูล ทั้ง result แต่ต้องไปทำที่หน้า UserBean ก่อน ไม่งั้น Error
//                    i.putExtra(UserBean.TABLE_NAME, result);
//
//                    startActivity(i);
                }
            } catch (Exception e) {
                //e.printStackTrace();
                Toast.makeText(MainActivity.this, "e:" + e, Toast.LENGTH_SHORT).show();
            }

        }
    }

}
