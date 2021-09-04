package com.epam.rd.autotasks.triangle;

import static java.lang.Math.sqrt;
import static java.lang.StrictMath.pow;

class Segment {

    private final Point start;
    private final Point end;

    public Point getStart() {
        return start;
    }

    public Point getEnd() {
        return end;
    }

    public Segment(Point start, Point end) {

        if (start == null) throw new RuntimeException("start == null");
        if (end == null) throw new RuntimeException("end == null");
        if (start.getX() == end.getX() && start.getY() == end.getY()) throw new RuntimeException("start == end");
        this.start = start;
        this.end = end;
    }

    double length() {
        return sqrt(pow(end.getX() - start.getX(), 2) + Math.pow(end.getY() - start.getY(), 2));
    }

    Point middle() {
        double x = (start.getX() + end.getX()) / 2;
        double y = (start.getY() + end.getY()) / 2;
        return new Point(x, y);
    }

    Point intersection(Segment another) {

        double a, b, c, d, x, y;

        // y = ax + b
        a = getA(start, end);
        b = getB(start, end);

        // y = cx + d
        c = getA(another.getStart(), another.getEnd());
        d = getB(another.getStart(), another.getEnd());

        if (a == c) // && b != d
            return null;

        if (start.getX() == end.getX())
            x = start.getX();
        else if (another.start.getX() == another.end.getX())
            x = another.start.getX();
        else
            x = (d - b) / (a - c);

        if (start.getY() == end.getY())
            y = start.getY();
        else if (another.start.getY() == another.end.getY())
            y = another.start.getY();
        else
            y = a * x + b;

        return new Point(x, y);

    }

    public static double getA(Point start, Point end) {
        return (end.getX() - start.getX()) == 0 ? 0 : (end.getY() - start.getY()) / (end.getX() - start.getX());
    }

    public static double getB(Point start, Point end) {
        if (end.getX() == start.getX())
            return end.getX();
        return start.getY() - (end.getY() - start.getY()) / (end.getX() / start.getX() - 1);
    }

}
