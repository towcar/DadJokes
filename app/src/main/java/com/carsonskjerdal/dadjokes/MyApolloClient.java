package com.carsonskjerdal.dadjokes;

import com.apollographql.apollo.ApolloClient;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;


/**
 * Created by Carson on 5/12/2018.
 * <p>
 * Feel free to use code just give credit please :)
 */
public class MyApolloClient {

    private static ApolloClient client;
    private static final String BASE_URL = "https://icanhazdadjoke.com/graphql";


    public static ApolloClient setupApollo() {

        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient httpClient = new OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor)
                .build();

        client = ApolloClient.builder()
                .serverUrl(BASE_URL)
                .okHttpClient(httpClient)
                .build();


        return client;
    }

}

