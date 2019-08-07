import java.util.ArrayList;

public class PointGraph {

    private int numPoints, width, height;
    public static ArrayList<Point> points = new ArrayList<>();


    public PointGraph (int numPoints, int width, int height) {
        this.numPoints = numPoints;
        this.width = width;
        this.height = height;
        generatePoints();
    }
    public int getNumPoints () {
        return numPoints;
    }

    private void generatePoints () {
        for (int i = 0; i < numPoints; i ++) {
            int x = (int)(Math.random() * width + 1);
            int y = (int)(Math.random() * height + 1);
            Point p = new Point(x, y);
            points.add(p);
        }
    }

    public void printGraph() {
        for (int i = 1; i < numPoints + 1; i ++) {
            System.out.println(
                    "Point " + i + ": " + "(" + points.get(i-1).getX() + ", " + points.get(i-1).getY() + ")"
            );
        }
    }
}
