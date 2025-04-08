/*
 * BFS 

Time Complexity (TC):
O(N + E), where N is the number of nodes and E is the number of edges in the graph. We process each node and each edge once during the BFS traversal.

Space Complexity (SC):
O(N), where N is the number of nodes. The space is used for the map (storing cloned nodes) and the queue (for BFS).

Code Explanation:
The algorithm uses BFS to traverse the graph, cloning each node and linking its neighbors. A map keeps track of original-to-clone mappings, 
and the queue ensures all nodes are processed. The solution returns the deep copy of the graph starting from the input node
 */

 class Solution {
    public Node cloneGraph(Node node) {
        // Edge case: if the input node is null, return null as there is no graph to clone
        if (node == null) return null;
        
        // Map to store original node to its clone (deep copy)
        HashMap<Node, Node> map = new HashMap<>();
        
        // Queue for BFS traversal
        Queue<Node> q = new LinkedList<>();
        
        // Create the first clone (node's deep copy) and put it in the map
        Node newNode = new Node(node.val);  // Clone the value of the starting node
        map.put(node, newNode);  // Map original node to its clone
        q.add(node);  // Enqueue the starting node to begin BFS
        
        // Perform BFS to traverse through all the nodes of the graph
        while (!q.isEmpty()) {
            Node curr = q.poll();  // Dequeue the current node from the queue
            List<Node> neighbors = curr.neighbors;  // Get the neighbors of the current node
            
            // Process each neighbor of the current node
            for (Node ne : neighbors) {
                // If the neighbor has not been cloned yet, create a clone for it
                if (!map.containsKey(ne)) {
                    Node copyNode = new Node(ne.val);  // Clone the neighbor node
                    map.put(ne, copyNode);  // Map the original neighbor to its clone
                    q.add(ne);  // Enqueue the neighbor for future processing
                }
                
                // Add the cloned neighbor to the current node's clone's neighbors list
                map.get(curr).neighbors.add(map.get(ne));
            }
        }
        
        // After BFS, the deep copy of the graph is ready, return the clone of the starting node
        return newNode;
    }
}
