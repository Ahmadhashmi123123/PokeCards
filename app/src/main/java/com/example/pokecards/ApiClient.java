package com.example.pokecards;

import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public class ApiClient {

    private static final String BASE_URL = "https://api.pokemontcg.io/v2/";
    private static final String API_KEY = "f4533d8b-2981-4ca7-83f0-de62d6592282";

    public interface PokemonApi {
        @GET("cards")
        Call<CardResponse> getCards(
                @Query("pageSize") int pageSize,
                @Query("page") int page
        );
    }

    public static PokemonApi getApi() {

        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .addInterceptor(chain -> {
                    Request request = chain.request().newBuilder()
                            .addHeader("X-Api-Key", API_KEY)
                            .addHeader("User-Agent", "pokecards-android")
                            .build();
                    return chain.proceed(request);
                })
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(PokemonApi.class);
    }

    public static class CardResponse {
        public List<Data> data;

        public static class Data {
            public String name;
            public Images images;

            public static class Images {
                public String small;
            }
        }
    }
}
