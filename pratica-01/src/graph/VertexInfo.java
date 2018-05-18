package graph;

public class VertexInfo {
    public Integer predecessor;
    public double distance = 0.0;

    public VertexInfo(Integer predecessor, double distance){
        this.predecessor = predecessor;
        this.distance = distance;
    }

    @Override
    public String toString() {
        return   "predecessor = " + predecessor +
                ", distance = " + distance;
    }
}
