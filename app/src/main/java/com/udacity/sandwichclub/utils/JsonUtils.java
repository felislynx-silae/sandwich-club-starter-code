package com.udacity.sandwichclub.utils;

import android.util.Log;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {
    private static final String KEY_NAME = "name";
    private static final String KEY_MAIN_NAME = "mainName";
    private static final String KEY_ALSO_KNOWN_AS = "alsoKnownAs";
    private static final String KEY_PLACE_OF_ORIGIN = "placeOfOrigin";
    private static final String KEY_DESCRIPTION = "description";
    private static final String KEY_IMAGE = "image";
    private static final String KEY_INGREDIENTS = "ingredients";

    public static Sandwich parseSandwichJson(String json) {
        Sandwich sandwich = null;
        try {
            JSONObject rootJsonObject = new JSONObject(json);
            sandwich = new Sandwich();
            JSONObject name = rootJsonObject.getJSONObject(KEY_NAME);
            sandwich.setMainName(name.getString(KEY_MAIN_NAME));
            JSONArray alsoKnownAsJson = name.getJSONArray(KEY_ALSO_KNOWN_AS);
            List<String> alsoKnownAs = new ArrayList<>();
            for (int i = 0; i < alsoKnownAsJson.length(); i++) {
                alsoKnownAs.add(alsoKnownAsJson.getString(i));
            }
            sandwich.setAlsoKnownAs(alsoKnownAs);
            sandwich.setPlaceOfOrigin(rootJsonObject.getString(KEY_PLACE_OF_ORIGIN));
            sandwich.setDescription(rootJsonObject.getString(KEY_DESCRIPTION));
            sandwich.setImage(rootJsonObject.getString(KEY_IMAGE));
            JSONArray ingredientsJson = rootJsonObject.getJSONArray(KEY_INGREDIENTS);
            List<String> ingredients = new ArrayList<>();
            for (int i = 0; i < ingredientsJson.length(); i++) {
                ingredients.add(ingredientsJson.getString(i));
            }
            sandwich.setIngredients(ingredients);
        } catch (JSONException jsonException) {
            Log.e(JsonUtils.class.getName(), "JsonException");
        }
        return sandwich;
    }
}