//Time =
//Space = 

class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        // Step 1: Create a graph to represent the courses and their prerequisites
        // Using an adjacency list representation
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) {
            graph.add(new ArrayList<>());
        }
        
        // Step 2: Add the prerequisites as edges in the graph
        // Also, keep track of the number of prerequisites (indegree) for each course
        int[] indegrees = new int[numCourses];
        for (int[] prereq : prerequisites) {
            int course = prereq[0];
            int prerequisite = prereq[1];
            graph.get(prerequisite).add(course); // Add the course as a neighbor of the prerequisite
            indegrees[course]++; // Increment the indegree of the course
        }
        
        // Step 3: Use a queue to keep track of the courses that can be taken
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if (indegrees[i] == 0) {
                queue.offer(i); // Add courses with indegree 0 to the queue
            }
        }
        
        // Step 4: Take courses that have no prerequisites until no more courses can be taken
        int numTaken = 0;
        while (!queue.isEmpty()) {
            int course = queue.poll(); // Get a course with indegree 0
            numTaken++; // Increment the number of courses taken
            for (int neighbor : graph.get(course)) { // Iterate over the neighbors of the course
                indegrees[neighbor]--; // Decrement the indegree of the neighbor
                if (indegrees[neighbor] == 0) {
                    queue.offer(neighbor); // If the neighbor now has indegree 0, add it to the queue
                }
            }
        }
        
        // Step 5: If all courses can be taken, return true; otherwise, return false
        return numTaken == numCourses;
    }
}


//Brute force
//Doesn't work for all test cases!

class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        // Step 1: Build a graph of prerequisites
        // Create a HashMap to map each course to its list of prerequisites
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for (int i = 0; i < prerequisites.length; i++) {
            int course = prerequisites[i][0];
            int prereq = prerequisites[i][1];
            if (!graph.containsKey(course)) {
                graph.put(course, new ArrayList<>());
            }
            graph.get(course).add(prereq);
        }
        
        // Step 2: Check for cycles in the graph using DFS
        // For each course, mark it as visited and recursively call the hasCycle function on its prerequisites
        for (int i = 0; i < numCourses; i++) {
            // Create a boolean array to track which courses have been visited
            boolean[] visited = new boolean[numCourses];
            if (hasCycle(graph, i, visited)) {
                // If a cycle is found, return false
                return false;
            }
        }
        
        // If no cycles are found, return true
        return true;
    }
    
    // Recursive helper function to check for cycles in the graph using DFS
    private boolean hasCycle(Map<Integer, List<Integer>> graph, int course, boolean[] visited) {
        // If the course has already been visited, there is a cycle in the graph
        if (visited[course]) {
            return true;
        }
        // Mark the course as visited and check its prerequisites
        visited[course] = true;
        if (!graph.containsKey(course)) {
            // If the course has no prerequisites, return false
            return false;
        }
        // Recursively call the hasCycle function on each prerequisite
        for (int prereq : graph.get(course)) {
            if (hasCycle(graph, prereq, visited)) {
                // If a cycle is found, return true
                return true;
            }
        }
        // Unmark the course as visited and return false if no cycles are found
        visited[course] = false;
        return false;
    }
}
