//Time = O(n+p), n is number of courses and p is number of prerequisites
//Space = O(n+p)

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

