package com.example.spring_boot_app.domain;

import jakarta.persistence.Entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = false)
@Data
@Entity
public class BoardGroupList extends BoardGroupBaseEntity{

    private int countIncludedBoards;
    private int countAccessibleMembers;
}