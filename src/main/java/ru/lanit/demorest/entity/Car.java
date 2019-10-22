package ru.lanit.demorest.entity;

import javax.persistence.*;

@Entity
@Table(name = "car", schema = "db_test_rest")
public class Car {

    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "vendor", nullable = false)
    private String vendor;

    @Column(name = "model", nullable = false)
    private String model;

    @Column(name = "horse_power")
    private int horsePower;

    @ManyToOne
    @JoinColumn(name = "person_id")
    private Person person;

    public Car() {
    }

    public Car(Long id, String vendor, String model, int horsePower, Person person) {
        this.id = id;
        this.vendor = vendor;
        this.model = model;
        this.horsePower = horsePower;
        this.person = person;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getHorsePower() {
        return horsePower;
    }

    public void setHorsePower(int horsePower) {
        this.horsePower = horsePower;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
}
