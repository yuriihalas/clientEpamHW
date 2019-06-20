package com.halas.bo;

import com.halas.soap.Copter;

import java.util.List;

public class HelperCopter {
    private HelperCopter() {
    }

    public static boolean findCopterById(List<Copter> copters, int idCopter) {
        return copters.stream().anyMatch(c -> c.getId().equals(idCopter));
    }
}
