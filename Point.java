public class Point {

    private int x, y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;

    }
    public Point (Point a) {
        this.x = a.getX();
        this.y = a.getY();
    }
    public void printPoint() {
        System.out.println("(" + this.x + ", " + this.y + ")");
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
