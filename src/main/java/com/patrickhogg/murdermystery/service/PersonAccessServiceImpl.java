package com.patrickhogg.murdermystery.service;

import com.patrickhogg.murdermystery.dao.PersonAccessImpl;
import com.patrickhogg.murdermystery.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Patrick Hogg
 */
@Service
public class PersonAccessServiceImpl implements PersonAccessService{

    @Autowired
    private PersonAccessImpl personAccess;

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
}
