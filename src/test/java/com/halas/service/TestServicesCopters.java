package com.halas.service;

import com.halas.CopterService;
import com.halas.DataProviderTypeService;
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

    @Factory(dataProvider = "typeServiceData", dataProviderClass = DataProviderTypeService.class)
    public TestServicesCopters(CopterServiceType serviceType) {
        LOG.info("Type service: " + serviceType);
        this.typeService = serviceType;
    }

    @Test
    public void testCreateCopter() throws DuplicateCopterIdException_Exception {
        LOG.info("testCreateCopter.");
        Copter copter = new Copter();
        copter.setId(23);
        copter.setName("Peterson");
        copter.setPosition(new Position());
        CopterService copterService = CopterFactory.getService(typeService);
        copterService.createCopter(copter);
        boolean isCopterContainsInServer = copterService.getAllCopters().contains(copter);
        assertTrue(isCopterContainsInServer);
    }

    @Test
    public void testDeleteCopter() throws NoSuchCopterIdException_Exception {
        LOG.info("testDeleteCopter");
        CopterService copterService = CopterFactory.getService(typeService);
        copterService.deleteCopter(23);
        boolean isCopterContainsInServer = copterService.getAllCopters()
                .stream().anyMatch(a -> a.getId().equals(23));
        assertFalse(isCopterContainsInServer);
    }

    @Test
    public void testMoveToPositionById()
            throws MaximumDistanceExceededException_Exception, NoSuchCopterIdException_Exception {
        LOG.info("testMoveToPositionById.");
        CopterService copterService = CopterFactory.getService(typeService);
        Position actualPosition = new Position(0, 0, 33);
        copterService.moveToPositionById(10, actualPosition);
        Position expectedPosition = copterService.getAllCopters()
                .stream().filter(c -> c.getId() == 10)
                .findFirst().get().getPosition();
        assertEquals(expectedPosition, actualPosition);
    }

    @Test
    public void testMoveToPositionByIdWithDegree()
            throws MaximumDistanceExceededException_Exception, NoSuchCopterIdException_Exception {
        LOG.info("testMoveToPositionByIdWithDegree.");
        CopterService copterService = CopterFactory.getService(typeService);
        Copter copter = copterService.findCopter(10);
        double oldX = copter.getPosition().getCoordinateX();
        double oldY = copter.getPosition().getCoordinateY();
        copterService.moveToPositionByIdWithDegree(10, 90.0);
        copter.getPosition().setCoordinateX(
                ParserPolarSystem.getCartesianX(MOVE_COPTER_STEP, 90.0) + oldX);
        copter.getPosition().setCoordinateY(
                ParserPolarSystem.getCartesianY(MOVE_COPTER_STEP, 90.0) + oldY);
        Position expectedPosition = copter.getPosition();
        Position actualPosition = copterService.findCopter(10).getPosition();
        assertEquals(expectedPosition, actualPosition);
    }

    @Test
    public void testMoveUp()
            throws NoSuchCopterIdException_Exception, MaximumDistanceExceededException_Exception {
        LOG.info("testMoveUp.");
        CopterService copterService = CopterFactory.getService(typeService);
        Copter copter = copterService.findCopter(10);
        double oldZ = copter.getPosition().getCoordinateZ();
        copterService.moveUp(10);
        copter.getPosition().setCoordinateZ(MOVE_COPTER_STEP + oldZ);
        Position expectedPosition = copter.getPosition();
        Position actualPosition = copterService.findCopter(10).getPosition();
        assertEquals(expectedPosition, actualPosition);
    }

    @Test
    public void testMoveDown()
            throws NoSuchCopterIdException_Exception, MaximumDistanceExceededException_Exception {
        LOG.info("testMoveDown.");
        CopterService copterService = CopterFactory.getService(typeService);
        Copter copter = copterService.findCopter(10);
        double oldZ = copter.getPosition().getCoordinateZ();
        copterService.moveDown(10);
        copter.getPosition().setCoordinateZ(oldZ - MOVE_COPTER_STEP);
        Position expectedPosition = copter.getPosition();
        Position actualPosition = copterService.findCopter(10).getPosition();
        assertEquals(expectedPosition, actualPosition);
    }

    @Test
    public void testHoldPosition()
            throws NoSuchCopterIdException_Exception {
        LOG.info("testHoldPosition.");
        CopterService copterService = CopterFactory.getService(typeService);
        Copter copter = copterService.findCopter(10);
        copterService.holdPosition(10);
        Position expectedPosition = copter.getPosition();
        Position actualPosition = copterService.findCopter(10).getPosition();
        assertEquals(expectedPosition, actualPosition);
    }
}