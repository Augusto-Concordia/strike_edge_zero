## Part A

```
Algorithm SolveStrikeEdgeZeroRecursive(D, sum):
    Input: Dataset (D) with the game configuration and the current signed sum of our moves.
    Output: True if the configuration is solvable; false otherwise.
    
    if sum <= 0 or D[sum] = _invalid_ then //we've started at an invalid index or we're looping
        return false
    
    if we reached the end then 
        return true
       
    move ← D[sum]
    currentMove ← 0
    
    if sum + move < length of D then
        currentMove ← move
    else if sum - move >= 0
        currentMove ← -move
    
    D[sum] = _invalid_
    sum ← sum + currentMove
    
    return SolveStrikeEdgeZeroRecursive(D, sum)

```

## Part B

```
Algorithm SolveStrikeEdgeZeroIterative(D, sum):
    Input: Dataset (D) with the game configuration and the current signed sum of our moves.
    Output: True if the configuration is solvable; false otherwise.
    
    positions ← _array list with capacity length of D_
    
    for i ← 0 to length of D do
        positions.add(i, D[i])
    
    while sum > 0 and positions.get(sum) != _invalid_
        move ← positions.get(sum)
        currentMove ← 0
    
        if sum + move < positions.size() then
            currentMove ← move
        else if sum - move >= 0
            currentMove ← -move
    
        positions.set(sum, _invalid_)
        sum ← sum + currentMove
    
    return sum = length of D - 1
```

## a)

Both of my implementations run in `O(n)` and have a space complexity of `O(n)` as well.

For part A, the maximum number of times the method will run is n times: worst case scenario is that the dataset is
filled with ones, and it goes through every element of the dataset before arriving to a solution. Also since the maximum
number of times the recursive method is going to be called is `n` and there is no array allocation, space complexity
equals time complexity.

For part B, the same worst case scenario applies for time complexity on top of the array copying needed at the beginning
of the method. However, for space complexity, since we copy the array into the `ArrayList` we get an equivalent space
complexity of `O(n)`.

## b)

If Java optimized tail-recursive algorithms, the space complexity of implementation A could be reduced to `O(1)`. That
is because once the method has run we could pop it from the stack and push the next one at its place, therefore never
increasing the stack size.
However, since it does not, space complexity stays the same, as does time complexity.

## c)

Choosing a data structure for this problem is adding complexity to a problem that does not need it. So I went for
an `ArrayList` to get the most similar behaviour to a standard array that I could.

## d)

It is possible to detect unsolvable datasets (it's what I did), by checking whether the current index has already been
visited. Making the analogy to a graph, once there's a cycle we'll never get out of the loop between the subgraph of
that cycle.

That being said, I don't think execution time could be improved. Once a cycle is detected, the method exits and declares
the dataset to be unsolvable, and it's a very sequential problem, so there isn't any room to make it parallelized.
However, there could be a math theorem somewhere that defines a simpler rule through proof. 