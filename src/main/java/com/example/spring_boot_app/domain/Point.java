package com.example.spring_boot_app.domain;

import jakarta.persistence.Entity;

import jakarta.persistence.Index;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = false)
@Data
@Entity
@Table(indexes = {
        @Index(name = "index1", columnList = "mb_id, po_rel_table, po_rel_id, po_rel_action"),
        @Index(name = "index2", columnList = "po_expire_date")
})
public class Point extends PointBaseEntity{

    /*	private int totalPoint;*/
}