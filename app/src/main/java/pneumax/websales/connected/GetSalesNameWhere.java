package pneumax.websales.connected;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import okhttp3.FormBody;
import pneumax.websales.BasicAuthInterceptor;

/**
 * Created by masterung on 9/4/2017 AD.
 */

public class GetSalesNameWhere extends AsyncTask<String, Void, String>{

    private Context context;

    public GetSalesNameWhere(Context context) {
        this.context = context;
    }

    @Override
    protected String doInBackground(String... strings) {

        okhttp3.RequestBody data = new FormBody.Builder()
                .add("STFcode",strings[0])
                .add("SAcode", strings[1])
                .add("DPcode", strings[2])
                .build();
        okhttp3.Request.Builder builder = new okhttp3.Request.Builder();
        builder.url(strings[3]).post(data).build();
        okhttp3.Request request = builder.build();

        try {

            okhttp3.OkHttpClient client = new okhttp3.OkHttpClient.Builder()
                    .addInterceptor(new BasicAuthInterceptor())
                    .build();
            okhttp3.Response response = client.newCall(request).execute();
            String result = response.body().string();
            Log.d("4SepV3", "restponse ==> " + result);
            return result;



        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}   // Main Class
