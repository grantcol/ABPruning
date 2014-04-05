ABPruning
=========

A demonstration of the alpha beta pruning algorithm 

##The Algorithm 

The program implements the double recursive minimax algorithm with alpha beta pruning. The initial call is maxValue() on the root of the game tree which continues by building and adding nodes as it goes along determining all the possible moves. maxvalue() checks whether the node is terminal ( win state ) and if not it fcontinues to generate the legal moves from the inspected nodes. It continues to call minValue() on all the children of the generated max nodes and vice versa. Thus the algorithm is essentially a DFS evaluation to the terminal nodes. Once it finds the terminal nodes ad the leaves it bubbles up with the alpha and beta values. This is where the pruning happens. It limits further evaluation of children from the upper loop. 

##Running the program

You may change the input path (in Main.java) to whatever input file that has been moved into the /src folder for testing by 
<pre>
javac Main.java 
java Main
</pre>
or if yu have already compiled the code rerun by simply 
<pre>javac main.java</pre>

##Issues

There is only one issue and that is, on board configurations with a higher branching factor, the algorithm may run the JVM to heap exhaustion. Note: i have only tested this on a 32bit machine and it may differ on a 64 bit machine. This is an unfortunate issue but one that i could not find a work around. 

