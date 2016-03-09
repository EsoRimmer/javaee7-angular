/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import javax.ejb.EJB;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.cortez.samples.javaee7angular.data.Person;
import com.cortez.samples.javaee7angular.pagination.PaginatedListWrapper;
import com.cortez.samples.javaee7angular.rest.PersonResource;

/**
 *
 * @author Jakub
 */

@RunWith(Arquillian.class)
public class PersonResourceTest {
    
    @Deployment
    public static Archive<?> createDeployment(){
        return ShrinkWrap.create(WebArchive.class, "PersonResourceTest.war")
                .addClasses(Person.class, PersonResource.class, PaginatedListWrapper.class)
                .addAsResource("META-INF/persistence.xml", "META-INF/persistence.xml")
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
        
    }
    
    @PersistenceContext
    EntityManager em;
    
    @EJB          
    PersonResource personResource;
    
    private Person person1;
    private Person person2;

    @Before
    public void setUp(){
        
        person1 = new Person();
        person1.setDescription("Test person");
        person1.setName("Jano Test");
        
        person2 = new Person();
        person2.setDescription("Description of person 2");
        person2.setName("Test Name");
        person2.setImageUrl("URL");
        
        personResource.savePerson(person1);
        personResource.savePerson(person2);
    }

    
    @Test
    public void createPersonTest(){  
        Assert.assertNotNull(personResource.getPerson(person1.getId()));
    }
    
    @Test
    public void deletePersonTest(){
        Assert.assertEquals(personResource.getPerson(person1.getId()), person1);
        Assert.assertEquals(personResource.getPerson(person2.getId()), person2);
        
        personResource.deletePerson(person1.getId());
        
        Assert.assertNull(personResource.getPerson(person1.getId()));
        Assert.assertEquals(personResource.getPerson(person2.getId()), person2);
    }
    
    @Test
    public void findPersonTest(){   
        Person found = personResource.getPerson(person2.getId());
        Assert.assertNotNull(found);
        Assert.assertEquals(found, person2);
    }
    
    @Test
    public void updatePersonTest(){
        Long id = person1.getId();
        Person updatedPerson = personResource.getPerson(person1.getId());
        Assert.assertEquals(updatedPerson, person1);
        
        updatedPerson.setName("updated name");
        updatedPerson.setDescription("updated description");
        personResource.savePerson(updatedPerson);
        Person found = personResource.getPerson(id);
        
        Assert.assertEquals(found.getName(), updatedPerson.getName());
        Assert.assertEquals(found.getDescription(), updatedPerson.getDescription());
        Assert.assertFalse(found.getName().equals(person1.getName()));
        Assert.assertFalse(found.getDescription().equals(person1.getDescription()));
    }
}
