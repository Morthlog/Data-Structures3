# Data structures 3rd assignment
The goal of this project was to implement a **symbol table** using our own **randomized Binary Search Tree (BST)** implementation in order to efficiently manage and analyze financial records of potential tax evaders. We were allowed to use **only** our own data structures implementations, except for arrays, the .io library and .util.Scanner to read files and user input and Exceptions. It was developed during the 3rd semester as part of the Data Structures course at AUEB.


## Tasks

We were tasked with implementing the following:

* A **randomized BST-based symbol** table using custom [TreeNode](RandomizedBST.java) and [LargeDepositor](LargeDepositor.java).
    * An implementation of a [Double Ended Queue (deque)](StringDoubleEndedQueueImpl.java) was used during data load.
  
* A way to get the top k most suspicious [Large Depositors](LargeDepositor.java) for tax evasion.
    * For this we made use of our **priority queue** implementation from our [previous project](https://github.com/Morthlog/Data-Structures2)


## How to run

### Prerequisites
* Your machine must be able to compile at least java 8

### Steps
* Download the repository
  
* Fill [Data.txt](Data.txt) with your data in the format:
`<AFM> <FirstName> <LastName> <Savings> <TaxedIncome>`
    * AFM is a unique identifier of the entity 
* Open command line on the repository root directory and type: `javac *.java`

* Then `java RandomizedBST` to start it

A menu will appear afterwards with the options to:
* Load data from file
  
* Insert, remove, or update a depositor

* Search by AFM or last name

* Print top k suspicious depositors

* Print the full list ordered by AFM

* Get the mean savings

## Contributors
<a href="https://github.com/Morthlog/Data-Structures2/graphs/contributors">
  <img src="https://contrib.rocks/image?repo=Morthlog/Data-Structures2"/>
</a>
