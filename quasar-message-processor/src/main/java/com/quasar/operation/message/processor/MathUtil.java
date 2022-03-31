package com.quasar.operation.message.processor;

import java.util.List;

public class MathUtil {

    private MathUtil() {
    }

    public static Float distanceBetweenPoints(Point p1, Point p2){
        return (float) Math.sqrt(Math.pow(p1.getX()-p2.getX(),2)+Math.pow(p1.getY()-p2.getY(),2));
    }

    public static Boolean floatsAreEquals(float f1, float f2, Float tolerance){
        return Math.abs(f1-f2)<tolerance;
    }

    public static Point calcTrilateration(List<Point> point, Float[] distances){
        
        float a = 2*point.get(1).getX() - 2*point.get(0).getX();
        float b = 2*point.get(1).getY() - 2*point.get(0).getY();
        float c = (float) (Math.pow(distances[0],2.0) - Math.pow(distances[1],2) 
            - Math.pow(point.get(0).getX(),2) + Math.pow(point.get(1).getX(),2) 
            - Math.pow(point.get(0).getY(),2) + Math.pow(point.get(1).getY(),2));
        float d = 2*point.get(2).getX() - 2*point.get(1).getX();
        float e = 2*point.get(2).getY() - 2*point.get(1).getY();
        float f = (float) (Math.pow(distances[1],2) - Math.pow(distances[2],2) 
            - Math.pow(point.get(1).getX(),2) + Math.pow(point.get(2).getX(),2) 
            - Math.pow(point.get(1).getY(),2) + Math.pow(point.get(2).getY(),2));
        float x = (c*e - f*b) / (e*a - b*d);
        float y = (c*d - a*f) / (b*d - a*e);
        return new Point(x,y);
    }
}
