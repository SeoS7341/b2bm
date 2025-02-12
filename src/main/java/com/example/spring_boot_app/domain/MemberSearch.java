package com.example.spring_boot_app.domain;

import jakarta.persistence.Entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = false)
@Data
@Entity
public class MemberSearch extends MemberBaseEntity {

    private String searchWord;
    private String keyword;
    private String orderBy;
}