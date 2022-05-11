# Percolation 💧 🧊

## The Problem
In recent years, percolation theory, the mathematical study of percolation, has provided new insights and techniques into studying the broad range of the natural sciences.  **Percolation** in these terms, typically refers to **movement** and **flow** - usually of filtering fluids through porous materials.  Interestingly, by modelling a percolation system, we are able to solve various problems such as: determining what fraction of a material needs to be metallic so a composite system is an electrical conductor, or under what conditions water will be able to filter through from the top to bottom of a porous landscape with water on the surface and oil below.  In these scientific problems, researchers have been interested in determining whether a system percolates.  Mathematical solutions for determining this threshold number is yet to be derived so the main **objective** is to write a program that is able to estimate the value of the **percolation threshold** through using statistic measures and visual models.

![image](https://user-images.githubusercontent.com/68613171/167939985-b89bb3af-d274-477c-b42d-57473de2b841.png)

**Figure 1:**  _To visualize the percolation model above, we can imagine each square constructing the system to be a site that is either open, letting water to flow through, or closed, ceasing water flow.  We initially start with a grid fully composed of closed sites. Each site is then uniformly at random being chosen to be open. If there leads a path where the open top site is connected to one of the open bottom sites, then the system can be determined as a system that percolates_

## Significance
The main challenges of writing a program that can efficiently determine the percolation threshold after running an n-by-n grid through a series of trials comes from creating a Percolation object as well as any instance methods that do not interfere with the **immutability** of the object itself.  Thus, the source code ensures immutability of these objects and instance methods.  Another strategy in solving this problem was to do as much **caching** as possible when constructing each Percolation grid object. By precomputing certain values and caching these values as instances, the time complexity and run time was greatly reduced throughout debugging the program since the statistical measures required lots of computation over many trials.

### Monte Carlo Simulation :bulb:	
To further elaborate on how the **Monte carlo simulation** estimates the percolation threshold, the program runs an experiment that:

_1._ Initializes all sites to be blocked 

_2._ Chooses a site among one of the closed sites uniformly at random and then opens it until the system percolates

_3._ The fraction of sites that are opened when the system percolates out of the total n-by-n grid spaces or sites provides an estimate of the percolation threshold value

By looking at the source code in percolation stats, this experiment is then computed several times through several **trials** and the average of these values is then obtained for a more accurate estimate of the percolation threshold for a specific n-by-n input.  Some important statistical attributes that were used to characterize the system were: **mean**, **standard deviation**, and the **low** and **high endpoint** of the **95% confidence threshold**.

### Percolation Statistics - Estimating the Threshold Value 

![image](https://user-images.githubusercontent.com/68613171/167933268-9f1e8bfd-89a2-4472-ba03-9afafbd23e69.png)

**Figure 2:**  The percolation threshold has been rigorously computed by researchers to be approximately around **0.593**.  In other words, **59.3%** of the sites in an n-by-n grid have to be open sites in order for a system to percolate in **worst case scenarios**.

Thus, the mean values of the percolation threhsold through many trials of running a PercolationStats object was seen to be around approximately in the range **0.530 - 0.580** - deviating from the theoretical value by a small range.  I was unable to achieve a value that was above 0.580 through several runs in the sample client and this is very reasonable since 0.593 is usually achieved in the worst case.  By running the same inputs, we also expect different mean results as these sites are opened at uniformly random - there is an internal source of **randomness** that changes the outcome each time despite inputting the same parameters.

#### Performance :chart_with_upwards_trend: :stopwatch:
Since the goal of this project is to model an n-by-n grid, each method in the implementation takes time proportional to n<sup> 2 </sup>.  All instance methods in the Percolation class also take constant time to operate.  Exceptions are also handled and catched.
