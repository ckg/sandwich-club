package com.udacity.sandwichclub.utils;

import android.util.Log;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class JsonUtils {

    public static String my_class = JsonUtils.class.getName();

    //json  is provided not from internet but as a string array in res directory
    public static Sandwich parseSandwichJson(String json) {
        try {
            //get root object
            JSONObject sandwichDetails = new JSONObject(json);
            JSONObject name = sandwichDetails.getJSONObject("name");
            String  mainName = name.getString("mainName");
            JSONArray  alsoKnownAs = name.getJSONArray("alsoKnownAs");
            //array as an ArrayList because it is defined as such in Sandwich class
            ArrayList<String> alsoKnownAsList = new ArrayList<>();
            for (int i = 0; i<alsoKnownAs.length(); i++){
                alsoKnownAsList.add(alsoKnownAs.getString(i));
            }
            String  placeOfOrigin = sandwichDetails.getString("placeOfOrigin");
            String  description = sandwichDetails.getString("description");
            String  image = sandwichDetails.getString("image");
            JSONArray ingredients = sandwichDetails.getJSONArray("ingredients");
            ArrayList<String> ingredientsList = new ArrayList<>();
            for (int i = 0; i<ingredients.length(); i++){
                ingredientsList.add(ingredients.getString(i));
            }
            //logs for debugging
            Log.v(my_class, "mainName is: " + mainName);
            Log.v(my_class, "placeOfOrigin is: " + placeOfOrigin);
            Log.v(my_class, "image is: " + image);
            Log.v(my_class, "description is: " + description);

            return new Sandwich(mainName, alsoKnownAsList, placeOfOrigin, description, image, ingredientsList);


        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;

    }
}
/*
<item>{\"name\":{\"mainName\":\"Ham and cheese
        sandwich\",\"alsoKnownAs\":[]},\"placeOfOrigin\":\"\",\"description\":\"A ham and cheese
        sandwich is a common type of sandwich. It is made by putting cheese and sliced ham
        between two slices of bread. The bread is sometimes buttered and/or toasted. Vegetables
        like lettuce, tomato, onion or pickle slices can also be included. Various kinds of
        mustard and mayonnaise are also
        common.\",\"image\":\"https://upload.wikimedia.org/wikipedia/commons/thumb/5/50/Grilled_ham_and_cheese_014.JPG/800px-Grilled_ham_and_cheese_014.JPG\",\"ingredients\":[\"Sliced
        bread\",\"Cheese\",\"Ham\"]}
</item>
*/