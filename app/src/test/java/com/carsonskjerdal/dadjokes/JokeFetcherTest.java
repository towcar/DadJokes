package com.carsonskjerdal.dadjokes;

import com.apollographql.apollo.ApolloCall;
import com.apollographql.apollo.api.Response;
import com.apollographql.apollo.exception.ApolloException;
import com.carsonskjerdal.graphql.Jokes;

import org.junit.Test;

import javax.annotation.Nonnull;

import static org.junit.Assert.*;

/**
 * Created by Carson on 5/15/2018.
 * <p>
 * Feel free to use code just give credit please :)
 */
public class JokeFetcherTest {


    @Test
    public void setupJokeTest() {

        MyApolloClient.setupApollo().query(Jokes.builder().build()).enqueue(new ApolloCall.Callback<Jokes.Data>() {
            @Override
            public void onResponse(@Nonnull Response response) {
                assertNotNull(response);
            }

            @Override
            public void onFailure(@Nonnull ApolloException e) {

            }
        });
    }

}