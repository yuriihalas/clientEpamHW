package com.halas.soap;

import com.halas.CopterService;

import java.util.List;

public class CopterSOAP implements CopterService {
    private CopterServiceSOAP serviceSOAP;

    public CopterSOAP() {
        serviceSOAP = new CopterSOAPService().getCopterSOAPPort();
    }

    @Override
    public boolean createCopter(Copter copter) throws DuplicateCopterIdException_Exception {
        return serviceSOAP.addCopter(copter);
    }

    @Override
    public List<Copter> getAllCopters() {
        return serviceSOAP.showAllCopters().getItem();
    }

    @Override
    public boolean deleteCopter(int id) throws NoSuchCopterIdException_Exception {
        return serviceSOAP.deleteCopterById(id);
    }

    @Override
    public boolean moveToPositionById(int id, Position newPosition) throws MaximumDistanceExceededException_Exception, NoSuchCopterIdException_Exception {
        return serviceSOAP.moveToPositionById(id, newPosition);
    }

    @Override
    public boolean moveUp(int idCopter) throws MaximumDistanceExceededException_Exception, NoSuchCopterIdException_Exception {
        return serviceSOAP.goUp(idCopter);
    }

    @Override
    public boolean moveDown(int idCopter) throws MaximumDistanceExceededException_Exception, NoSuchCopterIdException_Exception {
        return serviceSOAP.goDown(idCopter);
    }

    @Override
    public boolean moveToPositionByIdWithDegree(int idCopter, double degree) throws MaximumDistanceExceededException_Exception, NoSuchCopterIdException_Exception {
        return serviceSOAP.goByDegree(idCopter, degree);
    }

    @Override
    public boolean holdPosition(int idCopter) throws NoSuchCopterIdException_Exception {
        return serviceSOAP.holdPosition(idCopter);
    }

    @Override
    public Copter findCopter(int idCopter) throws NoSuchCopterIdException_Exception {
        return serviceSOAP.findCopter(idCopter);
    }
}
