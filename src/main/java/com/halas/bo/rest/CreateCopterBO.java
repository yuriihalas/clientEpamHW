package com.halas.bo.rest;

import com.halas.soap.Copter;
import com.halas.soap.DuplicateCopterIdException;
import com.halas.soap.NoSuchCopterIdException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;

import java.util.List;

import static com.halas.bo.HelperCopter.findCopterById;

public class CreateCopterBO extends CommonRestBO {
    private static final Logger LOG = LogManager.getLogger(CreateCopterBO.class);

    public void deleteIfExistCopter(Integer idCopter) {
        List<Copter> copters = copterREST.getAllCopters();
        if (findCopterById(copters, idCopter)) {
            try {
                copterREST.deleteCopter(idCopter);
            } catch (NoSuchCopterIdException e) {
                LOG.error(String.format("Don't found copter with id = %d", idCopter));
                e.printStackTrace();
            }
        }
    }

    public void createCopterAndVerifyCreated(Copter copter) throws DuplicateCopterIdException {
        copterREST.createCopter(copter);
        List<Copter> copters = copterREST.getAllCopters();
        Assert.assertTrue(copters.contains(copter));
    }
}
