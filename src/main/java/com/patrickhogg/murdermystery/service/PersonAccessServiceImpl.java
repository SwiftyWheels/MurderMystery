package com.patrickhogg.murdermystery.service;

import com.patrickhogg.murdermystery.dao.PersonAccessImpl;
import com.patrickhogg.murdermystery.model.Person;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

/**
 * @author Patrick Hogg
 */
public class PersonAccessServiceImpl implements PersonAccessService,
                                                Serializable {

    private final PersonAccessImpl personAccess = new PersonAccessImpl();

    @Override
    public void addPerson(Person person) {
        personAccess.addPerson(person);
    }

    @Override
    public List<Person> getAllPeople() {
        return personAccess.getAllPeople();
    }

    @Override
    public Person getPerson(Person person) {
        return personAccess.getPerson(person);
    }

    @Override
    public Person getPersonByName(String name) {
        return personAccess.getPersonByName(name);
    }

    @Override
    public Person getMurderer() {
        return personAccess.getMurderer();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PersonAccessServiceImpl that)) {
            return false;
        }
        return Objects.equals(personAccess, that.personAccess);
    }

    @Override
    public int hashCode() {
        return Objects.hash(personAccess);
    }

    @Override
    public String toString() {
        return "PersonAccessServiceImpl{" + "personAccess=" + personAccess
               + '}';
    }
}
