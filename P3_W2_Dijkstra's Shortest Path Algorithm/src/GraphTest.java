// import org.junit.jupiter.api.Test;
// import org.junit.jupiter.api.BeforeEach;
// import static org.junit.jupiter.api.Assertions.assertTrue;

// // --== CS400 Fall 2022 File Header Information ==--
// // Name: Pritish Das
// // Email: pdas26@wisc.edu
// // Team: DN
// // TA: Rahul Konchada
// // Lecturer: Florian Heimerl
// // Notes to Grader:

// /**
//  * Tests the implementation of CS400Graph for the individual component of
//  * Project Three: the implementation of Dijsktra's Shortest Path algorithm.
//  */
// public class GraphTest {

//     private CS400Graph<String,Integer> graph;
    
//     /**
//      * Instantiate graph.
//      */
//     @BeforeEach
//     public void createGraph() {
//         graph = new CS400Graph<>();
//         // insert vertices A-F
//         graph.insertVertex("A");
//         graph.insertVertex("B");
//         graph.insertVertex("C");
//         graph.insertVertex("D");
//         graph.insertVertex("E");
//         graph.insertVertex("F");
//         // insert edges
//         graph.insertEdge("A","B",6);
//         graph.insertEdge("A","C",2);
//         graph.insertEdge("A","D",5);
//         graph.insertEdge("B","E",1);
//         graph.insertEdge("B","C",2);
//         graph.insertEdge("C","B",3);
//         graph.insertEdge("C","F",1);
//         graph.insertEdge("D","E",3);
//         graph.insertEdge("E","A",4);
//         graph.insertEdge("F","A",1);
//         graph.insertEdge("F","D",1);
//     }

//     /**
//      * Checks the distance/total weight cost from the vertex A to F.
//      */
//     @Test
//     public void testPathCostAtoF() {
//         assertTrue(graph.getPathCost("A", "F") == 3);
//     }

//     /**
//      * Compute paths from starting node A with Dijkstra's Algorithm manually,
//      * then add an extra test method to confirm the path cost you computed for node E.
//      * A, C, B, E = 6
//      */
//     @Test
//     public void testPathCostAtoE() { //
//         assertTrue(graph.getPathCost("A", "F") == 6);
//     }

//     /**
//      * Add an extra test method to confirm the sequence of nodes along the path from A to E
//      * is correct.
//      */
//     @Test
//     public void testPathAtoE() {
//         assertTrue(graph.shortestPath("A", "E").toString().equals(
//                 "[A, C, B, E]"
//         ));
//     }

//     /**
//      * Compute paths from starting node B with Dijkstra's Algorithm manually,
//      * then add another test method to confirm the path cost you reported for node F.
//      */
//     @Test
//     public void testPathCostBtoF(){
//         assertTrue(graph.getPathCost("B", "F") == 3);
//     }

//     /**
//      * Add another test method to confirm the sequence
//      * of nodes along the path from B to F is correct
//      */
//     @Test
//     public void testPathBtoF() {
//         assertTrue(graph.shortestPath("B", "F").toString().equals(
//                 "[B, C, F]"
//         ));
//     }

//     /**
//      * Checks the distance/total weight cost from the vertex A to D.
//      */
//     @Test
//     public void testPathCostAtoD() {
//         assertTrue(graph.getPathCost("A", "D") == 4);
//     }

//     /**
//      * Checks the ordered sequence of data within vertices from the vertex 
//      * A to D.
//      */
//     @Test
//     public void testPathAtoD() {
//         assertTrue(graph.shortestPath("A", "D").toString().equals(
//             "[A, C, F, D]"
//         ));
//     }

//     /**
//      * Checks the ordered sequence of data within vertices from the vertex 
//      * A to F.
//      */
//     @Test
//     public void testPathAtoF() {
//         assertTrue(graph.shortestPath("A", "F").toString().equals(
//             "[A, C, F]"
//         ));
//     }


//     /**
//      * Add at least one more test method to this class to
//      * help convince yourself of the correctness of your implementation.
//      */
//     @Test
//     public void testPathCostBtoD(){
//         assertTrue(graph.getPathCost("B", "D") == 4);
//     }

//     /**
//      * Add at least one more test method to this class to help convince yourself of the
//      * correctness of your implementation.
//      */
//     @Test
//     public void testPathBtoD() {
//         assertTrue(graph.shortestPath("B", "D").toString().equals(
//                 "[B, C, F, D]"
//         ));
//     }
// }
