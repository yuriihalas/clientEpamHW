package com.halas;

import com.halas.factory.CopterServiceType;
import com.halas.service.TestServicesCopters;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Factory;

public class SampleFactory {
    private static final Logger LOG = LogManager.getLogger(SampleFactory.class);

    @Factory(dataProvider = "typeServiceData")
    public Object[] createInstances(CopterServiceType serviceType) {
        LOG.info("Instances Of Factory: " + serviceType);
        return new Object[]{new TestServicesCopters(serviceType)};
    }

    @DataProvider
    public Object[] typeServiceData() {
        return new Object[]{CopterServiceType.REST, CopterServiceType.SOAP};
    }
}
