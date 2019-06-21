package com.halas.utils.parser.data;

import com.halas.utils.parser.JsonParser;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class DataDeleteCopter extends JsonParser {

    public static Object[][] getCorrectCopters() {
        int amountUsers = getAmountCorrectCopters();
        Object[][] users = new Object[amountUsers][];
        for (int i = 0; i < amountUsers; i++) {
            users[i] = new Object[]{getIdCopter(i, getCorrectArray())};
        }
        return users;
    }

    public static Object[][] getUnCorrectCopters() {
        int amountUsers = getAmountUnCorrectCopters();
        Object[][] users = new Object[amountUsers][];
        for (int i = 0; i < amountUsers; i++) {
            users[i] = new Object[]{getIdCopter(i, getUnCorrectArray())};
        }
        return users;
    }

    private static int getAmountCorrectCopters() {
        return (getCorrectArray()).size();
    }

    private static int getAmountUnCorrectCopters() {
        return (getUnCorrectArray()).size();
    }

    private static JSONArray getCorrectArray() {
        return (JSONArray) getDeleteCopter().get("correct");
    }

    private static JSONArray getUnCorrectArray() {
        return (JSONArray) getDeleteCopter().get("unCorrect");
    }

    private static JSONObject getDeleteCopter() {
        return (JSONObject) jsonObject.get("deleteCopter");
    }

    private static int getIdCopter(int index, JSONArray typeCopter) {
        return Integer.parseInt(((JSONObject) typeCopter.get(index)).get("id").toString());
    }
}
