package oy.tol.tra;

public class Person implements Comparable<Person> {
    private String firstName;
    private String lastName;

    public Person(final Person person) {
        this.firstName = new String(person.firstName);
        this.lastName = new String(person.lastName);
    }
    
    public Person(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getFullName() {
        return lastName + " " + firstName;
    }

    @Override
    public String toString() {
        return getFullName();
    }
    @Override
    public int hashCode() {
        int hash=0;
        String hashString=firstName+lastName;
        for (int i = 0; i < hashString.length(); i++) {
            hash=37*hash+hashString.charAt(i);
        }
        return hash;
    }

    @Override
    public boolean equals(Object other) {
        if (other instanceof Person) {
            return this.getFullName().equals(((Person)other).getFullName());
        }
        return false;
    }


    @Override
    public int compareTo(Person other) {
        return getFullName().compareTo(other.getFullName());
    }
}
