package discrete.math;


public class Edge {

    private int source, destination, distance;

    Edge(int source, int destination, int distance){
        this.source = source;
        this.destination = destination;
        this.distance = distance;
    }

    public int getSource() {
        return source;
    }

    public void setSource(int source) {
        this.source = source;
    }

    public int getDestination() {
        return destination;
    }

    public void setDestination(int destination) {
        this.destination = destination;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

}
