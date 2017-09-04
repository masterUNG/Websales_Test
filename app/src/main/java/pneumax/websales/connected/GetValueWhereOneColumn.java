package pneumax.websales.connected;

import android.content.Context;
import android.os.AsyncTask;
import android.view.View;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import pneumax.websales.BasicAuthInterceptor;

/**
 * Created by masterung on 9/4/2017 AD.
 */

public class GetValueWhereOneColumn extends AsyncTask<String, View, String>{

    private Context context;

    public GetValueWhereOneColumn(Context context) {
        this.context = context;
    }

    @Override
    protected String doInBackground(String... strings) {

        try {

            RequestBody requestBody = new FormBody.Builder()
                    .add(strings[0], strings[1])
                    .build();
            Request.Builder builder = new Request.Builder();
            Request request = builder.url(strings[2]).post(requestBody).build();

            OkHttpClient okHttpClient = new OkHttpClient.Builder()
                    .addInterceptor(new BasicAuthInterceptor()).build();
            Response response = okHttpClient.newCall(request).execute();
            return response.body().string();


        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }
}   // Main Class
