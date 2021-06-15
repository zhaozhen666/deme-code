package com.zhao.entity;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;


@Data
@Table(name = "position")
@Entity
@ToString
public class Position implements Serializable {
   @Id
   @Column(name = "Id")
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long Id;
   @Column(name = "name")
   private String name;
   @Column(name = "salary")
   private  String salary;
   @Column(name = "city")
   private  String city;
}
