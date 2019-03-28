import java.util.List;
import java.util.stream.IntStream;

public class ShoppingBudget {

  public static void main(String[] args) {
    ShoppingBudget sb = new ShoppingBudget();
    assert 40 == sb.budgetShopping(50, List.of(20, 19), List.of(24, 20));
    assert 20 == sb.budgetShopping(4, List.of(10), List.of(2));
    assert 0 == sb.budgetShopping(1, List.of(10), List.of(2));
    assert 41 == sb.budgetShopping(50, List.of(20, 1), List.of(24, 2));
  }

  private void permute(int start, int moneyAvailable, int[] inputIndices,
      List<Integer> budgetQuantities, List<Integer> budgetCosts, MaxQuantity maxQuantity) {
    if (start == inputIndices.length) { // base case
      
      int possibleMax = findSolution(inputIndices, moneyAvailable, budgetQuantities, budgetCosts);
      
      maxQuantity.value = Math.max(maxQuantity.value, possibleMax);
      
      return;
    }
    for (int i = start; i < inputIndices.length; i++) {
      // swapping
      int temp = inputIndices[i];
      inputIndices[i] = inputIndices[start];
      inputIndices[start] = temp;
      // swap(input[i], input[start]);

      permute(start + 1, moneyAvailable, inputIndices, budgetQuantities, budgetCosts, maxQuantity);
      // swap(input[i],input[start]);

      int temp2 = inputIndices[i];
      inputIndices[i] = inputIndices[start];
      inputIndices[start] = temp2;
    }
  }

  private int findSolution(int[] inputIndices, int moneyAvailable, 
      List<Integer> budgetQuantities, List<Integer> budgetCosts) {
    int remaining = moneyAvailable;
    int counter = 0;

    for (int index : inputIndices) {
      if (remaining == 0) {
        break;
      }
      int quantity = budgetQuantities.get(index);
      int cost = budgetCosts.get(index);

      if (cost <= remaining) {
        int howManyToBuy = remaining / cost;
        counter += howManyToBuy * quantity;
        remaining -= (howManyToBuy * cost);
      }
    }

    return counter;
  }

  private int budgetShopping(int n, List<Integer> budgetQuantities,
      List<Integer> budgetCosts) {
    int[] inputIndices = IntStream.rangeClosed(0, budgetQuantities.size() - 1).toArray();
    MaxQuantity maxQuantity = new MaxQuantity();
    maxQuantity.value = Integer.MIN_VALUE;
    permute(0, n, inputIndices, budgetQuantities, budgetCosts, maxQuantity);
    
    return maxQuantity.value;
  }
}

class MaxQuantity {
  int value;
}
