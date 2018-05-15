package com.carsonskjerdal.dadjokes;

import com.apollographql.apollo.ApolloClient;

import org.junit.Test;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

import static org.junit.Assert.*;

/**
 * Created by Carson on 5/15/2018.
 * <p>
 * Feel free to use code just give credit please :)
 */
public class MyApolloClientTest {

    private static ApolloClient client;
    private static final String BASE_URL = "https://icanhazdadjoke.com/graphql";


    @Test
    public void setupApolloTest() {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient httpClient = new OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor)
                .build();

        client = ApolloClient.builder()
                .serverUrl(BASE_URL)
                .okHttpClient(httpClient)
                .build();

        assertNotNull(client);
    }
}