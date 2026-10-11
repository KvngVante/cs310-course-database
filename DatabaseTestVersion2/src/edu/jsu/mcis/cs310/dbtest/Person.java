package edu.jsu.mcis.cs310.dbtest;

public class Person {

    private final Integer id;
    private final String firstname, lastname;

    public Person(Integer id, String firstname, String lastname) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
    }

    public Integer getId() {
        return id;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append('#').append(id).append(": ");
        s.append(lastname).append(", ").append(firstname);
        return s.toString();
    }

}
