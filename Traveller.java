import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Traveller {

    //Order to call Traveller functions is setConnectedPoints(), setConnectedPaths(), set pathString(), calculateFitness()

    public static ConnectedGraph destinations;
    public ArrayList<Integer> pathString = new ArrayList<>();   //The path traveller takes, only names of points
    public ArrayList<Path> connectedPaths = new ArrayList<>();  //The path traveller takes through points
    public Map<Point, Point> connectedPoints = new HashMap<>();//A map of connected points the traveller goes through

    public double fitness;                         //Basically the distance of all paths

    public Traveller () {
        //this.destinations = destinations;
    }

    public void setConnectedPoints() {
        this.connectedPoints = ConnectedGraph.generateRanPaths();
    }

    public void setConnectedPaths(Map<Point, Point> pointConnections) {
        this.connectedPaths = ConnectedGraph.connectPaths(pointConnections);
    }

    public void setPathString(ArrayList<Path> connectedPaths, Map<Point, Point> connectedPoints) {
        this.pathString = ConnectedGraph.getPathString(connectedPaths, connectedPoints);
    }
    public void calculateFitness() {
        double fitness = ConnectedGraph.calculatePathDistance(this.connectedPaths);
        this.fitness = fitness;
    }

}
