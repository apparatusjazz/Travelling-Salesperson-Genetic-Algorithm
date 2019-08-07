import java.util.HashMap;
import java.util.Map;

public class GeneticTSPMain {

    public static void main(String [] args) {

        PointGraph p = new PointGraph(5, 100, 100);
        ConnectedGraph g = new ConnectedGraph(p);

        Map<Point, Point> m = new HashMap<>();
        g.setPointNames();


        Population a = new Population(1);

        Map<Point, Point> nn = ConnectedGraph.generateRanPaths();

        a.generateRandPopulation();
        a.sortByFitness();


        for (Map.Entry entry : a.travellers.get(0).connectedPoints.entrySet())
        {
            System.out.println("key: " + entry.getKey() + "; value: " + entry.getValue());
        }
        System.out.println("Connected path size: " + a.travellers.get(0).connectedPaths);
        for (int i = 0; i < a.travellers.get(0).pathString.size(); i ++) {
            System.out.println(a.travellers.get(0).pathString.get(i) + " ======== pathhstring");
            a.travellers.get(0).connectedPaths.get(i).printPath();
        }


        //a.crossOver(a.travellers.get(0), a.travellers.get(0));


    }
}
