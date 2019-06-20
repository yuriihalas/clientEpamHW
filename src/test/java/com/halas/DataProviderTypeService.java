package com.halas;

import com.halas.factory.CopterServiceType;
import org.testng.annotations.DataProvider;

public class DataProviderTypeService {

    @DataProvider
    public static Object[][] typeServiceData() {
        return new Object[][]{
                {CopterServiceType.REST},
                {CopterServiceType.SOAP}};
    }
}
