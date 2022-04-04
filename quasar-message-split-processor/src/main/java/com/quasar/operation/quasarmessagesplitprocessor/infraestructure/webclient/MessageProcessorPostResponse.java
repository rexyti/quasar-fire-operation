package com.quasar.operation.quasarmessagesplitprocessor.infraestructure.webclient;

import java.io.Serializable;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class MessageProcessorPostResponse implements Serializable{
    private static final long serialVersionUID = -622613644931077333L;
    private Position position;
    private String message;
}
