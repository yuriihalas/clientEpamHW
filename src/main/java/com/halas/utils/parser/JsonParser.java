package com.halas.utils.parser;

import com.halas.utils.parser.data.DataMoveDown;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;


public class JsonParser {
    private static final Logger LOG = LogManager.getLogger(JsonParser.class);
    protected static JSONObject jsonObject;

    static {
        createInstanceJsonObject();
    }

    private static void createInstanceJsonObject(){
        try {
            jsonObject = (JSONObject) (new JSONParser().parse(new FileReader("src/main/resources/data.json")));
        } catch (IOException | ParseException e) {
            LOG.error("Cannot find file json..");
            LOG.error("Class: " + e.getClass());
            LOG.error("Message: " + e.getMessage());
            LOG.error(e.getStackTrace());
        }
    }

    public static void main(String[] args) {
        System.out.println(Arrays.deepToString(DataMoveDown.getCorrectCopters()));
        System.out.println(Arrays.deepToString(DataMoveDown.getUnCorrectCopters()));
    }
}
