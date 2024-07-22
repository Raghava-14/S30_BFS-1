//Time = O(n), n is the number of nodes in the binary tree
//Space = O(m), m is the maximum number of nodes at any level in the binary tree  

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    public List<List<Integer>> levelOrder(TreeNode root) {
        // Initialize the result list
        List<List<Integer>> result = new ArrayList<>();
        // If the root is null, return the empty result list
        if (root == null) {
            return result;
        }
        
        // Create a queue to store nodes at each level
        Queue<TreeNode> queue = new LinkedList<>();
        // Add the root node to the queue
        queue.offer(root);
        
        // Traverse the tree level by level using BFS
        while (!queue.isEmpty()) {
            // Get the number of nodes at the current level
            int levelSize = queue.size();
            // Create a list to store the nodes at the current level
            List<Integer> level = new ArrayList<>();
            
            // Traverse all the nodes at the current level
            for (int i = 0; i < levelSize; i++) {
                // Remove the first node from the queue
                TreeNode node = queue.poll();
                // Add the node value to the level list
                level.add(node.val);
                
                // Add the left child node to the queue if it exists
                if (node.left != null) {
                    queue.offer(node.left);
                }
                
                // Add the right child node to the queue if it exists
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
            
            // Add the nodes at the current level to the result list
            result.add(level);
        }
        
        // Return the result list
        return result;
    }
}
