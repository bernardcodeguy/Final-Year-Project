package com.app.dermcop.API;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class OKHttpClientApi {


    public String getUVI() throws IOException {
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url("https://api.openuv.io/api/v1/uv?lat=-33.34&lng=115.342&dt=2018-01-24T10%3A50%3A52.283Z")
                .get()
                .addHeader("x-access-token", "2e7b5210b7e37b83d5883bcfd652e73d")
                .build();


            Response response = client.newCall(request).execute();


            String resp = response.body().string();

        return  resp;
    }







}
