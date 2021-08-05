package com.zhao.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Table(name = "position_detail")
@Entity
public class PositionDetail implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "pid")
    private Long pid;
    @Column(name = "description")
    private String description;
}
