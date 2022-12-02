package com.patrickhogg.murdermystery.dao;

import com.patrickhogg.murdermystery.model.Person;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

/**
 * @author Patrick Hogg
 */
public class PersonAccessImpl implements PersonAccess, Serializable {

    private final List<Person> personList;
    public PersonAccessImpl() {
        personList = new LinkedList<>();
    }

    @Override
    public void addPerson(Person person) {
        if (!personList.contains(person)) {
            personList.add(person);
        }
    }

    @Override
    public List<Person> getAllPeople() {
        return personList;
    }

    @Override
    public Person getPerson(Person person) {
        if (personList.contains(person)) {
            for (Person currentPerson : personList) {
                if (currentPerson.equals(person)) {
                    return currentPerson;
                }
            }
        }
        return null;
    }

    @Override
    public Person getPersonByName(String name) {
        for (Person currentPerson : personList) {
            if (currentPerson.getName().equalsIgnoreCase(name)) {
                return currentPerson;
            }
        }
        return null;
    }

    @Override
    public Person getMurderer() {
        for (Person currentPerson : personList) {
            if (currentPerson.isMurderer()) {
                return currentPerson;
            }
        }
        return null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PersonAccessImpl that)) {
            return false;
        }
        return Objects.equals(personList, that.personList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(personList);
    }

    @Override
    public String toString() {
        return "PersonAccessImpl{" + "personList=" + personList + '}';
    }
}
