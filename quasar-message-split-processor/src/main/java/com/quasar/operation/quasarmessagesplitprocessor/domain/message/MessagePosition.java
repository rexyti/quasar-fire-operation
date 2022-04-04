package com.quasar.operation.quasarmessagesplitprocessor.domain.message;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MessagePosition {
    private Float x;
    private Float y;

    public boolean isValid (){
        return x!=null && y!=null;
    }

}
