package com.carsonskjerdal.dadjokes;

import android.util.Log;

import com.apollographql.apollo.ApolloCall;
import com.apollographql.apollo.api.Response;
import com.apollographql.apollo.exception.ApolloException;
import com.carsonskjerdal.graphql.Jokes;

import javax.annotation.Nonnull;

/**
 * Created by Carson on 5/15/2018.
 * <p>
 * Feel free to use code just give credit please :)
 */
public class JokeFetcher {

    public static String jokeText;

    public static void setupJoke() {

        MyApolloClient.setupApollo().query(Jokes.builder().build()).enqueue(new ApolloCall.Callback<Jokes.Data>() {
            @Override
            public void onResponse(@Nonnull Response response) {
                //pull the response and pass into a string
                Jokes.Data responseData = (Jokes.Data) response.data();
                jokeText = responseData.joke().joke();
                Log.e("setupJoke","Joke Setup:" + jokeText);
            }

            @Override
            public void onFailure(@Nonnull ApolloException e) {
                //if failed set text for user to receive
                jokeText = "Failed to receive joke.. try again shortly";
            }
        });
    }
}
