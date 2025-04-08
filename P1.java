/*
Time Complexity : O(N^2)
Space Complexity : O(N*N)

BFS starts with the original string and explores all possibilities by removing one parenthesis at a time.

For each new string, check if it's valid:

If valid, add to the result.

If not, continue to generate new strings by removing one parenthesis and adding them to the queue.

Stop as soon as any valid strings are found (thanks to the found flag), ensuring minimal removals.
 */

class Solution {
    public List<String> removeInvalidParentheses(String s) {
        if(s == null || s.length() == 0) {
            return new ArrayList<>();
        }
        List<String> result = new ArrayList<>();
        
        Queue<String> queue = new LinkedList<>();
        HashSet<String> set = new HashSet<>();
        boolean found = false;
        
        queue.add(s);
        set.add(s);
        
        
        while(!queue.isEmpty() && !found) {
            int sz = queue.size();
            
            for(int i = 0; i < sz; i++) {
                String current = queue.poll();
                
                if(isValid(current)) {
                    result.add(current);
                    found = true;
                } else {
                    if(!found) {
                        for(int j = 0; j < current.length(); j++) {
                            if(Character.isLetter(current.charAt(j))) continue;
                            String child = current.substring(0,j) + current.substring(j+1);
                            if(!set.contains(child)) {
                                queue.add(child);
                                set.add(child);
                            }
                        }
                    }
                }
            }
        }
        return result; 
    }
    private boolean isValid(String s) {
        int count = 0;
        for(int i = 0 ; i < s.length(); i++) {
            if(s.charAt(i) == ')') {
                count--;
            }
            if(s.charAt(i) == '(') {
                count++;
            }
            if(count < 0) {
                return false;
            }
        }
        return count == 0;
    }
}