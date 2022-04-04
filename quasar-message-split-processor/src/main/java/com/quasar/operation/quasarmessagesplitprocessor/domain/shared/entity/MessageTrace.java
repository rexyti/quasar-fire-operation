package com.quasar.operation.quasarmessagesplitprocessor.domain.shared.entity;

import lombok.Data;


import javax.persistence.*;

import java.io.Serializable;
import java.util.Date;

@Table
@Entity
@Data
public class MessageTrace implements Serializable{
    private static final long serialVersionUID = 8911124943957142281L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long traceId;

    @Column(unique = true)
    private String uid;

    private String satelliteName;

    private Float distance;

    private String message;

    private Date creationDate;
}
