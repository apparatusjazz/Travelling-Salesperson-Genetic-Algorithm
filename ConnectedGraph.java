import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class ConnectedGraph {

    //int connections;
    public static PointGraph graph;                   //The Pointgraph with all points
    public static Map<Point, Integer> pointNames;     //Each point has and integer value as its name or identifier
    public static Map<Integer, Point> pointNamesReverse;
    public static Map<Point, Point> connectedPoints = new HashMap<>();     //A map that specifies each point and its connection

    public ArrayList<ArrayList<Path>> allPaths= new ArrayList<>();  //
    public ArrayList<Path> connectedPaths = new ArrayList<>();      //
    public static int numPoints;

    public ArrayList<Integer> pathString;   //List of integers that follow route by point names

    double totalPathDistance;           //Total distance of current paths added


    public ConnectedGraph (PointGraph graph) {
        this.graph = graph;
        this.connectedPaths = null;
        this.numPoints = graph.getNumPoints();
        //setConnections(graph);
        calculatePaths(graph);
    }
/*
    private void setConnections(PointGraph g) {
        int numPoints = g.getNumPoints(), connections = 0;
        for (int i = 1; i <= numPoints; i ++) {
            connections += i;
        }
        this.connections = connections;
    }

 */
    private void calculatePaths (PointGraph graph) {
        ArrayList<ArrayList<Path>> allPaths = new ArrayList<>();
        int numPoints = graph.getNumPoints();
        for (int i = 0; i < numPoints; i ++) {
            ArrayList<Path> pointPaths = new ArrayList<>();
            for (int j = 0; j < numPoints; j ++) {
                Path path = new Path(graph.points.get(i), graph.points.get(j));
                pointPaths.add(path);
            }
            allPaths.add(pointPaths);
        }
        this.allPaths = allPaths;
    }
    public static ArrayList<Path> connectPaths(Map<Point, Point> pointConnections) {
        ArrayList<Path> connectedPaths = new ArrayList<>();

        for (Map.Entry<Point,Point> entry : pointConnections.entrySet()) {
            Path p = new Path (entry.getKey(), entry.getValue());
            p.isConnected = true;
            connectedPaths.add(p);
            //p.printPath();
        }
        //this.connectedPaths = connectedPaths;
        return connectedPaths;
    }

    public static Map<Point, Point> generateRanPaths () {

        //System.out.println(graph.points.size() + "---size");

        Map<Point, Point> points = new HashMap<>();
        boolean first = true;
        ArrayList<Point> available = new ArrayList<>();
        //available = (ArrayList<Point>) PointGraph.points.clone();

        for(int i = 0; i < PointGraph.points.size(); i ++) {
            available.add(i, PointGraph.points.get(i));
        }
        available.remove(0);
        Point start = new Point (PointGraph.points.get(0));

        //System.out.println(start.getX() + ", " + start.getY() + " -- available size: " + available.size());
        //for (int i = 0; i < available.size(); i ++) {
         //   available.get(i).printPoint();
        //}

        //System.out.println("----------------");


        while (available.size() > 0) {

            Random generator = new Random();
            int randomIndex = generator.nextInt(available.size());
            Point end = available.get(randomIndex);
            points.put(start, end);


            available.remove(end);
            start = end;
            if(available.size() == 0 && first) { //If we don't have paths left connect the last path to start point
                available.add(graph.points.get(0));
                //available.get(available.size()-1).printPoint();
                first = false;
            }
            //System.out.println(points.get(0) + " ----available size " +  available.size());
            //connectPoints.get(start).printPoint();


        }
        //System.out.println(points.size());
        /*for (Map.Entry<Point,Point> entry : points.entrySet()){
            entry.getKey().printPoint();
            entry.getValue().printPoint();
            System.out.println("-----------");
        }*/
        //System.out.println(points.size());
        //this.connectedPoints = connectPoints;
        return points;
    }

    public void setPointNames () {
        Map<Point, Integer> pointNames = new HashMap<>();
        Map<Integer, Point> pointNamesReverse = new HashMap<>();
        for (int i = 0; i < graph.points.size(); i ++ ) {
            pointNames.put(graph.points.get(i), i);
        }
        for (int i = 0; i < graph.points.size(); i ++ ) {
            pointNamesReverse.put(i, graph.points.get(i));
        }
        this.pointNames = pointNames;
        this.pointNamesReverse = pointNamesReverse;
    }

    public static ArrayList<Integer> getPathString(ArrayList<Path> connectedPaths, Map<Point, Point> connectedPoints) {
        ArrayList<Integer> pathString = new ArrayList<>();
        System.out.println("---------------------------------------------Pathstring function");
/*
        for(int i = 0; i < connectedPaths.size(); i ++) {
            connectedPaths.get(i).printPath();
        }
*/      System.out.println("connected path size: "+ connectedPaths.size());
        Point current = connectedPaths.get(0).a;
        for(int i = 0; i < connectedPoints.size(); i ++) {
            //current.printPoint();
            pathString.add(i, pointNames.get(current));
            current = connectedPoints.get(current);
            if(current == null) {
                System.out.println("Why do we have null?????");
            }
        }
        for(int i = 0; i < connectedPaths.size(); i ++) {
            System.out.println(pathString.get(i) + " size ---------- and i is: " + i);
        }
        System.out.println("---------------------------------------------Pathstring function end");
        return pathString;
    }

    public static double calculatePathDistance(ArrayList<Path> connectedPaths) {
        double distance = 0;
        for (int i = 0; i < connectedPaths.size(); i ++) {
            distance += connectedPaths.get(i).distance();
        }
        return distance;
    }

    public void setTotalPathDistance(double d) {
        this.totalPathDistance = d;
    }
}
