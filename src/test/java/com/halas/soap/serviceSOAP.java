package com.halas.soap;

import org.testng.annotations.Test;


public class serviceSOAP {
    @Test
    public void testGetAllUsers() {
        CopterServiceSOAP service = new CopterSOAPService().getCopterSOAPPort();
        service.showAllCopters().getItem().forEach(System.out::println);
    }
}
