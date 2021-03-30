package com.example.myapplication.UtilImages;

import android.content.Context;
import android.graphics.Picture;
import android.widget.ImageView;

import com.example.myapplication.R;
import com.pixplicity.sharp.Sharp;

import java.io.IOException;
import java.io.InputStream;

import okhttp3.Cache;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class util {
    private static OkHttpClient httpClient;

    public static void fetchSvg(Context context, String url, final ImageView target) {
        if (httpClient == null) {
            // Use cache for performance and basic offline capability
            httpClient = new OkHttpClient.Builder()
                    .cache(new Cache(context.getCacheDir(), 5 * 1024 * 1014))
                    .build();
        }

        final Request request = new Request.Builder().url(url).build();
        httpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
              //  target.setImageDrawable(R.drawable.ic_launcher_background);
                target.setImageResource(R.drawable.not_availa);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
              try {
                  InputStream stream = response.body().byteStream();
                  Sharp.loadInputStream(stream).into(target);

                  stream.close();
                  //  call.cancel();
                  response.body().close();
                  response.close();
              }
              catch(IllegalStateException e){
                  System.out.println(e.getMessage());
              }
              catch (Exception  e){
                  System.out.println(e.getMessage());
                  System.out.println(e.getStackTrace());
                  
              }
            }
        });
    }
}
