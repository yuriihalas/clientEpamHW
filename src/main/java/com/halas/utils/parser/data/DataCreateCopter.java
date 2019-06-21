package com.halas.utils.parser.data;

import com.halas.soap.Copter;
import com.halas.soap.Position;
import com.halas.utils.parser.JsonParser;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class DataCreateCopter extends JsonParser {
    public static Object[][] getCorrectCopters() {
        int amountUsers = getAmountCorrectCopters();
        Object[][] users = new Object[amountUsers][];
        for (int i = 0; i < amountUsers; i++) {
            users[i] = new Object[]{getCopter(i, getCorrectArray())};
        }
        return users;
    }

    public static Object[][] getUnCorrectCopters() {
        int amountUsers = getAmountUnCorrectCopters();
        Object[][] users = new Object[amountUsers][];
        for (int i = 0; i < amountUsers; i++) {
            users[i] = new Object[]{getCopter(i, getUnCorrectArray())};
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
        return (JSONArray) getCreateCopter().get("correct");
    }

    private static JSONArray getUnCorrectArray() {
        return (JSONArray) getCreateCopter().get("unCorrect");
    }

    private static JSONObject getCreateCopter() {
        return (JSONObject) jsonObject.get("createCopter");
    }

    private static Copter getCopter(int index, JSONArray typeCopter) {
        Copter copter = new Copter();
        int idCopter = getIdCopter(index, typeCopter);
        copter.setId(idCopter);
        String nameCopter = getNameCopter(index, typeCopter);
        copter.setName(nameCopter);
        Position possCopter = getPositionByIndex(index, typeCopter);
        copter.setPosition(possCopter);
        return copter;
    }

    private static int getIdCopter(int index, JSONArray typeCopter) {
        return Integer.parseInt(((JSONObject) typeCopter.get(index)).get("id").toString());
    }

    private static String getNameCopter(int index, JSONArray typeCopter) {
        return ((JSONObject) (typeCopter.get(index))).get("name").toString();
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
