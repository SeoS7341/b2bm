package com.example.spring_boot_app.domain;

import jakarta.persistence.*;

import lombok.Data;


@Entity(name="group")
public class BoardGroup extends BoardGroupBaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="group_id", columnDefinition = "int(11) NOT NULL")
    private int id;

}