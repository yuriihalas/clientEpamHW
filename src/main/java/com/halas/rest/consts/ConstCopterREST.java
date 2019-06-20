package com.halas.rest.consts;

public class ConstCopterREST {
    public static final String SUCCESS_ACTION = "The action was SUCCESSFUL.";
    public static final String ID_EXIST_BAD_FORMAT = "The action was FAILURE, because this ID:\"%s\" already exists.";
    public static final String FAILURE_FORMAT = "ERROR.\nThe action was FAILURE,\n%s";
    public static final String ID_NOT_EXISTS = "Id not exist.";
    public static final String MAXIMUM_DISTANCE_EXECUTED = "Maximum Distance will be executed, can't move so far!\n";
    public static final String EMPTY_LIST_COPTERS = "There are no copters!";
    public static final String NOT_EXPECTED_STATUS = "Not expected status!";

    public static final String DEFAULT_URL = "http://localhost:9966/api/copters/";
    public static final String ALL_COPTERS_URL = DEFAULT_URL;
    public static final String CREATE_COPTER_URL = DEFAULT_URL;
    public static final String DELETE_URL = DEFAULT_URL + "%d";
    public static final String MOVE_TO_POSITION_BY_ID_URL = DEFAULT_URL + "%d";
    public static final String MOVE_TO_POSITION_WITH_DEGREE_URL = DEFAULT_URL + "%d/%f";
    public static final String MOVE_UP_URL = DEFAULT_URL + "%d/move-up";
    public static final String MOVE_DOWN_URL = DEFAULT_URL + "%d/move-down";
    public static final String HOLD_POSITION_URL = DEFAULT_URL + "%d/hold-position";

    private ConstCopterREST() {
    }
}
