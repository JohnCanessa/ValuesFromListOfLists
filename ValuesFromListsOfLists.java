import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Random;


/**
 * 
 */
public class ValuesFromListsOfLists {


    /**
     * Auxiliary class
     */
    static class Pair {
        public int val;
        public int qn;

        public Pair(int val, int qn) {
            this.val = val;
            this.qn = qn;
        }

        @Override
        public String toString() {
            return "" + val + "," + qn;
        }
    }


    /**
     * Given a list of lists the lists holding integers 
     * in ascending order but skipping values,
     * return a list of all integers in ascending order.
     * 
     * Execution: O(n) - Space: O(k)
     */
    static public List<Integer> inOrderList(List<List<Integer>> lol) {

        // **** initialization ****
        int k                   = lol.size();
        List<Integer> result    = new ArrayList<Integer>();
        PriorityQueue<Pair> pq  = new PriorityQueue<>(
            k,
            (p1, p2) -> (p1.val - p2.val)
            );

        // **** take a value from each list (prime the priority queue) - O(k) ****
        for (int i = 0; i < lol.size(); i++) {

            // **** get list from lol ****
            List<Integer> lst = lol.get(i);

            // **** get val from this list (if possible) ****
            if (lst.isEmpty()) continue;
            int val = lst.remove(0);

            // **** build pair for priority queue ****
            Pair p = new Pair(val, i);

            // **** add pair to priority queue ****
            pq.add(p);
        }

        // **** build the output list - O(n - k) ****
        while (!pq.isEmpty()) {

            // **** get next pair from the priority queue ****
            Pair p = pq.poll();

            // **** add val to result list ****
            result.add(p.val);

            // **** get list from lol ****
            List<Integer> lst = lol.get(p.qn);
        
            // **** generate pair from this list (if possible) ****
            if (lst.isEmpty()) continue;
    
            // **** pair for priority queue ****
            p = new Pair(lst.remove(0), p.qn);

            // **** add pair to priority queue ****
            pq.add(p);
        }

        // **** return the result list ****
        return result;
    }


    /**
     * Test scaffold
     */
    public static void main(String[] args) {
        
        // **** determine the number of lists to use ****
        Random rand = new Random();
        int k       = 2 + rand.nextInt(4);

        // ???? ????
        System.out.println("main <<< k: " + k);

        // **** determine the number of integers to use ****
        int n = k + rand.nextInt(k * 5);

        // ???? ????
        System.out.println("main <<< n: " + n);

        // **** declare list of lists ****
        List<List<Integer>> lol = new ArrayList<List<Integer>>(k);

        // **** create lists ****
        for (int i = 0; i < k; i++)
            lol.add(new ArrayList<Integer>());

        // **** populate lists ****
        for (int i = 0; i < n; i++)
            lol.get(rand.nextInt(k)).add(i);

        // ???? ????
        System.out.println("main <<< lol: " + lol.toString()); 

        // **** ****
        System.out.println("main <<< result: " + inOrderList(lol).toString());
    }
}