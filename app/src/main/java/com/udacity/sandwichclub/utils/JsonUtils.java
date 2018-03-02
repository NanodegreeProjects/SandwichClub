package com.udacity.sandwichclub.utils;

import android.util.Log;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    private static final String S_NAME = "name";
    private static final String S_MAIN_NAME = "mainName";
    private static final String S_ALSO = "alsoKnownAs";
    private static final String S_PLACE_ORIGIN = "placeOfOrigin";
    private static final String S_DESCRIPTION = "description";
    private static final String S_IMAGE = "image";
    private static final String S_INGREDIENTS = "ingredients";

    public static Sandwich parseSandwichJson(String json) {
        String mainName = null;
        String placeOfOrigin = null;
        String description = null;
        String image = null;
        List<String> ingredientsList = new ArrayList<>();
        List<String> alsoKnownAsList = new ArrayList<>();
        try {
            JSONObject sandwichDetail = new JSONObject(json);
            JSONObject name = sandwichDetail.getJSONObject(S_NAME);
            mainName = name.getString(S_MAIN_NAME);
            placeOfOrigin = sandwichDetail.getString(S_PLACE_ORIGIN);
            description = sandwichDetail.getString(S_DESCRIPTION);
            image = sandwichDetail.getString(S_IMAGE);

            alsoKnownAsList = list(name.getJSONArray(S_ALSO));
            ingredientsList = list(sandwichDetail.getJSONArray(S_INGREDIENTS));

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return new Sandwich(mainName, alsoKnownAsList, placeOfOrigin, description, image, ingredientsList);
    }

    private static List<String> list(JSONArray array) {
        List<String> list = new ArrayList<>(0);
        if (array != null) {
            for (int i = 0; i < array.length(); i++) {
                try {
                    list.add(array.getString(i));
                } catch (JSONException e) {
                    Log.e("error", "We have problems!", e);
                }
            }
        }
        return list;
    }
}
