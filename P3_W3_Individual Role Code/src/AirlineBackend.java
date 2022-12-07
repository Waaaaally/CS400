import java.util.ArrayList;
import java.util.List;

public class AirlineBackend implements IAirlineBackend{
    private IAirGraph<String, Integer>  graph;

    public AirlineBackend(){
        graph = new AirGraph<String, Integer>();
    }

    @Override
    public boolean removeNode(String node) {
        return graph.removeVertex(node);
    }

    @Override
    public void insertPath(IEdge edge) {
        graph.insertEdge(edge.getSource(), edge.getDestination(), edge.getWeight());
    }

    @Override
    public boolean removeE(String node1, String node2) {
        return graph.removeEdge(node1, node2);
    }

    @Override
    public List<List<String>> getPaths(String source, String destination) {
        //No implmentation for kind of plane.
        List<List<String>> paths = new ArrayList<>();
        paths.add(graph.rangedShortestPath(source, destination, 100000));//Not sure about plane values?
        return paths;
    }

    @Override
    public List<Double> getPathCosts(String source, String destination) {
        List<Double> pathCosts = new ArrayList<>();
        ////Not sure about plane values?
        pathCosts.add(graph.rangedGetPathCost(source, destination, 100000));
        return pathCosts;
    }

    @Override
    public List<String> listNodes() {
        List<String> nodes = graph.breadthFirstSearch();
        return nodes;
    }

    @Override
    public boolean nodeExists(String node) {
        return graph.containsVertex(node);
    }
    
}
