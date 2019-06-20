package com.halas.rest;

import com.halas.soap.Copter;
import com.halas.soap.DuplicateCopterIdException;
import com.halas.soap.NoSuchCopterIdException;
import com.halas.soap.Position;
import org.testng.annotations.Test;

public class serviceREST {

    @Test
     public void testGetAllUsers(){
        CopterREST copterREST = new CopterREST();
        System.out.println(copterREST.getAllCopters());
    }

    @Test
    public void testCreatingCopter() throws DuplicateCopterIdException {
        CopterREST copterREST = new CopterREST();

        Copter copter = new Copter();
        copter.setId(44);
        copter.setName("");
        copter.setPosition(new Position());

        System.out.println(copterREST.createCopter(copter));
    }

    @Test
    public void testDeletingCopter() throws NoSuchCopterIdException {
        CopterREST copterREST = new CopterREST();

        System.out.println(copterREST.deleteCopter(44));
    }
}
