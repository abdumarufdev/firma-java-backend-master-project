package com.example.firmawebsayti.Entity.Abstrak;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Collection;

@Data
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class Abstrakt{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @CreatedBy
    private Integer  kimTomonidanYaratilgan;
    @LastModifiedBy
    private Integer kimTomonidanTahrirlangan;
    @Column(nullable = false)
    @CreationTimestamp
    private Timestamp qachonYaratilganVaqt;
    @Column(nullable = false)
    @UpdateTimestamp
    private Timestamp qachonTahrirlanganVaqt;

  }
