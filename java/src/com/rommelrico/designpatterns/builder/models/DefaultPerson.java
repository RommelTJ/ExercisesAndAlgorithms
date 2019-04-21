package com.rommelrico.designpatterns.builder.models;

import com.rommelrico.designpatterns.builder.interfaces.*;

public class DefaultPerson implements Person {

    // Required Parameters
    private final String firstName;
    private final String lastName;

    // Optional Parameters
    String address;
    int age;
    String phoneNumber;

    DefaultPerson(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    DefaultPerson(PersonBuilder personBuilder) {
        this(personBuilder.getFirstName(), personBuilder.getLastName());
        this.address = personBuilder.getAddress();
        this.age = personBuilder.getAge();
        this.phoneNumber = personBuilder.getPhoneNumber();
    }

    // Inner Class
    public static class PersonBuilder extends DefaultPerson {

        public PersonBuilder(String firstName, String lastName) {
            super(firstName, lastName);
        }

        public PersonBuilder address(String address) {
            this.address = address;
            return this;
        }

        public PersonBuilder age(int age) {
            this.age = age;
            return this;
        }

        public PersonBuilder phoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
            return this;
        }

        public DefaultPerson build() {
            return new DefaultPerson(this);
        }
    }


    @Override
    public String getFirstName() {
        return firstName;
    }

    @Override
    public String getLastName() {
        return lastName;
    }

    @Override
    public String getAddress() {
        return address;
    }

    @Override
    public int getAge() {
        return age;
    }

    @Override
    public String getPhoneNumber() {
        return phoneNumber;
    }

    @Override
    public String toString() {
        return "DefaultPerson{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", address='" + address + '\'' +
                ", age=" + age +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
}
