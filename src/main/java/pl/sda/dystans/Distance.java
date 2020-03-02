package pl.sda.dystans;

public class Distance {
    private int point1;
    private int point2;
    private double distance;

    public Distance(int point1, int point2, double distance) {
        this.point1 = point1;
        this.point2 = point2;
        this.distance = distance;
    }

    public int getPoint1() {
        return point1;
    }

    public int getPoint2() {
        return point2;
    }

    public double getDistance() {
        return distance;
    }
}
