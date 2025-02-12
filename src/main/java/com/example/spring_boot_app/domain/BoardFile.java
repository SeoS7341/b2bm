package com.example.spring_boot_app.domain;

import java.io.Serializable;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

import lombok.Data;

@Entity
@Data
@IdClass(BoardFileParent.class)
public class BoardFile implements Serializable{

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "bo_id", columnDefinition = "shortint(6) NOT NULL")
    private int boardId;

    @Id @Column(name="wr_id", columnDefinition = "int(11) NOT NULL default '0'")
    private int writeId;

    @Id @Column(name="bf_no", columnDefinition = "int(11) NOT NULL default '0'")
    private int no;

    @Column(name="bf_source", columnDefinition = "varchar(255) NOT NULL default ''")
    private String source;

    @Column(name="bf_file", columnDefinition = "varchar(255) NOT NULL default ''")
    private String file;

    @Column(name="bf_download", columnDefinition = "int(11) NOT NULL")
    private int download;

    @Column(name="bf_content", columnDefinition = "text NOT NULL")
    private String content;

    @Column(name="bf_filesize", columnDefinition = "int(11) NOT NULL default '0'")
    private int filesize;

    @Column(name="bf_width", columnDefinition = "int(11) NOT NULL default '0'")
    private int fileWidth;

    @Column(name="bf_height", columnDefinition = "smallint(6) NOT NULL default '0'")
    private int fileHeight;

    @Column(name="bf_type", columnDefinition = "tinyint(4) NOT NULL default '0'")
    private int fileType;

    @Column(name="bf_datetime", columnDefinition = "datetime NOT NULL default '0000-00-00 00:00:00'")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fileDatetime;
}