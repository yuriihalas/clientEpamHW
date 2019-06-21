package com.halas.rest;

import com.halas.CopterService;
import com.halas.DataProviders;
import com.halas.factory.CopterFactory;
import com.halas.factory.CopterServiceType;
import com.halas.soap.*;
import com.halas.utils.parser.ParserPolarSystem;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

import static com.halas.consts.ConstCopterCommon.MOVE_COPTER_STEP;
import static org.testng.Assert.*;

public class TestServicesCopters {
    private static final Logger LOG = LogManager.getLogger();
    private CopterServiceType typeService;

    @Factory(dataProvider = "typeServiceData", dataProviderClass = DataProviders.class)
    public TestServicesCopters(CopterServiceType serviceType) {
        LOG.info("Type service: " + serviceType);
        this.typeService = serviceType;
    }

    @Test(dataProvider = "correctCoptersCreateData", dataProviderClass = DataProviders.class)
    public void testCreateCopter(Copter copter) throws DuplicateCopterIdException_Exception {
        LOG.info("testCreateCopter.");
        CopterService copterService = CopterFactory.getService(typeService);
        copterService.createCopter(copter);
        boolean isCopterContainsInServer = copterService.getAllCopters().contains(copter);
        assertTrue(isCopterContainsInServer);
    }

    @Test(dataProvider = "correctCoptersDeleteData", dataProviderClass = DataProviders.class)
    public void testDeleteCopter(int idCopter) throws NoSuchCopterIdException_Exception {
        LOG.info("testDeleteCopter");
        CopterService copterService = CopterFactory.getService(typeService);
        copterService.deleteCopter(idCopter);
        boolean isCopterContainsInServer = copterService.getAllCopters()
                .stream().anyMatch(a -> a.getId().equals(idCopter));
        assertFalse(isCopterContainsInServer);
    }

    @Test(dataProvider = "correctCoptersMoveToPositionByIdData", dataProviderClass = DataProviders.class)
    public void testMoveToPositionById(int idCopter, Position actualPosition)
            throws MaximumDistanceExceededException_Exception, NoSuchCopterIdException_Exception {
        LOG.info("testMoveToPositionById.");
        CopterService copterService = CopterFactory.getService(typeService);
        copterService.moveToPositionById(idCopter, actualPosition);
        Position expectedPosition = copterService.getAllCopters()
                .stream().filter(c -> c.getId() == (idCopter))
                .findFirst().get().getPosition();
        assertEquals(expectedPosition, actualPosition);
    }

    @Test(dataProvider = "correctCoptersMoveToPositionByIdWithDegreeData", dataProviderClass = DataProviders.class)
    public void testMoveToPositionByIdWithDegree(int idCopter, double degreeForMove)
            throws MaximumDistanceExceededException_Exception, NoSuchCopterIdException_Exception {
        LOG.info("testMoveToPositionByIdWithDegree.");
        CopterService copterService = CopterFactory.getService(typeService);
        Copter copter = copterService.findCopter(idCopter);
        double oldX = copter.getPosition().getCoordinateX();
        double oldY = copter.getPosition().getCoordinateY();
        copterService.moveToPositionByIdWithDegree(idCopter, degreeForMove);
        copter.getPosition().setCoordinateX(
                ParserPolarSystem.getCartesianX(MOVE_COPTER_STEP, degreeForMove) + oldX);
        copter.getPosition().setCoordinateY(
                ParserPolarSystem.getCartesianY(MOVE_COPTER_STEP, degreeForMove) + oldY);
        Position expectedPosition = copter.getPosition();
        Position actualPosition = copterService.findCopter(idCopter).getPosition();
        assertEquals(expectedPosition, actualPosition);
    }

    @Test(dataProvider = "correctCoptersMoveUpData", dataProviderClass = DataProviders.class)
    public void testMoveUp(int idCopter)
            throws NoSuchCopterIdException_Exception, MaximumDistanceExceededException_Exception {
        LOG.info("testMoveUp.");
        CopterService copterService = CopterFactory.getService(typeService);
        Copter copter = copterService.findCopter(idCopter);
        double oldZ = copter.getPosition().getCoordinateZ();
        copterService.moveUp(idCopter);
        copter.getPosition().setCoordinateZ(MOVE_COPTER_STEP + oldZ);
        Position expectedPosition = copter.getPosition();
        Position actualPosition = copterService.findCopter(idCopter).getPosition();
        assertEquals(expectedPosition, actualPosition);
    }

    @Test(dataProvider = "correctCoptersMoveDownData", dataProviderClass = DataProviders.class)
    public void testMoveDown(int idCopter)
            throws NoSuchCopterIdException_Exception, MaximumDistanceExceededException_Exception {
        LOG.info("testMoveDown.");
        CopterService copterService = CopterFactory.getService(typeService);
        Copter copter = copterService.findCopter(10);
        double oldZ = copter.getPosition().getCoordinateZ();
        copterService.moveDown(idCopter);
        copter.getPosition().setCoordinateZ(oldZ - MOVE_COPTER_STEP);
        Position expectedPosition = copter.getPosition();
        Position actualPosition = copterService.findCopter(idCopter).getPosition();
        assertEquals(expectedPosition, actualPosition);
    }

    @Test(dataProvider = "correctCoptersHoldPositionData", dataProviderClass = DataProviders.class)
    public void testHoldPosition(int idCopter)
            throws NoSuchCopterIdException_Exception {
        LOG.info("testHoldPosition.");
        CopterService copterService = CopterFactory.getService(typeService);
        Copter copter = copterService.findCopter(idCopter);
        copterService.holdPosition(idCopter);
        Position expectedPosition = copter.getPosition();
        Position actualPosition = copterService.findCopter(idCopter).getPosition();
        assertEquals(expectedPosition, actualPosition);
    }
}