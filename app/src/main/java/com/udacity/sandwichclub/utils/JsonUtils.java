package com.udacity.sandwichclub.utils;

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
    private static final String S_DEFAULT = "No data available.";

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
            mainName = name.optString(S_MAIN_NAME, S_DEFAULT);
            placeOfOrigin = sandwichDetail.optString(S_PLACE_ORIGIN, S_DEFAULT);
            description = sandwichDetail.optString(S_DESCRIPTION, S_DEFAULT);
            image = sandwichDetail.optString(S_IMAGE, S_DEFAULT);

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
                list.add(array.optString(i, S_DEFAULT));
            }
        }
        return list;
    }
}
