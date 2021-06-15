package com.zhao.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
@Data
@Table(name = "b_order")
@Entity
public class Border implements Serializable {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name="is_del")
    private Boolean isDel;
    @Column(name = "company_id")
    private Integer companyId;
    @Column(name = "position_id")
    private long positionId;
    @Column(name = "user_id")
    private Integer userId;
    @Column(name = "publish_user_id")
    private Integer publishUserId;
    @Column(name = "resume_type")
    private Integer resumeType;
    @Column(name = "status")
    private String status;
    @Column(name = "create_time")
    private Date createTime;
    @Column(name = "operate_time")
    private Date operateTime;
    @Column(name = "work_year")
    private String workYear;
    @Column(name = "name")
    private  String name;
    @Column(name = "position_name")
    private String positionName;
    @Column(name = "resume_id")
    private Integer resumeId;
}
