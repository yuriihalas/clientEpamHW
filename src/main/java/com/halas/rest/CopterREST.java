package com.halas.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.halas.CopterService;
import com.halas.soap.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

import static com.halas.rest.consts.ConstCopterREST.*;

public class CopterREST implements CopterService {
    private static final Logger LOG = LogManager.getLogger(CopterREST.class);

    private RestTemplate restTemplate;
    private ObjectMapper mapper;

    public CopterREST() {
        restTemplate = new RestTemplate();
        mapper = new ObjectMapper();
    }

    public List<Copter> getAllCopters() {
        LOG.info("method getAllCopters.");
        ResponseEntity<Object> copterResponse = restTemplate.exchange(GET_ALL_COPTERS_URL,
                HttpMethod.GET, null, Object.class);
        if (copterResponse.getStatusCode() != HttpStatus.OK) {
            LOG.warn(EMPTY_LIST_COPTERS);
            return new ArrayList<>();
        }
        LOG.info(SUCCESS_ACTION);
        return mapper.convertValue(copterResponse.getBody(), CopterArray.class).getCopters();
    }

    public boolean createCopter(Copter copter) throws DuplicateCopterIdException_Exception {
        LOG.info("method createCopter.");
        HttpEntity<Copter> requestEntity = new HttpEntity<>(copter);
        ResponseEntity<Object> copterResponse = restTemplate.exchange(CREATE_COPTER_URL,
                HttpMethod.POST, requestEntity, Object.class);
        if (copterResponse.getStatusCode() == HttpStatus.CREATED) {
            LOG.info(SUCCESS_ACTION);
            return true;
        }
        if (copterResponse.getStatusCode() == HttpStatus.SEE_OTHER) {
            LOG.error(String.format(ID_EXIST_BAD_FORMAT, copter.getId()));
            throw new DuplicateCopterIdException_Exception();
        }
        LOG.fatal(NOT_EXPECTED_STATUS);
        return false;
    }

    public boolean deleteCopter(int id) throws NoSuchCopterIdException_Exception {
        LOG.info("method deleteCopter.");
        try {
            ResponseEntity<Object> copterResponse = restTemplate.exchange(
                    String.format(DELETE_URL, id), HttpMethod.DELETE,
                    null, Object.class);
            if (copterResponse.getStatusCode() == HttpStatus.OK) {
                LOG.info(SUCCESS_ACTION);
                return true;
            }
        } catch (HttpClientErrorException e) {
            if (e.getStatusCode() == HttpStatus.BAD_REQUEST) {
                LOG.error(String.format(FAILURE_FORMAT, ID_NOT_EXISTS));
                throw new NoSuchCopterIdException_Exception();
            }
        }
        LOG.fatal(NOT_EXPECTED_STATUS);
        return false;
    }

    public boolean moveToPositionById(int id, Position newPosition)
            throws MaximumDistanceExceededException_Exception,
            NoSuchCopterIdException_Exception {
        LOG.info("method moveToPositionById.");
        HttpEntity<Position> requestEntity = new HttpEntity<>(newPosition);
        String url = String.format(MOVE_TO_POSITION_BY_ID_URL, id);
        return (boolean) commonHandlerErrors(url, HttpMethod.PUT, requestEntity);
    }

    public boolean moveToPositionByIdWithDegree(int id, double degree)
            throws MaximumDistanceExceededException_Exception, NoSuchCopterIdException_Exception {
        LOG.info("method moveToPositionByIdWithDegree.");
        String url = String.format(MOVE_TO_POSITION_WITH_DEGREE_URL, id, degree);
        return (boolean) commonHandlerErrors(url, HttpMethod.PUT, null);
    }

    public boolean moveUp(int id) throws MaximumDistanceExceededException_Exception,
            NoSuchCopterIdException_Exception {
        LOG.info("method moveUp.");
        String url = String.format(MOVE_UP_URL, id);
        return (boolean) commonHandlerErrors(url, HttpMethod.PUT, null);
    }

    public boolean moveDown(int id)
            throws MaximumDistanceExceededException_Exception, NoSuchCopterIdException_Exception {
        LOG.info("method moveDown.");
        String url = String.format(MOVE_DOWN_URL, id);
        return (boolean) commonHandlerErrors(url, HttpMethod.PUT, null);
    }

    public boolean holdPosition(int id) throws NoSuchCopterIdException_Exception {
        LOG.info("method holdPosition.");
        ResponseEntity<Object> positionResponse = restTemplate.exchange(
                String.format(HOLD_POSITION_URL, id), HttpMethod.PUT,
                null, Object.class);
        if (positionResponse.getStatusCode() == HttpStatus.OK) {
            LOG.info(SUCCESS_ACTION);
            return true;
        }
        if (positionResponse.getStatusCode() == HttpStatus.BAD_REQUEST) {
            LOG.error(String.format(FAILURE_FORMAT, ID_NOT_EXISTS));
            throw new NoSuchCopterIdException_Exception();
        }
        LOG.fatal(NOT_EXPECTED_STATUS);
        return false;
    }

    public Copter findCopter(int id) throws NoSuchCopterIdException_Exception {
        LOG.info("method holdPosition.");
        ResponseEntity<Object> positionResponse = restTemplate.exchange(
                String.format(GET_FIND_COPTER_BY_ID, id), HttpMethod.GET,
                null, Object.class);
        if (positionResponse.getStatusCode() == HttpStatus.FOUND) {
            LOG.info(SUCCESS_ACTION);
            return mapper.convertValue(positionResponse.getBody(), Copter.class);
        }
        if (positionResponse.getStatusCode() == HttpStatus.BAD_REQUEST) {
            LOG.error(String.format(FAILURE_FORMAT, ID_NOT_EXISTS));
            throw new NoSuchCopterIdException_Exception();
        }
        LOG.fatal(NOT_EXPECTED_STATUS);
        return new Copter();
    }

    private Object commonHandlerErrors(
            String url, HttpMethod httpMethod, HttpEntity<?> entity)
            throws MaximumDistanceExceededException_Exception, NoSuchCopterIdException_Exception {
        try {
            ResponseEntity<Object> positionResponse = restTemplate.exchange
                    (url, httpMethod, entity, Object.class);
            if (positionResponse.getStatusCode() == HttpStatus.OK) {
                LOG.info(SUCCESS_ACTION);
                return true;
            }
        } catch (HttpClientErrorException e) {
            switch (e.getStatusCode()) {
                case CONFLICT:
                    LOG.error(String.format(FAILURE_FORMAT, MAXIMUM_DISTANCE_EXECUTED));
                    throw new MaximumDistanceExceededException_Exception();
                case BAD_REQUEST:
                    LOG.error(String.format(FAILURE_FORMAT, ID_NOT_EXISTS));
                    throw new NoSuchCopterIdException_Exception();
            }
        }
        LOG.fatal(NOT_EXPECTED_STATUS);
        return false;
    }
}
