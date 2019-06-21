package com.halas.rest;

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
        copter.setId(1);
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
        Position position = new Position(0, 0, 100);
        copterService.moveToPositionById(1, position);
    }

    @Test(expectedExceptions = NoSuchCopterIdException_Exception.class)
    public void testMoveToPositionWithNoExistId()
            throws NoSuchCopterIdException_Exception, MaximumDistanceExceededException_Exception {
        LOG.info("testMoveToPositionWithNoExistId.");
        CopterService copterService = CopterFactory.getService(typeService);
        Position position = new Position(0, 0, 0);
        copterService.moveToPositionById(-1, position);
    }

    @Test(expectedExceptions = MaximumDistanceExceededException_Exception.class)
    public void testMoveUpMaximumDistance()
            throws NoSuchCopterIdException_Exception, MaximumDistanceExceededException_Exception {
        LOG.info("testMoveUpMaximumDistance.");
        CopterService copterService = CopterFactory.getService(typeService);
        copterService.moveToPositionById(1, new Position(0, 0, 90));
        copterService.moveUp(1);
    }

    @Test(expectedExceptions = MaximumDistanceExceededException_Exception.class)
    public void testMoveDownMaximumDistance()
            throws NoSuchCopterIdException_Exception, MaximumDistanceExceededException_Exception {
        LOG.info("testMoveDownMaximumDistance.");
        CopterService copterService = CopterFactory.getService(typeService);
        copterService.moveToPositionById(2, new Position(0, 0, -90));
        copterService.moveDown(2);
    }
}
