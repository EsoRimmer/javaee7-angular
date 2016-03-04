/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cortez.samples.javaee7angular.web;

import org.junit.Test;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.*;
import com.cortez.samples.javaee7angular.data.Person;
import com.cortez.samples.javaee7angular.rest.PersonResource;
import javax.inject.Inject;
import org.openqa.selenium.By;

/**
 *
 * @author Jakub
 */
public class PersonResourceWebTest {
    
    @Inject          
    PersonResource personResource;
    
    @Test
    public void testCreatePerson(){
        open("http://localhost:8080/javaee7-angular-3.6");
        $(By.name("person.name")).setValue("Test Name");
        $(By.name("person.description")).setValue("Test Description");
        $(By.name("person.url")).setValue("http://localhost:8080/javaee7-angular-3.6/");
        $("#submit").click();
//        personResource.getPerson(Long.MIN_VALUE)
    }
    
}
