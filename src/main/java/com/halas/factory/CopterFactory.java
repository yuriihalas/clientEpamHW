package com.halas.factory;

import com.halas.CopterService;
import com.halas.service.CopterREST;
import com.halas.soap.CopterSOAP;

public class CopterFactory {
    private CopterFactory() {
    }

    public static CopterService getService(CopterServiceType type) {
        switch (type) {
            case REST:
                return new CopterREST();
            case SOAP:
                return new CopterSOAP();
        }
        return null;
    }
}
