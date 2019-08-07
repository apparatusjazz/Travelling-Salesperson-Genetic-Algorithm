import java.util.*;

public class Population {

    int size;
    ArrayList<Traveller> travellers = new ArrayList<>();

    public Population (int size) {
        this.size = size;

    }
    public void sortByFitness () {

        this.travellers.sort(Comparator.comparing(e -> e.fitness));
    }

    public void setPopulation (ArrayList<Traveller> travellers) {
        this.travellers = travellers;
    }
    public void generateRandPopulation () {
        ArrayList<Traveller> travellers = new ArrayList<>();
        for (int i = 0; i < size; i ++) {
            Traveller t = new Traveller();
            Map<Point, Point> connectedPoints = ConnectedGraph.generateRanPaths();
            t.setConnectedPoints();
            t.setConnectedPaths(t.connectedPoints);
            t.setPathString(t.connectedPaths, t.connectedPoints);
            t.calculateFitness();

            travellers.add(t);
        }
        this.travellers = travellers;
    }

    public Traveller crossOver(Traveller a, Traveller b) {

        for(int i = 0; i < a.pathString.size(); i ++) {
            System.out.println(a.pathString.get(i));
        }

        ArrayList<Integer> parentA = new ArrayList<>();
        ArrayList<Integer> parentB = new ArrayList<>();
        ArrayList<Integer> offspring = new ArrayList<>();
        parentA.addAll(a.pathString);
        parentB.addAll(b.pathString);

        //ArrayList<Integer> available = new ArrayList<>();
        //for(int i = 0; i < parentA.size(); i ++) {
        //    offspring.add(null);
        //}
        //available.addAll(parentB);
        //for(int i = 0; i < parentA.size(); i ++) {
          //  System.out.println(parentA.get(i));
        //}

        int randomLength = ConnectedGraph.numPoints + (int)(Math.random() * ((ConnectedGraph.numPoints) + 1));

        for(int i = 0; i < PointGraph.points.size() / 2; i ++) {
            Random generator = new Random();
            int randomIndex = generator.nextInt(parentA.size());
            System.out.println(randomIndex);
            int targetIndex = a.pathString.indexOf(parentA.get(randomIndex));

            offspring.add(targetIndex, a.pathString.get(targetIndex));
            System.out.println(a.pathString.get(targetIndex) + " pathstring");
            //Remove object so we can't pick it again randomly
            parentA.remove(parentA.indexOf(a.pathString.get(targetIndex)));
            //Remove object from parent b so when we populate the remaining indexes of offspring,
            //we don't choose the same one.
            parentB.remove(parentB.indexOf(a.pathString.get(targetIndex)));

        }
        //Now we have our offspring which contains random elements from Parent a in the same indexes
        int parentBIterator = 0;
        for(int i = 0; i < offspring.size(); i ++) {
            if (offspring.get(i) == null) //If we have null object, add the object from parent b
            {
                offspring.add(i, parentB.get(parentBIterator));
                parentBIterator++;
            }
        }

        //Now we have to convert the offspring pathString into a Traveller object

        Map<Point, Point> connectedPoints = new HashMap<>();
        Point a1, b1;
        for (int i = 0; i < offspring.size() - 1; i ++) {
            a1 = ConnectedGraph.pointNamesReverse.get(offspring.get(i));
            b1 = ConnectedGraph.pointNamesReverse.get(offspring.get(i + 1));
            connectedPoints.put(a1, b1);
        }
        connectedPoints.put(ConnectedGraph.pointNamesReverse.get(offspring.get(offspring.size() - 1)),
                ConnectedGraph.pointNamesReverse.get(offspring.get(0))); //Connect last point to first

        ArrayList<Path> connectedPaths = new ArrayList<>();
        connectedPaths = ConnectedGraph.connectPaths(connectedPoints);

        //Now we have all the components we need to make a Traveller object

        Traveller child = new Traveller();
        child.setConnectedPoints();
        child.setConnectedPaths(connectedPoints);
        child.setPathString(connectedPaths, connectedPoints);

        return child;
    }
}
