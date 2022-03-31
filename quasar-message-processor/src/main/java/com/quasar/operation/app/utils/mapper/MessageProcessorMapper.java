package com.quasar.operation.app.utils.mapper;

import java.util.List;

import com.quasar.operation.app.dto.Position;
import com.quasar.operation.message.processor.Point;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MessageProcessorMapper {

    public Point positionToPoint(Position position);

    public List<Point> listOfPositionToListOfPoint(List<Position> positions);

    public Position pointToPosition(Point point);

}
