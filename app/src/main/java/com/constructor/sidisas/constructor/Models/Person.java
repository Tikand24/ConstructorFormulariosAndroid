package com.constructor.sidisas.constructor.Models;

import com.constructor.sidisas.constructor.R;

import java.util.ArrayList;
import java.util.List;

public class Person {
    String name;
    String age;
    int photoId;

    public Person(String name, String age, int photoId) {
        this.name = name;
        this.age = age;
        this.photoId = photoId;
    }
    private List<Person> persons;

    public Person() {
        initializeData();
    }

    // This method creates an ArrayList that has three Person objects
    // Checkout the project associated with this tutorial on Github if
    // you want to use the same images.
    private void initializeData(){
        persons = new ArrayList<>();
        persons.add(new Person("Emma Wilson", "23 years old", R.drawable.parametros));
        persons.add(new Person("Lavery Maiss", "25 years old", R.drawable.parametros));
        persons.add(new Person("Lillie Watts", "35 years old", R.drawable.parametros));
    }
}

