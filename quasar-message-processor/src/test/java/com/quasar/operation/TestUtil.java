package com.quasar.operation;

import java.util.Arrays;

import com.google.gson.Gson;
import com.quasar.operation.app.dto.MessageProcessorRequest;
import com.quasar.operation.app.dto.SatelliteMessage;

public class TestUtil {
    
    public static String getValidMessageRequest(){
        MessageProcessorRequest mpr = new MessageProcessorRequest();
        mpr.setSatellites(Arrays.asList(
            new SatelliteMessage("skywalker",128.362767f,new String[] {"", "es", "", "", "secreto"}),
            new SatelliteMessage("sato",532.988742f,new String[] {"","","","","","este", "", "un", "", ""}),
            new SatelliteMessage("kenobi",511.348217f,new String[] {"este", "", "", "mensaje", ""})
        ));
        return new Gson().toJson(mpr);
    }

    public static String getInvalidMessageRequest(){
        MessageProcessorRequest mpr = new MessageProcessorRequest();
        mpr.setSatellites(Arrays.asList(
            new SatelliteMessage("skywalker",128.362767f,new String[] {"", "es", "", "", "secreto"}),
            new SatelliteMessage("sato",532.988742f,new String[] {"","","","","","", "", "", "", ""}),
            new SatelliteMessage("kenobi",511.348217f,new String[] {"este", "", "", "mensaje", ""})
        ));
        return new Gson().toJson(mpr);
    }

}
