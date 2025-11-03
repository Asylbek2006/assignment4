Assignment 4 Report

Executive Summary

This report details the successful implementation of several fundamental algorithms used in the Smart City Scheduling problem. The algorithms and their respective results are summarized below:

Implemented Algorithms:

Tarjan's SCC Algorithm: Successfully implemented to identify Strongly Connected Components (SCC) in a directed graph.

Kahn's Topological Sort: Employed for obtaining a topological ordering of a Directed Acyclic Graph (DAG).

DAG Shortest/Longest Paths: Used for calculating shortest and longest paths in a DAG.

Test Datasets: A set of 9 test datasets of varying sizes and complexities was analyzed.

Test Results: All tests passed successfully, ensuring that the algorithms function correctly across different graph structures.

The algorithms were implemented to handle real-world scheduling problems, particularly in the context of city infrastructure tasks such as street cleaning, repairs, and maintenance. The datasets were designed to test both cyclic and acyclic graphs.

Performance Analysis

The following table summarizes the time complexity, space complexity, and status of each implemented algorithm:

Algorithm	Time Complexity	Space Complexity	Status
Tarjan’s SCC	O(V + E)	O(V)	✅
Topological Sort	O(V + E)	O(V)	✅
DAG Shortest Path	O(V + E)	O(V)	✅
DAG Longest Path	O(V + E)	O(V)	✅
Analysis:

Time Complexity: All algorithms perform linearly with respect to the number of vertices V and edges E in the graph. The time complexity of SCC, Topological Sort, and Shortest/Longest Path algorithms is O(V + E), which is optimal for graph traversal problems.

Space Complexity: The space complexity is dependent on the graph's structure. For Tarjan’s SCC and Topological Sort, space complexity is O(V) as we maintain visited nodes and recursion stacks. For the DAG Shortest and Longest Path algorithms, the space complexity is also O(V) due to the need to store distance arrays and auxiliary structures.

Status: All algorithms were implemented and tested successfully with no errors, showing efficient performance across varying sizes of graphs.

Dataset Results
Test Datasets:

A total of 9 datasets were generated and analyzed for testing the efficiency and correctness of the implemented algorithms. The datasets varied in size and complexity, with the following categories:

Small Graphs (6-10 nodes): These graphs consisted of 1-2 cycles or were purely DAGs.

Medium Graphs (10-20 nodes): Mixed structures with several SCCs and more complex interdependencies.

Large Graphs (20-50 nodes): Performance and scalability tests with a higher number of SCCs.

Results Summary:

Small Graphs: The algorithms showed efficient performance with 2-6 SCCs detected and excellent execution speed for both SCC and Topological Sort.

Medium Graphs: The algorithms performed well with 3-8 SCCs and good execution times for Topological Sort and Shortest/Longest Path algorithms.

Large Graphs: Even with 5-15 SCCs, the algorithms scaled well and handled larger datasets efficiently, demonstrating their robustness and capability to handle real-world Smart City scheduling tasks.

Detailed Results:

Small Graphs:

Number of SCCs: 2-6 SCCs

Algorithm Performance: Efficient execution, all algorithms ran in optimal time, with minimal space consumption.

Medium Graphs:

Number of SCCs: 3-8 SCCs

Algorithm Performance: Good performance with medium complexity. Algorithms continued to run efficiently, with only a slight increase in execution time compared to smaller graphs.

Large Graphs:

Number of SCCs: 5-15 SCCs

Algorithm Performance: Scales well, maintaining reasonable performance even with larger datasets. The Longest Path algorithm showed minimal overhead even with high-density graphs.

Challenges and Improvements

While the algorithms performed well, some challenges were encountered during testing:

Challenges:

Handling Cyclic Graphs: The identification of cycles in graphs was crucial for implementing Topological Sort. We ensured that cyclic graphs correctly triggered an error message ("Graph contains a cycle") as topological sorting is not possible in such graphs.

Scalability: As the graph size increased, the Shortest Path and Longest Path algorithms still managed to scale effectively. However, for graphs with high density and large SCCs, some optimization could be considered for improving runtime performance.

Possible Improvements:

Parallel Processing: For very large datasets (50+ nodes), parallel processing techniques could be applied to speed up the SCC and Shortest/Longest Path algorithms, especially when dealing with independent components.

Memory Optimization: Additional memory optimizations could be applied for graph representation, especially for large sparse graphs. Using adjacency lists with optimized storage can improve space complexity for graphs with fewer edges.

Conclusion

The Smart City Scheduling assignment successfully implemented key graph algorithms including Tarjan's SCC, Topological Sort, and DAG Shortest/Longest Paths, while thoroughly testing them on datasets of various sizes and complexities. All algorithms passed the tests successfully, demonstrating their correctness and efficiency.

Efficiency: The algorithms showed linear time complexity and space efficiency, making them suitable for practical application in smart city scheduling.

Scalability: The algorithms scaled well with increasing graph size, with minimal performance degradation for both small and large graphs.

Future Improvements: Possible future work includes parallelizing the computations for larger graphs and optimizing the space complexity for sparse graphs.

This report concludes that the algorithms are suitable for large-scale graph processing tasks and could be applied to real-world smart city scheduling problems with efficiency and scalability.