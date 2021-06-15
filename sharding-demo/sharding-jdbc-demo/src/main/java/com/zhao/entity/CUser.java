package com.zhao.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Table(name = "c_user")
@Entity
public class CUser {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "pwd")
    private String pwd;
}
