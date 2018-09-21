package com.dipalimvpsample.mvparchitecture.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.dipalimvpsample.mvparchitecture.BuildConfig;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.util.concurrent.TimeUnit;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Mitesh on 2/4/2017
 * Create OBject of Retrofit for Web service call.
 */

public final class RetrofitSingleton {

    private static final String CONTENTYPE = "application/json";
    private static final String NST = "ruqbkxxG2XIpjOx2SQahhMrXQ4ivch7ZCUs8rt4PVYBszFLBdaRK9xAa_PlUNNMSD-ct1QMcWgsQ";
    private static volatile RetrofitSingleton instance;

    private RetrofitSingleton() {
    }

    public static RetrofitSingleton getInstance() {
        if (instance == null) {
            instance = new RetrofitSingleton();
        }
        return instance;
    }

    public static OkHttpClient.Builder getUnsafeOkHttpClient() {

        try {
            // Create a trust manager that does not validate certificate chains
            final TrustManager[] trustAllCerts = new TrustManager[]{
                    new X509TrustManager() {
                        @Override
                        public void checkClientTrusted(java.security.cert.X509Certificate[] chain, String authType) {
                        }

                        @Override
                        public void checkServerTrusted(java.security.cert.X509Certificate[] chain, String authType) {
                        }

                        @Override
                        public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                            return new java.security.cert.X509Certificate[]{};
                        }
                    }
            };

            // Install the all-trusting trust manager
            final SSLContext sslContext = SSLContext.getInstance("SSL");
            sslContext.init(null, trustAllCerts, new java.security.SecureRandom());

            // Create an ssl socket factory with our all-trusting manager
            final SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();
            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            builder.sslSocketFactory(sslSocketFactory, (X509TrustManager) trustAllCerts[0]);
            builder.hostnameVerifier((hostname, session) -> true);
            builder.connectTimeout(80, TimeUnit.SECONDS)
                    .addInterceptor(chain -> {
                        Request original = chain.request();

                        Request request = original.newBuilder()
                                .header("Content-Type", CONTENTYPE)
                                .header("Accept", CONTENTYPE)
                                .header("NST", NST)
                                /* .header("Token", OnBoardingActivity
                                        .loginInfoDto.getAuthToken())*/
                                .method(original.method(), original.body())
                                .build();
                        return chain.proceed(request);
                    });

            return builder;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }


    private static GsonConverterFactory provideGsonConverterFactory() {
        return GsonConverterFactory.create(provideGson());
    }

    private static Gson provideGson() {
        return new GsonBuilder().setLenient().create();
    }

    private static RxJava2CallAdapterFactory provideRxJavaCallAdapterFactory() {
        return RxJava2CallAdapterFactory.create();
    }

    private Retrofit provideRetrofit() {
        return new Retrofit.Builder()
                .baseUrl(BuildConfig.SERVER_URL)
                .addConverterFactory(provideGsonConverterFactory())
                .addCallAdapterFactory(provideRxJavaCallAdapterFactory())
                .client(getUnsafeOkHttpClient().build())
                .build();
    }

    public IMyWebAPI provideApiService() {
        return provideRetrofit().create(IMyWebAPI.class);
    }

}
