package com.example.spring_boot_app.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(indexes = {
        @Index(name = "mb_today_login", columnList = "mb_today_login"),
        @Index(name = "mb_datetime", columnList = "mb_datetime")
},uniqueConstraints={@UniqueConstraint(name="mb_id",columnNames={"mb_id"})})
public class Member extends MemberBaseEntity{


}