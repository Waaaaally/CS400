import java.util.List;

public class AirGraph<NodeType,EdgeType extends Number> implements IAirGraph<NodeType,EdgeType> {

    @Override
    public boolean insertVertex(Object data) {
        
        return false;
    }

    @Override
    public boolean removeVertex(Object data) {
        
        return false;
    }

    @Override
    public boolean insertEdge(Object source, Object target, Number weight) {
        
        return false;
    }

    @Override
    public boolean removeEdge(Object source, Object target) {
        
        return false;
    }

    @Override
    public boolean containsVertex(Object data) {
        
        return false;
    }

    @Override
    public boolean containsEdge(Object source, Object target) {
        
        return false;
    }

    @Override
    public Number getWeight(Object source, Object target) {
        
        return null;
    }

    @Override
    public List shortestPath(Object start, Object end) {
        
        return null;
    }

    @Override
    public double getPathCost(Object start, Object end) {
        
        return 0;
    }

    @Override
    public boolean isEmpty() {
    
        return false;
    }

    @Override
    public int getEdgeCount() {
        
        return 0;
    }

    @Override
    public int getVertexCount() {
        
        return 0;
    }

    @Override
    public List rangedShortestPath(Object start, Object end, int maxRange) {
        
        return null;
    }

    @Override
    public List breadthFirstSearch() {
        
        return null;
    }

    @Override
    public double rangedGetPathCost(Object start, Object end, int maxRange) {
        
        return 0;
    }
    
}
