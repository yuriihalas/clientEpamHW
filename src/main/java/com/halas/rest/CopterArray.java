package com.halas.rest;

import com.halas.soap.Copter;

import java.util.ArrayList;
import java.util.List;

public class CopterArray {

    private List<Copter> copters;

    public CopterArray() {
    }

    public List<Copter> getCopters() {
        if (copters == null) {
            copters = new ArrayList<>();
        }
        return this.copters;
    }
}
