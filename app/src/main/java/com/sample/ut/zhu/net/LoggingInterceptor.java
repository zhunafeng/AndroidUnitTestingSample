package com.sample.ut.zhu.net;

import android.text.TextUtils;

import com.elvishew.xlog.XLog;

import java.io.IOException;
import java.net.URLDecoder;

import okhttp3.FormBody;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @author Nafeng Zhu
 * Created by Nafeng Zhu on 2016/9/1.
 * log collector
 *
 */
public class LoggingInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        long startTime = System.currentTimeMillis();
        okhttp3.Response response = chain.proceed(chain.request());
        long endTime = System.currentTimeMillis();
        long duration = endTime - startTime;
        okhttp3.MediaType mediaType = response.body().contentType();
        XLog.d("\n");
        XLog.d("----------Start----------------");
        XLog.d("| RequestUrl:" + request.url());
        XLog.d("| RequestHeaders:\n" + request.headers());
        
        String method = request.method();
        if("POST".equals(method)){
            StringBuilder sb = new StringBuilder();
            if (request.body() instanceof FormBody) {
                FormBody body = (FormBody) request.body();
                for (int i = 0; i < body.size(); i++) {
                    sb.append(body.encodedName(i) + ":" + (TextUtils.isEmpty(body.encodedValue(i)) ?
                            "argus null" : URLDecoder.decode(body.encodedValue(i), "utf-8")) + ",");
                }
                if (sb.length() != 0){
                    sb.delete(sb.length() - 1, sb.length());
                }
                XLog.d("| RequestParams:\n");
                try {
                    XLog.json("{" + sb.toString() + "}");
                } catch (Exception e) {
                    XLog.d("{" + sb.toString() + "}");
                }
            }
        }
        XLog.d("| ResponseHeaders:\n" + response.headers());
        String content = response.body().string();
        try {
            XLog.json(content);
        } catch (Exception e) {
            XLog.d(content);
        }
        XLog.d("----------End:" + duration + "millisecond----------");
        return response.newBuilder()
                .body(okhttp3.ResponseBody.create(mediaType, content))
                .build();
    }
}