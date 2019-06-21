package com.halas.rest;

import com.halas.CopterService;
import com.halas.DataProviders;
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

    @Factory(dataProvider = "typeServiceData", dataProviderClass = DataProviders.class)
    public TestErrorsCopters(CopterServiceType serviceType) {
        LOG.info("Type service: " + serviceType);
        this.typeService = serviceType;
    }

    @Test(dataProvider = "unCorrectCoptersCreateData", dataProviderClass = DataProviders.class,
            expectedExceptions = DuplicateCopterIdException_Exception.class)
    public void testCreateExistCopter(Copter copter) throws DuplicateCopterIdException_Exception {
        LOG.info("testCreateExistsCopter.");
        CopterService copterService = CopterFactory.getService(typeService);
        copterService.createCopter(copter);
    }

    @Test(dataProvider = "unCorrectCoptersDeleteData", dataProviderClass = DataProviders.class,
            expectedExceptions = NoSuchCopterIdException_Exception.class)
    public void testDeleteNotExistCopter(int idCopter) throws NoSuchCopterIdException_Exception {
        LOG.info("testDeleteNotExistCopter.");
        CopterService copterService = CopterFactory.getService(typeService);
        copterService.deleteCopter(idCopter);
    }

    @Test(dataProvider = "unCorrectCoptersMoveToPositionMaxDistanceData", dataProviderClass = DataProviders.class,
            expectedExceptions = MaximumDistanceExceededException_Exception.class)
    public void testMoveToPositionMaximumDistance(int idCopter, Position positionCopter)
            throws NoSuchCopterIdException_Exception, MaximumDistanceExceededException_Exception {
        LOG.info("testMoveToPositionMaximumDistance.");
        CopterService copterService = CopterFactory.getService(typeService);
        copterService.moveToPositionById(idCopter, positionCopter);
    }

    @Test(dataProvider = "unCorrectCoptersMoveToPositionNoIdData", dataProviderClass = DataProviders.class,
            expectedExceptions = NoSuchCopterIdException_Exception.class)
    public void testMoveToPositionWithNoExistId(int idCopter, Position positionCopter)
            throws NoSuchCopterIdException_Exception, MaximumDistanceExceededException_Exception {
        LOG.info("testMoveToPositionWithNoExistId.");
        CopterService copterService = CopterFactory.getService(typeService);
        copterService.moveToPositionById(idCopter, positionCopter);
    }

    @Test(dataProvider = "unCorrectCoptersMoveUpData", dataProviderClass = DataProviders.class,
            expectedExceptions = MaximumDistanceExceededException_Exception.class)
    public void testMoveUpMaximumDistance(int idCopter, Position positionCopter)
            throws NoSuchCopterIdException_Exception, MaximumDistanceExceededException_Exception {
        LOG.info("testMoveUpMaximumDistance.");
        CopterService copterService = CopterFactory.getService(typeService);
        copterService.moveToPositionById(idCopter, positionCopter);
        copterService.moveUp(idCopter);
    }

    @Test(dataProvider = "unCorrectCoptersMoveDownData", dataProviderClass = DataProviders.class,
            expectedExceptions = MaximumDistanceExceededException_Exception.class)
    public void testMoveDownMaximumDistance(int idCopter, Position positionCopter)
            throws NoSuchCopterIdException_Exception, MaximumDistanceExceededException_Exception {
        LOG.info("testMoveDownMaximumDistance.");
        CopterService copterService = CopterFactory.getService(typeService);
        copterService.moveToPositionById(idCopter, positionCopter);
        copterService.moveDown(idCopter);
    }
}
