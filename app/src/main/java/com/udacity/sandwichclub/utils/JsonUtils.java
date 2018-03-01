package com.udacity.sandwichclub.utils;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {


    public static Sandwich parseSandwichJson(String json) {
        String mainName = null;
        String placeOfOrigin = null;
        String description = null;
        String image = null;
        List<String> ingredientsList = new ArrayList<>();
        List<String> alsoKnownAsList = new ArrayList<>();
        try {
            JSONObject sandwichDetail = new JSONObject(json);
            JSONObject name = sandwichDetail.getJSONObject("name");
            mainName = name.getString("mainName");
            placeOfOrigin = sandwichDetail.getString("placeOfOrigin");
            description = sandwichDetail.getString("description");
            image = sandwichDetail.getString("image");

            JSONArray alsoKnownAsArray = name.getJSONArray("alsoKnownAs");
            alsoKnownAsList = new ArrayList<>();
            for (int i = 0; i < alsoKnownAsArray.length(); i++) {
                String alsoKnownAs = alsoKnownAsArray.getString(i);
                alsoKnownAsList.add(alsoKnownAs);
            }

            JSONArray ingredientsArray = sandwichDetail.getJSONArray("ingredients");
            ingredientsList = new ArrayList<>();
            for (int i = 0; i < ingredientsArray.length(); i++) {
                String ingredient = ingredientsArray.getString(i);
                ingredientsList.add(ingredient);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return new Sandwich(mainName, alsoKnownAsList, placeOfOrigin, description, image, ingredientsList);

    }
}
