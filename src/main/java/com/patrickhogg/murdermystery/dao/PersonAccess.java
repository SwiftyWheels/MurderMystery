package com.patrickhogg.murdermystery.dao;

import com.patrickhogg.murdermystery.model.Person;

import java.util.List;

/**
 * @author Patrick Hogg
 */
public interface PersonAccess {
    void addPerson(Person person);

    List<Person> getAllPeople();

    Person getPerson(Person person);

    Person getPersonByName(String name);

    Person getMurderer();
}
