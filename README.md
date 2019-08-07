# Travelling-Salesperson-Genetic-Algorithm

The travelling salesperson problem tries to find the best path along points visiting each point only once and returning to the starting point at the end. 

With a genetic algorithm, the problem is solved with a population of individuals. Each individual of the population has a fitness level. In the TSP problem, the fitness corresponds to the length of the individual’s path. The lower, the better. Individuals are chosen to create offspring through a selection process. They create an offspring with an offspring function that includes chromosome from both parents. Each offspring is also mutated to create more variety among each generation. This process is repeated through many generations until our solution no longer improves.

I first designed classes that would model points and paths on a graph. The point class creates points. The graph class generates  random points of a given size. These random points are to be used for the lifespan of the running program. The traveller class is an individual that has a path, which is random in the first generation of travellers. Their fitness is the length of their path. The population class contains an array of travellers. The first generation is an array of travellers with random paths. The class contains selections, crossover, and mutation functions.

Selection Process:
The fittest 10% of the population automatically go on to the next generation. Then the top 50% of the population are selected to create offspring. The probabilities of the top 50% are calculated and are essentially picked by a roulette type of process. 

Crossover:
Once two individuals are chosen to create offspring, they go through a process that chooses chromosomes for the offspring. The chromosomes are based off the path, an array of integers, each integer representing a point. Two random numbers are generated, a starting point and length. The starting point denotes the starting index where chromosomes will be taken from the first parent. The length denotes how many indexes over the string of chromosomes end. The remaining chromosomes come from the second parent. If the chromosome already exists in the offspring, the next index of the second parent is check and so on until a new offspring can be formed.

Mutation:
The mutation function picks to random indexes and swaps the chromosomes.

For most of the tests, the population size of each generation was 50. There was 50 points on a grid size of 100 x 100. The fittest of the first generation ranged between 2000 - 2500. The fittest becomes considerably lower the first 500 generations. It takes increasingly more generations to create individuals that are fitter. By 5000 generations, the individuals reach their peak levels of fitness. Any more than this are negligible differences. At generation 5000, the fitness ranges from 600 - 800.
