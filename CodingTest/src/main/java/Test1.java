import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Test1 {
    public static void main(String[] args) {
        List<List<Integer>> condition1 = satisfyCondition1();
        List<List<Integer>> finalResult = satisfyCondition2(condition1);

        for (List list : finalResult) {
            System.out.println(list.get(0) + "" + list.get(1) + "\n*" + list.get(2) + "\n" + list.get(3) + "" + list.get(4));
            System.out.println("+" + list.get(5) + "" + list.get(6) + "\n" + list.get(7) + "" + list.get(8));
            System.out.println("-------");
        }

    }

    /**
     * This method takes a list of 5 unique numbers where d and e are last two numbers and chooses two numbers f and g which satisfy the below condition
     *  d    e
     * +f   g
     * -------
     * m    n
     * @param listOf5UniqueNos - list of 5 unique numbers
     * @return list of 9 unique nos which satisfy the above condition
     */
    private static List<List<Integer>> satisfyCondition2(List<List<Integer>> listOf5UniqueNos) {
        List<List<Integer>> finalResult = new ArrayList<>();
        for (int i = 0; i < listOf5UniqueNos.size(); i++) {
            List list = listOf5UniqueNos.get(i);
            List<Integer> skipList = new ArrayList<>(list);
            //Increase the skiplist to accomodate the coming combinations of f and g.
            skipList.add(skipList.get(0));
            skipList.add(skipList.get(0));
            int d = skipList.get(3);
            int e = skipList.get(4);
            for (int f = 0; f < 10; f++) {
                if (skipList.contains(f)) continue;
                skipList.set(5, f);
                for (int g = 0; g < 10; g++) {
                    if (skipList.contains(g)) continue;
                    skipList.set(6, g);
                    int sum = 10 * d + e + 10 * f + g;
                    int m = sum / 10, n = sum % 10;
                    if (sum > 99) continue;
                    if (skipList.contains(m) || skipList.contains(n) || m == n) continue;
                    List<Integer> newMatchResult = addNewMatches(list, f, g, m, n);
                    finalResult.add(newMatchResult);
                }
            }
        }
        return finalResult;
    }

    /**
     * This method chooses unique a, b, c such that it derives unique d, e where below condition is met
     *  a   b
     *  X   c
     *  ------
     *  d   e
     * @return list of matching a, b, c, d, e which meets above condition
     */
    private static List<List<Integer>> satisfyCondition1() {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> skipList = Arrays.asList(0, 0, 0);
        for (int a = 0; a < 10; a++) {
            skipList.set(0, a);
            for (int b = 0; b < 10; b++) {
                if (skipList.contains(b)) continue;
                skipList.set(1, b);
                for (int c = 0; c < 10; c++) {
                    if (skipList.contains(c)) continue;
                    skipList.set(2, c);
                    int sum = (10 * a + b) * c;
                    if (sum > 99) continue;
                    int d = sum / 10, e = sum % 10;
                    if (skipList.contains(d) || skipList.contains(e) || d == e) continue;
                    List derivedElements = addNewMatches(Collections.emptyList(), a, b, c, d, e);
                    result.add(derivedElements);
                }
            }
        }
        return result;
    }

    private static List<Integer> addNewMatches(List list, int... f) {
        List<Integer> newMatch = new ArrayList<>();
        newMatch.addAll(list);
        if (f != null) {
            for (int nn : f) {
                newMatch.add(nn);
            }
        }
        return newMatch;
    }

}
