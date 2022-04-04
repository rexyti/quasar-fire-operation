package com.quasar.operation.quasarmessagesplitprocessor.domain.shared.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Table
@Entity
@Data
public class ProcessedMessage implements Serializable {

    private static final long serialVersionUID = -7824409983206775850L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long messageId;

    @Column(unique = true)
    private String uid;

    private String message;

    private Float x;

    private Float y;

    private String status;

    private Date creationDate;


}
