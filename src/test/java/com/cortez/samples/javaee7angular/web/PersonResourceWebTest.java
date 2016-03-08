/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cortez.samples.javaee7angular.web;

import static com.codeborne.selenide.Condition.text;
import com.codeborne.selenide.ElementsCollection;
import org.junit.Test;
import static com.codeborne.selenide.Selenide.*;
import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import com.cortez.samples.javaee7angular.rest.PersonResource;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Inject;
import org.junit.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/**
 *
 * @author Jakub
 */
public class PersonResourceWebTest {
    
//    @Before
//    public void setUp(){
//        open("http://localhost:8080/javaee7-angular-3.6");
//    }        
//    
//    @Test
//    public void testCreatePerson(){
//        $(By.name("name")).setValue("CreateTestName");
//        $(By.name("description")).setValue("Test Description");
//        $(By.name("imageUrl")).setValue("http://localhost:8080/javaee7-angular-3.6/");
//        $(By.xpath("//button[text()='Save']")).click();
//        
//        $(By.xpath("//*[text()='Last']")).click();
//        
//        $(By.className("gridStyle")).shouldHave(text("CreateTestName \n Test Description"));      
//    }
//    
//    @Test
//    public void testUpdatePerson(){       
//        $(By.xpath("//*[text()='Last']")).click();
//        addTestPerson("TestNameForUpdate");
//        $(By.xpath("//*[text()='TestNameForUpdate']")).click();
//        
//        $(By.name("name")).has(text("TestNameForUpdate"));
//        $(By.name("description")).has(text("TestDescriptionforUpdate"));
//        $(By.name("imgUrl")).has(text("https://www.google.sk/"));
//        
//        $(By.name("name")).setValue("UpdatedName");
//        $(By.xpath("//button[text()='Save']")).click();
//        $(By.className("gridStyle")).shouldHave(text("UpdatedName \n TestDescription")); 
//    }
//    
//    private void addTestPerson(String name){
//        $(By.name("name")).setValue(name);
//        $(By.name("description")).setValue("TestDescription");
//        $(By.name("imageUrl")).setValue("https://www.google.sk/");
//        $(By.xpath("//button[text()='Save']")).click();
//    }
//    
//    @Test
//    public void testDeletePerson(){
//        addTestPerson("DeleteTestName");
//        $(By.xpath("//*[text()='Last']")).click();
//        SelenideElement table = $(By.className("gridStyle"));
//        ElementsCollection rows= table.$$(By.xpath("//div[@class='ng-scope ngRow even']"));
//        for(SelenideElement row : rows){
//            if(row.has(text("DeleteTestName"))){
//                row.$(By.xpath("//span[@class='glyphicon glyphicon-remove remove ng-scope']")).click();
//                break;
//            }
//        }
//
//        table.shouldNotHave(text("DeleteTestName"));
//    }
//    
}
