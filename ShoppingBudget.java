import java.util.List;

/**
 * A variation of the Knapsack 0/1 problem
 * Molly wants to purchase laptops for her school. Find out how many laptops she can purchase by comparing the vendors available.
 * Each vendor sells the laptops in batches, with a quantity identifying how many laptops on each batch, and a cost for the whole batch.
 * Sample input: 50 [20,19] [24,20]
 * That means Molly has 50 dollars to spend.
 * The first vendor has 20 laptops per batch and each batch costs 24 dollars.
 * The second vendor has 19 laptops per batch and each batch costs 20 dollars.
 * The possible answers are 40 and 38.
 * If she buys from the first vendor, she will spend 48 dollars (24 * 2) and since she's buying 2 batches the total quantity is 40 (20 * 2).
 * If however she would buy from the second vendor, the maximum quantity would be 38,
 * since each batch has 19 laptops and she'd run out of money after the second batch.
 * The final answer is then 40, since 40 is higher than 38.
 */
public class ShoppingBudget {

  public static void main(String[] args) {
    ShoppingBudget sb = new ShoppingBudget();
    long start = System.currentTimeMillis();
    assert 39 == sb.findSolution(47, List.of(20, 19), List.of(24, 20));
    assert 40 == sb.findSolution(50, List.of(20, 19), List.of(24, 20));
    assert 0 == sb.findSolution(9, List.of(10,20,30,40,50,60,70,80,90,100), List.of(10,20,30,40,50,60,70,80,90,100));

    assert 125 == sb.findSolution(50, List.of(20, 19, 5), List.of(24, 20, 2));

    assert 10 == sb.findSolution(2, List.of(10), List.of(2));
    assert 20 == sb.findSolution(4, List.of(10), List.of(2));
    assert 0 == sb.findSolution(1, List.of(10), List.of(2));
    assert 41 == sb.findSolution(50, List.of(20, 1), List.of(24, 2));
  }

  private int findSolution(int moneyAvailable, List<Integer> budgetQuantities, List<Integer> budgetCosts) {
    if (moneyAvailable <= 0) {
      return 0;
    }
    int size = budgetQuantities.size();
    if (size != budgetCosts.size()) {
      throw new IllegalArgumentException("Quantity and Cost arrays must have the same size.");
    }
    int position = size - 1;
    Integer[][] memo = new Integer[size][moneyAvailable + 1];
    return findSolution(position, moneyAvailable, budgetQuantities, budgetCosts, memo);
  }

  private int findSolution(int position, int moneyAvailable, List<Integer> budgetQuantities, List<Integer> budgetCosts, Integer[][] memo) {

    if (position < 0 || moneyAvailable <= 0) { // accounting for bad parameters in the <=
      return 0;
    }
    Integer memoizedResult = memo[position][moneyAvailable];
    if (memoizedResult != null) {
      return memoizedResult;
    }

    int batchCost = budgetCosts.get(position);

    if (batchCost > moneyAvailable) {
      int result = findSolution(position - 1, moneyAvailable, budgetQuantities, budgetCosts, memo);
      memo[position][moneyAvailable] = result;
      return result;
    } else {

      int maxBatches = moneyAvailable / batchCost;

      int result = Integer.MIN_VALUE;
      for (int batches = 0; batches <= maxBatches; batches++) {
        int subTotal = batchCost * batches;
        int sumQuantity = budgetQuantities.get(position) * batches;
        int costOfChoice = sumQuantity + findSolution(position - 1, moneyAvailable - subTotal, budgetQuantities, budgetCosts, memo);
        result = Math.max(result, costOfChoice);
      }
      memo[position][moneyAvailable] = result;
      return result;
    }
  }

}