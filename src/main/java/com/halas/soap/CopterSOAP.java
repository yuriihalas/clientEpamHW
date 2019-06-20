package com.halas.soap;

import com.halas.CopterService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class CopterSOAP implements CopterService {
    private static final Logger LOG = LogManager.getLogger(CopterSOAP.class);
    private CopterServiceSOAP serviceSOAP;

    public CopterSOAP() {
        serviceSOAP = new CopterSOAPService().getCopterSOAPPort();
    }

    @Override
    public boolean createCopter(Copter copter) throws DuplicateCopterIdException_Exception {
        LOG.info("method createCopter.");
        return serviceSOAP.addCopter(copter);
    }

    @Override
    public List<Copter> getAllCopters() {
        LOG.info("method getAllCopters.");
        return serviceSOAP.showAllCopters().getItem();
    }

    @Override
    public boolean deleteCopter(int id) throws NoSuchCopterIdException_Exception {
        LOG.info("method deleteCopter.");
        return serviceSOAP.deleteCopterById(id);
    }

    @Override
    public boolean moveToPositionById(int id, Position newPosition) throws
            MaximumDistanceExceededException_Exception,
            NoSuchCopterIdException_Exception {
        LOG.info("method moveToPositionById.");
        return serviceSOAP.moveToPositionById(id, newPosition);
    }

    @Override
    public boolean moveUp(int idCopter) throws
            MaximumDistanceExceededException_Exception,
            NoSuchCopterIdException_Exception {
        LOG.info("method moveUp.");
        return serviceSOAP.goUp(idCopter);
    }

    @Override
    public boolean moveDown(int idCopter) throws
            MaximumDistanceExceededException_Exception,
            NoSuchCopterIdException_Exception {
        LOG.info("method moveDown.");
        return serviceSOAP.goDown(idCopter);
    }

    @Override
    public boolean moveToPositionByIdWithDegree(int idCopter, double degree) throws
            MaximumDistanceExceededException_Exception,
            NoSuchCopterIdException_Exception {
        LOG.info("method moveToPositionByIdWithDegree.");
        return serviceSOAP.goByDegree(idCopter, degree);
    }

    @Override
    public boolean holdPosition(int idCopter) throws NoSuchCopterIdException_Exception {
        LOG.info("method holdPosition.");
        return serviceSOAP.holdPosition(idCopter);
    }

    @Override
    public Copter findCopter(int idCopter) throws NoSuchCopterIdException_Exception {
        LOG.info("method findCopter.");
        return serviceSOAP.findCopter(idCopter);
    }
}
