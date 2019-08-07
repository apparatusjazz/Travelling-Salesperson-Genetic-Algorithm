public class Path {

    //Path object is the path from one point to another
    public Point a, b;
    private double distance;
    public boolean isConnected;
    public Path(Point a, Point b) {
        this.a = a;
        this.b = b;
        this.distance = distance();
    }

    public double distance() {
        return Math.sqrt((a.getX()-b.getX())*(a.getX()-b.getX()) + (a.getY()-b.getY())*(a.getY()-b.getY()));
    }

    public double getDistance() {
        return distance;
    }
    public void printPath () {
        System.out.println("Distance from points " + "(" + a.getX() + ", " + a.getY() + ") " +
                " to (" + b.getX() + ", " + b.getY() + ") is " + this.getDistance());
    }
}
