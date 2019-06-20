package com.halas.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.halas.soap.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

import static com.halas.rest.consts.ConstCopterREST.*;

public class CopterREST {
    private static final Logger LOG = LogManager.getLogger(CopterREST.class);

    private RestTemplate restTemplate;
    private ObjectMapper mapper;

    public CopterREST() {
        restTemplate = new RestTemplate();
        mapper = new ObjectMapper();
    }

    public List<Copter> getAllCopters() {
        ResponseEntity<Object> copterResponse = restTemplate.exchange(ALL_COPTERS_URL,
                HttpMethod.GET, null, Object.class);

        if (copterResponse.getStatusCode() != HttpStatus.OK) {
            LOG.warn(EMPTY_LIST_COPTERS);
            return new ArrayList<>();
        }

        return mapper.convertValue(copterResponse.getBody(), CopterArray.class).getCopters();
    }

    public Copter createCopter(Copter copter) throws DuplicateCopterIdException {
        HttpEntity<Copter> requestEntity = new HttpEntity<>(copter);

        ResponseEntity<Object> copterResponse = restTemplate.exchange(CREATE_COPTER_URL,
                HttpMethod.POST, requestEntity, Object.class);

        if (copterResponse.getStatusCode() == HttpStatus.CREATED) {
            LOG.info(SUCCESS_ACTION);
            return mapper.convertValue(copterResponse.getBody(), Copter.class);
        }

        if (copterResponse.getStatusCode() == HttpStatus.SEE_OTHER) {
            LOG.error(String.format(ID_EXIST_BAD_FORMAT, copter.getId()));
            throw new DuplicateCopterIdException();
        }

        //if not expected status
        LOG.fatal(NOT_EXPECTED_STATUS);
        return new Copter();
    }

    public Copter deleteCopter(Integer id) throws NoSuchCopterIdException {
        ResponseEntity<Object> copterResponse = restTemplate.exchange(
                String.format(DELETE_URL, id), HttpMethod.DELETE,
                null, Object.class);

        if (copterResponse.getStatusCode() == HttpStatus.OK) {
            LOG.info(SUCCESS_ACTION);
            return mapper.convertValue(copterResponse.getBody(), Copter.class);
        }

        if (copterResponse.getStatusCode() == HttpStatus.BAD_REQUEST) {
            LOG.error(String.format(FAILURE_FORMAT, ID_NOT_EXISTS));
            throw new NoSuchCopterIdException();
        }

        //if not expected status
        LOG.fatal(NOT_EXPECTED_STATUS);
        return new Copter();
    }


    public Position moveToPositionById(Integer id, Position newPosition)
            throws MaximumDistanceExceededException,
            NoSuchCopterIdException {
        HttpEntity<Position> requestEntity = new HttpEntity<>(newPosition);

        ResponseEntity<Object> positionResponse = restTemplate.exchange(
                String.format(MOVE_TO_POSITION_BY_ID_URL, id), HttpMethod.PUT,
                requestEntity, Object.class);

        return commonChangePosition(positionResponse);
    }

    public Position moveToPositionByIdWithDegree(Integer id, Double degree)
            throws MaximumDistanceExceededException, NoSuchCopterIdException {
        ResponseEntity<Object> positionResponse = restTemplate.exchange(
                String.format(MOVE_TO_POSITION_WITH_DEGREE_URL, id, degree),
                HttpMethod.PUT, null, Object.class);

        return commonChangePosition(positionResponse);
    }

    public Position moveUp(Integer id) throws MaximumDistanceExceededException,
            NoSuchCopterIdException {
        ResponseEntity<Object> positionEntity = restTemplate.exchange(
                String.format(MOVE_UP_URL, id), HttpMethod.PUT,
                null, Object.class);

        return commonChangePosition(positionEntity);
    }

    public Position moveDown(Integer id) throws MaximumDistanceExceededException, NoSuchCopterIdException {
        ResponseEntity<Object> positionEntity = restTemplate.exchange(
                String.format(MOVE_DOWN_URL, id), HttpMethod.PUT,
                null, Object.class);

        return commonChangePosition(positionEntity);
    }

    public Copter holdPosition(Integer id) throws NoSuchCopterIdException {
        ResponseEntity<Object> positionResponse = restTemplate.exchange(
                String.format(HOLD_POSITION_URL, id), HttpMethod.PUT,
                null, Object.class);

        if (positionResponse.getStatusCode() == HttpStatus.OK) {
            LOG.info(SUCCESS_ACTION);
            return mapper.convertValue(positionResponse.getBody(), Copter.class);
        }
        if (positionResponse.getStatusCode() == HttpStatus.BAD_REQUEST) {
            LOG.error(String.format(FAILURE_FORMAT, ID_NOT_EXISTS));
            throw new NoSuchCopterIdException();
        }

        //if not expected status
        LOG.fatal(NOT_EXPECTED_STATUS);
        return new Copter();
    }

    private Position commonChangePosition(ResponseEntity positionResponse) throws MaximumDistanceExceededException, NoSuchCopterIdException {
        if (positionResponse.getStatusCode() == HttpStatus.OK) {
            LOG.info(SUCCESS_ACTION);
            return mapper.convertValue(positionResponse.getBody(), Position.class);
        }
        if (positionResponse.getStatusCode() == HttpStatus.CONFLICT) {
            LOG.error(String.format(FAILURE_FORMAT, MAXIMUM_DISTANCE_EXECUTED));
            throw new MaximumDistanceExceededException();
        }
        if (positionResponse.getStatusCode() == HttpStatus.BAD_REQUEST) {
            LOG.error(String.format(FAILURE_FORMAT, ID_NOT_EXISTS));
            throw new NoSuchCopterIdException();
        }

        //if not expected status
        LOG.fatal(NOT_EXPECTED_STATUS);
        return new Position();
    }
}
