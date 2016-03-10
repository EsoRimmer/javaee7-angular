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
import com.cortez.samples.javaee7angular.data.Person;
import com.cortez.samples.javaee7angular.pagination.PaginatedListWrapper;
import com.cortez.samples.javaee7angular.rest.PersonResource;
import java.io.File;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ArchivePaths;
import org.jboss.shrinkwrap.api.Filters;
import org.jboss.shrinkwrap.api.GenericArchive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.exporter.ZipExporter;
import org.jboss.shrinkwrap.api.importer.ExplodedImporter;
import org.jboss.shrinkwrap.api.importer.ZipImporter;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;

/**
 *
 * @author Jakub
 */
@RunWith(Arquillian.class)
public class PersonResourceWebTest {
    
    private static final String WEBAPP_SRC = "src/main/webapp";
    
    @Deployment(testable = false)
    public static WebArchive createDeployment() {
        return ShrinkWrap.create(WebArchive.class, "PersonResourceWebTest.war")
                .addClasses(Person.class, PersonResource.class, PaginatedListWrapper.class)
                .addAsResource("META-INF/persistence.xml", "META-INF/persistence.xml")
                .merge(ShrinkWrap.create(GenericArchive.class).as(ExplodedImporter.class)
                                .importDirectory(WEBAPP_SRC).as(GenericArchive.class),
                        "/", Filters.includeAll())
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    
    @Before
    public void setUp(){   
        open("http://localhost:8080/PersonResourceWebTest");
    }     
    
    @Test
    public void testCreatePerson(){
        $(By.name("name")).setValue("CreateTestName");
        $(By.name("description")).setValue("Test Description");
        $(By.name("imageUrl")).setValue("http://localhost:8080/javaee7-angular-3.6/");
        $(By.xpath("//button[text()='Save']")).click();
        
        $(By.xpath("//*[text()='Last']")).click();
        
        $(By.className("gridStyle")).shouldHave(text("CreateTestName \n Test Description"));      
    }
    
    @Test
    public void testUpdatePerson(){       
        $(By.xpath("//*[text()='Last']")).click();
        addTestPerson("TestNameForUpdate");
        $(By.xpath("//*[text()='TestNameForUpdate']")).click();
        
        $(By.name("name")).has(text("TestNameForUpdate"));
        $(By.name("description")).has(text("TestDescriptionforUpdate"));
        $(By.name("imgUrl")).has(text("https://www.google.sk/"));
        
        $(By.name("name")).setValue("UpdatedName");
        $(By.xpath("//button[text()='Save']")).click();
        $(By.className("gridStyle")).shouldHave(text("UpdatedName \n TestDescription")); 
    }
    
    private void addTestPerson(String name){
        $(By.name("name")).setValue(name);
        $(By.name("description")).setValue("TestDescription");
        $(By.name("imageUrl")).setValue("https://www.google.sk/");
        $(By.xpath("//button[text()='Save']")).click();
    }
    
    @Test
    public void testDeletePerson(){
        addTestPerson("DeleteTestName");
        $(By.xpath("//*[text()='Last']")).click();
        SelenideElement table = $(By.className("gridStyle"));
        ElementsCollection rows= table.$$(By.xpath("//div[@class='ng-scope ngRow even']"));
        for(SelenideElement row : rows){
            if(row.has(text("DeleteTestName"))){
                row.$(By.xpath("//span[@class='glyphicon glyphicon-remove remove ng-scope']")).click();
                break;
            }
        }

        table.shouldNotHave(text("DeleteTestName"));
    }
}
