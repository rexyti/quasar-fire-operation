package com.quasar.operation.message.processor;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.List;

import com.quasar.operation.message.processor.exceptions.IncorrectDistancesExeption;
import com.quasar.operation.message.processor.exceptions.IncorrectMessagesNumberExeption;
import com.quasar.operation.message.processor.exceptions.MessagesNoMatchException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MessageProcessorTest {

    MessageProcessor messageProcessor;

    @BeforeEach
    void setup(){
        List<Point> satellites = Arrays.asList(
            new Point(-500f,-200f),
            new Point(100f,-100f),
            new Point(500f,100f));
        messageProcessor= new MessageProcessor(satellites,0.01f);
    }

    @Test
    void testGetLocationSucces() {
        Point result = messageProcessor.getLocation(511.348217f,128.362767f,532.988742f);
        assertTrue(MathUtil.floatsAreEquals(result.getX(),-14f, 0.01f));
        assertTrue(MathUtil.floatsAreEquals(result.getY(),-41f, 0.01f));
    }

    @Test
    void testGetLocationFailIncorrectMessagesNumber() {
        assertThrows(IncorrectMessagesNumberExeption.class,()->messageProcessor.getLocation(50f,50f) );
    }

    @Test
    void testGetLocationFailIncorrectDistances() {
        assertThrows(IncorrectDistancesExeption.class,()->messageProcessor.getLocation(50f,50f,50f) );
    }

    @Test
    void testGetMessageSucces() {
        String result1 = messageProcessor.getMessage(
            new String[]{ "", "este", "es", "","mensaje" },
            new String[]{ "","","","","","este", "", "un", "mensaje" },
            new String[]{ "","","", "", "", "un","" }
        );
        String result2 = messageProcessor.getMessage(
            new String[]{ "este", "", "","mensaje","secreto" },
            new String[]{ "", "es", "", "","secreto" },
            new String[]{ "este", "", "un", "","" }
        );
        assertEquals("este es un mensaje", result1);
        assertEquals("este es un mensaje secreto", result2);
    }

    @Test
    void testGetMessageFailBlackMessage() {
        assertThrows(MessagesNoMatchException.class,()->messageProcessor.getMessage(
            new String[]{ "", "este", "es", "","mensaje" },
            new String[]{ "","","","","","este", "", "un", "mensaje" },
            new String[]{ "","","", "", "", "","" }
        ) );
    }

    @Test
    void testGetMessageFailMessagesNoMatch() {
        assertThrows(MessagesNoMatchException.class,()->messageProcessor.getMessage(
            new String[]{ "este", "", "","mensaje","secreto2" },
            new String[]{ "", "es", "", "","secreto1" },
            new String[]{ "este", "", "un", "","" }
        ) );
    }
}
