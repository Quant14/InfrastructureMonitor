package org.elsys.infrastructuremonitorserver.controller;

import okhttp3.*;

import java.io.IOException;

public class RequestThread extends Thread {
    private String mediaType;
    private String body;
    private String url;
    private String method;

    public RequestThread(String mediaType, String body, String url, String method) {
        this.mediaType = mediaType;
        this.body = body;
        this.url = url;
        this.method = method;
    }

    public void run() {
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        MediaType mediaType = MediaType.parse(this.mediaType);
        RequestBody body = RequestBody.create(mediaType, this.body);
        Request request = new Request.Builder()
                .url(this.url)
                .method(this.method, body)
                .build();
        try {
            Response response = client.newCall(request).execute();
            System.out.println(response.code());
        } catch (IOException e) {
            System.out.println("Request to url failed: " + url);
            throw new RuntimeException(e);
        }
    }

    public String getUrl() {
        return url;
    }
}
