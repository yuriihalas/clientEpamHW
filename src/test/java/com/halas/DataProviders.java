package com.halas;

import com.halas.factory.CopterServiceType;
import com.halas.utils.parser.data.*;
import org.testng.annotations.DataProvider;

import java.util.Iterator;
import java.util.stream.Stream;

public class DataProviders {

    @DataProvider
    public static Object[][] typeServiceData() {
        return new Object[][]{
                {CopterServiceType.REST},
                {CopterServiceType.SOAP}};
    }

    @DataProvider(parallel = true)
    public Iterator<Object[]> correctCoptersCreateData() {
        return Stream.of(DataCreateCopter.getCorrectCopters()).iterator();
    }

    @DataProvider(parallel = true)
    public Iterator<Object[]> unCorrectCoptersCreateData() {
        return Stream.of(DataCreateCopter.getUnCorrectCopters()).iterator();
    }

    @DataProvider(parallel = true)
    public Iterator<Object[]> correctCoptersDeleteData() {
        return Stream.of(DataDeleteCopter.getCorrectCopters()).iterator();
    }

    @DataProvider(parallel = true)
    public Iterator<Object[]> unCorrectCoptersDeleteData() {
        return Stream.of(DataDeleteCopter.getUnCorrectCopters()).iterator();
    }

    @DataProvider(parallel = true)
    public Iterator<Object[]> correctCoptersMoveToPositionByIdData() {
        return Stream.of(DataMoveToPositionById.getCorrectCopters()).iterator();
    }

    @DataProvider(parallel = true)
    public Iterator<Object[]> unCorrectCoptersMoveToPositionNoIdData() {
        return Stream.of(DataMoveToPositionById.getUnCorrectNoSuchIdCopters()).iterator();
    }

    @DataProvider(parallel = true)
    public Iterator<Object[]> unCorrectCoptersMoveToPositionMaxDistanceData() {
        return Stream.of(DataMoveToPositionById.getUnCorrectMaximumDistanceCopters()).iterator();
    }

    @DataProvider(parallel = true)
    public Iterator<Object[]> correctCoptersMoveToPositionByIdWithDegreeData() {
        return Stream.of(DataMoveToPositionByIdWithDegree.getCorrectCopters()).iterator();
    }

    @DataProvider(parallel = true)
    public Iterator<Object[]> correctCoptersMoveUpData() {
        return Stream.of(DataMoveUp.getCorrectCopters()).iterator();
    }

    @DataProvider(parallel = true)
    public Iterator<Object[]> unCorrectCoptersMoveUpData() {
        return Stream.of(DataMoveUp.getUnCorrectCopters()).iterator();
    }

    @DataProvider(parallel = true)
    public Iterator<Object[]> correctCoptersMoveDownData() {
        return Stream.of(DataMoveDown.getCorrectCopters()).iterator();
    }

    @DataProvider(parallel = true)
    public Iterator<Object[]> unCorrectCoptersMoveDownData() {
        return Stream.of(DataMoveDown.getUnCorrectCopters()).iterator();
    }

    @DataProvider(parallel = true)
    public Iterator<Object[]> correctCoptersHoldPositionData() {
        return Stream.of(DataHoldPosition.getCorrectCopters()).iterator();
    }

    @DataProvider(parallel = true)
    public Iterator<Object[]> unCorrectCoptersHoldPositionData() {
        return Stream.of(DataHoldPosition.getUnCorrectCopters()).iterator();
    }
}
