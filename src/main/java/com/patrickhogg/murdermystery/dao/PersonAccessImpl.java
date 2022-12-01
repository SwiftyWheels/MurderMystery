package com.patrickhogg.murdermystery.dao;

import com.patrickhogg.murdermystery.model.Person;
import org.springframework.stereotype.Repository;

import java.util.LinkedList;
import java.util.List;

/**
 * @author Patrick Hogg
 */
@Repository
public class PersonAccessImpl implements PersonAccess{
    private List<Person> people = new LinkedList<>();
    @Override
    public void addPerson(Person person) {
        if (!people.contains(person)) {
            people.add(person);
        }
    }

    @Override
    public List<Person> getAllPeople() {
        return people;
    }

    @Override
    public Person getPerson(Person person) {
        if (people.contains(person)) {
            for (Person currentPerson : people) {
                if (currentPerson.equals(person)) {
                    return currentPerson;
                }
            }
        }
        return null;
    }

    @Override
    public Person getPersonByName(String name) {
        for (Person currentPerson : people) {
            if (currentPerson.getName().equalsIgnoreCase(name)) {
                return currentPerson;
            }
        }
        return null;
    }

    @Override
    public Person getMurderer() {
        for (Person currentPerson : people) {
            if (currentPerson.isMurderer()) {
                return currentPerson;
            }
        }
        return null;
    }
}
