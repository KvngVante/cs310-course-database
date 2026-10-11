package edu.jsu.mcis.cs310.dbtest.dao;

import edu.jsu.mcis.cs310.dbtest.Person;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * This class is an implementation of the {@link Person} model.
 *
 */
public class PersonDAO {
    
    private static final String QUERY_CREATE = "INSERT INTO people (firstname, lastname) VALUES (?, ?)";
    private static final String QUERY_FIND = "SELECT * FROM people WHERE id = ?";
    private static final String QUERY_LIST = "SELECT * FROM people";
    private static final String QUERY_UPDATE = "UPDATE people SET firstname = ?, lastname = ? WHERE id = ?";
    private static final String QUERY_DELETE = "DELETE FROM people WHERE id = ?";
    
    private final DAOFactory daoFactory;
    
    /**
     * Construct a new Person DAO for the given DAOFactory.  This is private to
     * the DAO package so that new DAOs can be constructed only from within this
     * package.
     * @param daoFactory The DAOFactory to construct this DAO for.
     */
    PersonDAO(DAOFactory daoFactory) {
        this.daoFactory = daoFactory;
    }
    
    /**
     * Creates a new "Person" record in the database..
     * @param p A Person model object containing the data to be added to the record.
     * @return The primary key value generated for the new record, returned as an integer.
     */
    public int create(Person p) {
        
        int result = 0;
        
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try {
            
            Connection conn = daoFactory.getConnection();
            
            if (conn.isValid(0)) {
                
                ps = conn.prepareStatement(QUERY_CREATE, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, p.getFirstname());
                ps.setString(2, p.getLastname());
                
                int updateCount = ps.executeUpdate();
                
                if (updateCount > 0) {
            
                    rs = ps.getGeneratedKeys();

                    if (rs.next()) {
                        result = rs.getInt(1);
                    }

                }
                
            }
            
        }
        
        catch (Exception e) { e.printStackTrace(); }
        
        finally {
            
            if (rs != null) { try { rs.close(); } catch (Exception e) { e.printStackTrace(); } }
            if (ps != null) { try { ps.close(); } catch (Exception e) { e.printStackTrace(); } }
            
        }
        
        return result;
        
    }
    
    /**
     * Returns an existing "Person" record from the database.
     * @return A Person model objects representing the specified record.
     */
    public List<Person> list() {
        
        List<Person> results = new ArrayList<>();
        
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try {
            
            Connection conn = daoFactory.getConnection();
            
            if (conn.isValid(0)) {
                
                ps = conn.prepareStatement(QUERY_LIST);
                
                boolean hasresults = ps.execute();
                
                if (hasresults) {

                    rs = ps.getResultSet();

                    while(rs.next()) {
                        
                        Integer id = rs.getInt("id");
                        String firstname = rs.getString("firstname");
                        String lastname = rs.getString("lastname");
                        Person p = new Person(id, firstname, lastname);
                        results.add(p);
                        
                    }

                }
                
            }
            
        }
        
        catch (Exception e) { e.printStackTrace(); }
        
        finally {
            
            if (rs != null) { try { rs.close(); } catch (Exception e) { e.printStackTrace(); } }
            if (ps != null) { try { ps.close(); } catch (Exception e) { e.printStackTrace(); } }
            
        }
        
        return results;
        
    }
    
    /**
     * Returns a list of existing "Person" records in the database.
     * @param id An integer indicating the ID of the record.
     * @return A List of Person model objects, one for each record.
     */
    public Person find(int id) {
        
        Person result = null;
        
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try {
            
            Connection conn = daoFactory.getConnection();
            
            if (conn.isValid(0)) {
                
                ps = conn.prepareStatement(QUERY_FIND);
                ps.setInt(1, id);
                
                boolean hasresults = ps.execute();
                
                if (hasresults) {

                    rs = ps.getResultSet();

                    while(rs.next()) {

                        String firstname = rs.getString("firstname");
                        String lastname = rs.getString("lastname");
                        result = new Person(id, firstname, lastname);
                        
                    }

                }
                
            }
            
        }
        
        catch (Exception e) { e.printStackTrace(); }
        
        finally {
            
            if (rs != null) { try { rs.close(); } catch (Exception e) { e.printStackTrace(); } }
            if (ps != null) { try { ps.close(); } catch (Exception e) { e.printStackTrace(); } }
            
        }
        
        return result;
        
    }
    
    /**
     * Updates an existing "Person" record in the database.
     * @param p A Person model object containing the new representation of the record.
     * @return A Boolean indicating whether the update was successful (that is,
     * if the number of records affected is equal to 1).
     */
    public boolean update(Person p) {
        
        boolean result = false;
        
        PreparedStatement ps = null;
        
        try {
            
            Connection conn = daoFactory.getConnection();
            
            if (conn.isValid(0)) {
                
                ps = conn.prepareStatement(QUERY_UPDATE);
                ps.setString(1, p.getFirstname());
                ps.setString(2, p.getLastname());
                ps.setInt(3, p.getId());
                
                int updateCount = ps.executeUpdate();
                
                if (updateCount > 0) {
            
                    result = true;

                }
                
            }
            
        }
        
        catch (Exception e) { e.printStackTrace(); }
        
        finally {

            if (ps != null) { try { ps.close(); } catch (Exception e) { e.printStackTrace(); } }
            
        }
        
        return result;
        
    }
    
    /**
     * Deletes an existing "Person" record in the database.
     * @param p A Person model object containing the representation of the record.
     * @return A Boolean indicating whether the deletion was successful (that is,
     * if the number of records affected is equal to 1).
     */
    public boolean delete(Person p) {
        
        return delete(p.getId());
        
    }
    
    /**
     * Deletes an existing "Person" record in the database..
     * @param id An integer indicating the ID of the record.
     * @return A Boolean indicating whether the deletion was successful (that is,
     * if the number of records affected is equal to 1).
     */    
    public boolean delete(int id) {
        
        boolean result = false;
        
        PreparedStatement ps = null;
        
        try {
            
            Connection conn = daoFactory.getConnection();
            
            if (conn.isValid(0)) {
                
                ps = conn.prepareStatement(QUERY_DELETE);
                ps.setInt(1, id);
                
                int updateCount = ps.executeUpdate();
                
                if (updateCount > 0) {
            
                    result = true;

                }
                
            }
            
        }
        
        catch (Exception e) { e.printStackTrace(); }
        
        finally {

            if (ps != null) { try { ps.close(); } catch (Exception e) { e.printStackTrace(); } }
            
        }
        
        return result;
        
    }
    
}