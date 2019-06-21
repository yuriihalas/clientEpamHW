package com.halas.utils.parser.data;

import com.halas.utils.parser.JsonParser;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class DataMoveToPositionByIdWithDegree extends JsonParser {
    public static Object[][] getCorrectCopters() {
        int amountUsers = getAmountCorrectCopters();
        Object[][] users = new Object[amountUsers][];
        for (int i = 0; i < amountUsers; i++) {
            users[i] = new Object[]{
                    getIdCopter(i, getCorrectArray()),
                    getDegreeCopter(i, getCorrectArray())};
        }
        return users;
    }

    private static int getAmountCorrectCopters() {
        return (getCorrectArray()).size();
    }

    private static JSONArray getCorrectArray() {
        return (JSONArray) getMoveToPositionByIdData().get("correct");
    }

    private static JSONObject getMoveToPositionByIdData() {
        return (JSONObject) jsonObject.get("moveToPositionByIdWithDegree");
    }

    private static int getIdCopter(int index, JSONArray typeCopter) {
        return Integer.parseInt(((JSONObject) typeCopter.get(index)).get("id").toString());
    }

    private static double getDegreeCopter(int index, JSONArray typeCopter) {
        return Double.parseDouble(((JSONObject) typeCopter.get(index)).get("degree").toString());
    }
}
