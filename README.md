# Problem description

Molly wants to purchase laptops for her school. Find out how many laptops she can purchase by comparing the vendors available.
Each vendor sells the laptops in batches, with a quantity identifying how many laptops on each batch, and a cost for the whole batch.   
Sample input: 50 [20,19] [24,20]   
That means Molly has 50 dollars to spend.   
The first vendor has 20 laptops per batch and each batch costs 24 dollars.   
The second vendor has 19 laptops per batch and each batch costs 20 dollars.  
The possible answers are 40 and 38.   
If she buys from the first vendor, she will spend 48 dollars (24 * 2) and since she's buying 2 batches the total quantity is 40 (20 * 2).
If however she would buy from the second vendor, the maximum quantity would be 38, since each batch has 19 laptops and she'd run out of money after the second batch.  
The final answer is then 40, since 40 is higher than 38.

# Running
run with -ea to enable assertions.
