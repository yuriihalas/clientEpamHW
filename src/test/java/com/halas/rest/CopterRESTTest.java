package com.halas.rest;

import com.halas.bo.rest.CreateCopterBO;
import com.halas.soap.Copter;
import com.halas.soap.DuplicateCopterIdException;
import com.halas.soap.Position;
import org.testng.annotations.Test;


public class CopterRESTTest {

    @Test
    public void testGetAllCopters() {
    }

    @Test
    public void testCreateCopter() throws DuplicateCopterIdException {
        Copter copter = new Copter();
        copter.setId(33);
        copter.setName("Peterson");
        copter.setPosition(new Position());

        CreateCopterBO createCopterBO = new CreateCopterBO();
        createCopterBO.createCopterAndVerifyCreated(copter);
        createCopterBO.deleteIfExistCopter(copter.getId());
    }

    @Test
    public void testDeleteCopter() {
    }

    @Test
    public void testMoveToPositionById() {
    }

    @Test
    public void testMoveToPositionByIdWithDegree() {
    }

    @Test
    public void testMoveUp() {
    }

    @Test
    public void testMoveDown() {
    }

    @Test
    public void testHoldPosition() {
    }
}