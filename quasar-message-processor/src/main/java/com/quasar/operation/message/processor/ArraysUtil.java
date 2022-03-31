package com.quasar.operation.message.processor;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import com.quasar.operation.message.processor.exceptions.MessagesNoMatchException;

public class ArraysUtil {

    private ArraysUtil() {
    }

    public static String[] trimArray(String[] array) {
        return Arrays.copyOfRange(array, calculateOffset(array), array.length);
    }

    public static int calculateOffset(String[] messages) throws MessagesNoMatchException{
        return IntStream.range(0, messages.length)
                .filter(i -> !messages[i].isBlank())
                .findFirst()
                .orElseThrow(MessagesNoMatchException::new);
    }

    public static String unificateValue(String... values) throws MessagesNoMatchException {
        String result = Arrays.stream(values)
                .filter(value -> !value.isBlank())
                .reduce("", (candidate, newString) -> (newString.equals(candidate) || candidate.isBlank()) ? newString
                        : "");
        if (result.isBlank()) {
            throw new MessagesNoMatchException();
        }
        return result;
    }

    public static int getMaxSize(List<String[]> listArray) {
        return listArray.stream()
                .mapToInt(array -> array.length)
                .max().orElse(-1);
    }

    public static String[] unificateListValue(List<String[]> listArray, int size) {
        return IntStream.range(0, size)
            .mapToObj(i -> unificateValue(listArray.get(0)[i], listArray.get(1)[i], listArray.get(2)[i]))
            .toArray(String[]::new);
    }

    public static String[] fillArrayWithBlackValues(String[] array, int size) {
        int blankValuesSize = size - array.length;
        String[] blankStrings = Arrays.stream(new String[blankValuesSize])
                .map(s -> "").toArray(String[]::new);
        return Stream.concat(Arrays.stream(blankStrings),Arrays.stream(array)).toArray(String[]::new);

    }
}
