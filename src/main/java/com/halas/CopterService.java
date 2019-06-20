package com.halas;

import com.halas.soap.*;

import java.util.List;

public interface CopterService {
    boolean createCopter(Copter copter) throws DuplicateCopterIdException_Exception;

    List<Copter> getAllCopters();

    boolean deleteCopter(int id) throws NoSuchCopterIdException_Exception;

    boolean moveToPositionById(int id, Position newPosition)
            throws MaximumDistanceExceededException_Exception, NoSuchCopterIdException_Exception;

    boolean moveUp(int idCopter)
            throws MaximumDistanceExceededException_Exception, NoSuchCopterIdException_Exception;

    boolean moveDown(int idCopter)
            throws MaximumDistanceExceededException_Exception, NoSuchCopterIdException_Exception;

    boolean moveToPositionByIdWithDegree(int idCopter, double degree)
            throws MaximumDistanceExceededException_Exception, NoSuchCopterIdException_Exception;

    boolean holdPosition(int idCopter) throws NoSuchCopterIdException_Exception;

    Copter findCopter(int idCopter) throws NoSuchCopterIdException_Exception;
}
