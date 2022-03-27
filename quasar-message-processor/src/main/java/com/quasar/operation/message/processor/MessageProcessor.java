package com.quasar.operation.message.processor;

import java.util.List;
import java.util.stream.IntStream;

import com.quasar.operation.message.processor.exceptions.IncorrectDistancesExeption;
import com.quasar.operation.message.processor.exceptions.IncorrectMessagesNumberExeption;
import com.quasar.operation.message.processor.exceptions.MessageProcessorExeption;

import lombok.Setter;

@Setter
public class MessageProcessor {
    private List<Point> satellites;
    private Float tolerance;

    public Point getLocation(Float... distances) throws MessageProcessorExeption{
        validateDistances(distances);
        Point txPoint = MathUtil.calcTrilateration(satellites, distances);
        validateTrilaterationPoint(txPoint, distances);
        return txPoint;
    }

    public String getMessage(String... messages) throws MessageProcessorExeption{
        return null;
    }

    
    private void validateDistances(Float[] distances) throws IncorrectMessagesNumberExeption{
        if (distances.length != satellites.size()){
            throw new IncorrectMessagesNumberExeption();
        }
    }

    private void validateTrilaterationPoint(Point txPoint, Float[] distances){
        Float[] txDistances = satellites.stream()
            .map(satelite-> (float)MathUtil.distanceBetweenPoints(txPoint, satelite))
            .toArray(Float[]::new);
            
        boolean areEquals = IntStream.range(0, distances.length).allMatch(i -> MathUtil.floatsAreEquals(txDistances[i],  distances[i], tolerance));
        if (!areEquals) {
            throw new IncorrectDistancesExeption();
        }    
        
    }

}
