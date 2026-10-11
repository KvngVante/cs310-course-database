package edu.jsu.mcis.cs310.dbtest;

import edu.jsu.mcis.cs310.dbtest.dao.*;
import java.util.List;

public class DatabaseTest {
    
    public static void main(String[] args) {
        
        /* Create DAOs */
        
        DAOFactory daoFactory = new DAOFactory("databasetest.jdbc");
        PersonDAO personDAO = daoFactory.getPersonDAO();
        
        /* Create Record */
        
        Person p1 = new Person(null, "Anakin", "Skywalker");
        
        int newid = personDAO.create(p1); // creates record
        
        if (newid != 0) {
            System.err.println("Create Successful!");
        }
        
        /* Find Record */
        
        Person p3 = personDAO.find(newid); // creates record
        
        System.err.println("Record Found: " + p3);
        
        /* Update Record */
        
        Person p2 = new Person(newid, "Darth", "Vader");
        
        if (personDAO.update(p2)) { // updates record
            System.err.println("Update Successful!");
        }
        
        /* Delete Record */
        
        if (personDAO.delete(p2)) { // deletes record
            System.err.println("Delete Successful!");
        }
        
        /* List People in Database */
        
        List<Person> people = personDAO.list(); // gets list
        
        System.err.println("Listing " + people.size() + " Record(s):");
        
        for (Person p : people) {
            System.err.format("%20s", p.getId());
            System.err.format("%20s", p.getFirstname());
            System.err.format("%20s", p.getLastname());
            System.err.println();
        }
        
        System.err.println();
        
    }
    
}