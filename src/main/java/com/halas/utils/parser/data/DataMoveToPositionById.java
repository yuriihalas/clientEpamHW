package com.halas.utils.parser.data;

import com.halas.soap.Position;
import com.halas.utils.parser.JsonParser;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class DataMoveToPositionById extends JsonParser {
    public static Object[][] getCorrectCopters() {
        int amountUsers = getAmountCorrectCopters();
        Object[][] users = new Object[amountUsers][];
        for (int i = 0; i < amountUsers; i++) {
            users[i] = new Object[]{
                    getIdCopter(i, getCorrectArray()),
                    getPositionByIndex(i, getCorrectArray())};
        }
        return users;
    }

    public static Object[][] getUnCorrectNoSuchIdCopters() {
        int amountUsers = getAmountUnCorrectNoSuchIdCopters();
        Object[][] users = new Object[amountUsers][];
        for (int i = 0; i < amountUsers; i++) {
            users[i] = new Object[]{
                    getIdCopter(i, getNoSuchIdArray()),
                    getPositionByIndex(i, getNoSuchIdArray())};
        }
        return users;
    }

    public static Object[][] getUnCorrectMaximumDistanceCopters() {
        int amountUsers = getAmountUnCorrectMaximumDistanceCopters();
        Object[][] users = new Object[amountUsers][];
        for (int i = 0; i < amountUsers; i++) {
            users[i] = new Object[]{
                    getIdCopter(i, getMaximumDistanceArray()),
                    getPositionByIndex(i, getMaximumDistanceArray())};
        }
        return users;
    }

    private static int getAmountCorrectCopters() {
        return (getCorrectArray()).size();
    }

    private static int getAmountUnCorrectNoSuchIdCopters() {
        return (getNoSuchIdArray()).size();
    }

    private static int getAmountUnCorrectMaximumDistanceCopters() {
        return (getMaximumDistanceArray()).size();
    }

    private static JSONArray getCorrectArray() {
        return (JSONArray) getMoveToPositionByIdData().get("correct");
    }

    private static JSONObject getUnCorrectArray() {
        return (JSONObject) getMoveToPositionByIdData().get("unCorrect");
    }

    private static JSONArray getMaximumDistanceArray() {
        return (JSONArray) getUnCorrectArray().get("maximumDistance");
    }

    private static JSONArray getNoSuchIdArray() {
        return (JSONArray) getUnCorrectArray().get("noSuchId");
    }

    private static JSONObject getMoveToPositionByIdData() {
        return (JSONObject) jsonObject.get("moveToPositionById");
    }

    private static int getIdCopter(int index, JSONArray typeCopter) {
        return Integer.parseInt(((JSONObject) typeCopter.get(index)).get("id").toString());
    }

    private static Position getPositionByIndex(int index, JSONArray typeCopter) {
        double posX = Double.parseDouble(getPosition(index, "coordinateX", typeCopter));
        double posY = Double.parseDouble(getPosition(index, "coordinateY", typeCopter));
        double posZ = Double.parseDouble(getPosition(index, "coordinateZ", typeCopter));
        return new Position(posX, posY, posZ);
    }

    private static String getPosition(int index, String poss, JSONArray typeCopter) {
        return ((JSONObject) (((JSONObject) typeCopter.get(index))).get("position")).get(poss).toString();
    }
}
