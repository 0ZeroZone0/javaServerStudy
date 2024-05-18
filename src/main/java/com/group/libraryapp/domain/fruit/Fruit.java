package com.group.libraryapp.domain.fruit;

import lombok.Getter;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
public class Fruit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private long price;

    @Column(nullable = false)
    private LocalDate warehousingDate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status;

    protected Fruit(){}

    public Fruit(String name, LocalDate warehousingDate, long price) {
        this.name = name;
        this.price = price;
        this.warehousingDate =warehousingDate;
        this.status = Status.NOT_SOLD;
    }

    public void updateSoldStatus(){
        this.status = Status.SOLD;
    }

    public enum Status {
        SOLD, NOT_SOLD
    }

}
