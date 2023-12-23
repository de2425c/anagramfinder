David Eyal
Data Structures Capstone Project - Brian Borowski
12/12/2023


Anagram Finder to find all anagrams of a provided word present in a text file

I expect the HashMap implementation to be the fastest performing data structure for the task. This is because, when inputting the data into the different data structures we have to constantly check if the anagram is already present in the table, and the hashmap provides near constant time lookups for getting the data values.


I believe the BST will be slower than AVL and slower than HashMap for similar reasons to why I believe HashMap will be the fastest. Since for every line read in the dictionary we have to check if its sorted anagram is in the tree, the look ups can run in worse than O(lg(n)) time if the BST starts to degenerate into a linkedlist. Since the main operation done is the lookups for the key, and AVL Rotations are in O(1) time, the BST will offer inferior performance than AVL Trees, and the slowest data structure for the task.


The results of the timing of my program's execution matches my expectations in terms of speed. Binary Search Trees provided the worst performance, with an average performance of 2.9954s, over double that of AVL Trees. Similarly, the HashMap implementation performed at 0.6208s, almost than half of AVL’s 1.1998s.
HashTables’ quick look up times combined with separate chaining resulted in performance vastly exceeding any other data structure.




Runtimes
time java AnagramFinder least words.txt bst
Test 1 - 2.822s
Test 2 - 2.800s
Test 3 - 3.058s
Test 4 - 3.032s
Test 5 - 3.065s
Average - 2.9954s
time java AnagramFinder least words.txt avl
Test 1 - 1.224s
Test 2 - 1.157s
Test 3 - 1.201s
Test 4 - 1.162s
Test 5 - 1.255s
Average - 1.1998s
time java AnagramFinder least words.txt hash
Test 1 - 0.637s
Test 2 - 0.621s
Test 3 - 0.570s
Test 4 - 0.622s
Test 5 - 0.654s
Average - 0.6208s

