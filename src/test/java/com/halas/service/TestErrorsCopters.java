package com.halas.service;

import com.halas.CopterService;
import com.halas.DataProviderTypeService;
import com.halas.factory.CopterFactory;
import com.halas.factory.CopterServiceType;
import com.halas.soap.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

public class TestErrorsCopters {
    private static final Logger LOG = LogManager.getLogger();
    private CopterServiceType typeService;

    @Factory(dataProvider = "typeServiceData", dataProviderClass = DataProviderTypeService.class)
    public TestErrorsCopters(CopterServiceType serviceType) {
        LOG.info("Type service: " + serviceType);
        this.typeService = serviceType;
    }

    @Test(expectedExceptions = DuplicateCopterIdException_Exception.class)
    public void testCreateExistCopter() throws DuplicateCopterIdException_Exception {
        LOG.info("testCreateExistsCopter.");
        Copter copter = new Copter();
        copter.setId(10);
        copter.setName("");
        copter.setPosition(new Position());
        CopterService copterService = CopterFactory.getService(typeService);
        copterService.createCopter(copter);
    }

    @Test(expectedExceptions = NoSuchCopterIdException_Exception.class)
    public void testDeleteNotExistCopter() throws NoSuchCopterIdException_Exception {
        LOG.info("testDeleteNotExistCopter.");
        CopterService copterService = CopterFactory.getService(typeService);
        copterService.deleteCopter(-1);
    }

    @Test(expectedExceptions = MaximumDistanceExceededException_Exception.class)
    public void testMoveToPositionMaximumDistance()
            throws NoSuchCopterIdException_Exception, MaximumDistanceExceededException_Exception {
        LOG.info("testMoveToPositionMaximumDistance.");
        CopterService copterService = CopterFactory.getService(typeService);
        Position actualPosition = new Position(0, 0, 100);
        copterService.moveToPositionById(10, actualPosition);
    }
}
