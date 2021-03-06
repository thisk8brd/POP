package com.example.thiago.myapplication;

import android.content.Context;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public final class JSONUtils {

    public static ArrayList<Movie> getJSONFromString(Context context, String jsonStr) throws JSONException {

        String[] parsedData = null;

        String VOTE_COUNT;
        String ID;
        String VIDEO;
        String VOTE_AVERAGE;
        String TITLE;
        String POPULARITY;
        String POSTER_PATH;
        String ORIGINAL_LANGUAGE;
        String ORIGINAL_TITLE;
        Boolean isAdult;
        String OVERVIEW;
        String RELEASE_DATE;

        JSONObject json = new JSONObject(jsonStr);
        JSONArray jsonArray = json.getJSONArray("results");

        //Movie[] movieArray = new Movie[jsonArray.length()];
        ArrayList<Movie> movieArrayList = new ArrayList<>();

        for(int i = 0; i < jsonArray.length(); i++){

            JSONObject movie = jsonArray.getJSONObject(i);

            //parsedData[i] = movie.getString("title");


            Movie theMovie = new Movie(
                    movie.getString("vote_count"),
                    movie.getInt("id"),
                    movie.getString("video"),
                    movie.getString("vote_average"),
                    movie.getString("title"),
                    movie.getString("popularity"),
                    movie.getString("poster_path"),
                    movie.getString("original_language"),
                    movie.getString("original_title"),
                    movie.getBoolean("adult"),
                    movie.getString("overview"),
                    movie.getString("release_date"));

            movieArrayList.add(theMovie);

        }

        return movieArrayList;
    }

    public static ArrayList<String> getVideoJSONFromString(Context context, String jsonStr) throws JSONException {


        Log.i("url",jsonStr);

        JSONObject json = new JSONObject(jsonStr);
        JSONArray jsonArray = json.getJSONArray("results");

        ArrayList<String> videoArrayList = new ArrayList<>();

        if(jsonArray.length() > 0) {
            for (int i = 0; i < jsonArray.length(); i++) {

                JSONObject videoKey = jsonArray.getJSONObject(i);
                videoArrayList.add(videoKey.getString("key"));

            }
            return videoArrayList;
        }else{
            return null;
        }


    }

    public static ArrayList<Review> getReviewsFromString(Context context, String jsonStr) throws JSONException {


        Log.i("url",jsonStr);

        JSONObject json = new JSONObject(jsonStr);
        JSONArray jsonArray = json.getJSONArray("results");

        ArrayList<Review> reviewArrayList = new ArrayList<>();
        Review review;

        if(jsonArray.length() != 0) {
            for (int i = 0; i < jsonArray.length(); i++) {

                JSONObject reviewObj = jsonArray.getJSONObject(i);
                review = new Review(reviewObj.getString("id"),
                        reviewObj.getString("author"),
                        reviewObj.getString("content"),
                        reviewObj.getString("url"));
                reviewArrayList.add(review);

            }
        }else{
            reviewArrayList = null;
        }

        return reviewArrayList;
    }

}
