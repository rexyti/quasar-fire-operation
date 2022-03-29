package com.quasar.operation.message.processor;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import com.quasar.operation.message.processor.exceptions.IncorrectDistancesExeption;
import com.quasar.operation.message.processor.exceptions.IncorrectMessagesNumberExeption;
import com.quasar.operation.message.processor.exceptions.MessageProcessorExeption;

import lombok.AllArgsConstructor;
import lombok.Setter;

@Setter
@AllArgsConstructor
public class MessageProcessor {
    private List<Point> satellites;
    private Float tolerance;

    public Point getLocation(Float... distances) throws MessageProcessorExeption {
        validateMessageSize(distances.length);
        Point txPoint = MathUtil.calcTrilateration(satellites, distances);
        validateTrilaterationPoint(txPoint, distances);
        return txPoint;
    }

    public String getMessage(String[]... messages) throws MessageProcessorExeption {
        validateMessageSize(messages.length);
        List<String[]> resultList = Arrays.stream(messages)
                .map(ArraysUtil::trimArray)
                .collect(Collectors.toList());
        int messageSize = ArraysUtil.getMaxSize(resultList);
        resultList = resultList.stream()
                .map(s -> ArraysUtil.fillArrayWithBlackValues(s, messageSize)).collect(Collectors.toList());
        
        String[] result = ArraysUtil.unificateListValue(resultList, messageSize);

        return String.join(" ", result);
    }

    private void validateMessageSize(int length) throws IncorrectMessagesNumberExeption {
        if (length != satellites.size()) {
            throw new IncorrectMessagesNumberExeption();
        }
    }

    private void validateTrilaterationPoint(Point txPoint, Float[] distances) {
        Float[] txDistances = satellites.stream()
                .map(satelite -> (float) MathUtil.distanceBetweenPoints(txPoint, satelite))
                .toArray(Float[]::new);

        boolean areEquals = IntStream.range(0, distances.length)
                .allMatch(i -> MathUtil.floatsAreEquals(txDistances[i], distances[i], tolerance));
        if (!areEquals) {
            throw new IncorrectDistancesExeption();
        }

    }

}
