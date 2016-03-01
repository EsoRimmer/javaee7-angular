/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cortez.samples.javaee7angular.rest;

import com.cortez.samples.javaee7angular.data.Person;
import com.cortez.samples.javaee7angular.pagination.PaginatedListWrapper;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.ejb.EJB;

/**
 *
 * @author Jakub
 */
@RunWith(Arquillian.class)
public class PersonResourceTest {
    
    @Deployment
    public static JavaArchive getDeployment(){
        return ShrinkWrap.create(JavaArchive.class)
                .addClasses(Person.class, PersonResource.class, 
                        PaginatedListWrapper.class)
                .addAsResource("META-INF/persistence.xml", "META-INF/persistence.xml")
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
    }
    
    @EJB
    PersonResource personResource;
    
    @Test
    public void createPersonTest(){
        Assert.assertNotNull(1);
    }
    
}
