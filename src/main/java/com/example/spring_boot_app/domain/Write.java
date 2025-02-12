package com.example.spring_boot_app.domain;

import jakarta.persistence.Entity;

import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = false)
@Entity
@Data
@Table(uniqueConstraints = {
        @UniqueConstraint(name = "wr_board", columnNames = {"wr_id", "bo_id"}),
        @UniqueConstraint(name = "wr_num_reply_parent", columnNames = {"wr_num", "wr_reply", "wr_parent"}),
        @UniqueConstraint(name = "wr_is_comment", columnNames = {"wr_is_comment", "wr_id"})
})
public class Write extends WriteBaseEntity{

    private static final long serialVersionUID = 1L;

}