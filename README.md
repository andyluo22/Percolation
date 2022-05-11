# Percolation 💧 🧊

## The Problem
In recent years, percolation theory, the mathematical study of percolation, has provided new insights and techniques into studying the broad range of the natural sciences.  **Percolation** in these terms, typically refers to **movement** and **flow** - usually of filtering fluids through porous materials.  Interestingly, by modelling a percolation system, we are able to solve various problems such as: determining what fraction of a material needs to be metallic so a composite system is an electrical conductor, or under what conditions water will be able to filter through from the top to bottom of a porous landscape with water on the surface and oil below.  In these scientific problems, researchers have been interested in determining whether a system percolates.  Mathematical solutions for determining this threshold number is yet to be derived so the main **objective** is to write a program that is able to estimate the value of the **percolation threshold** through using statistic measures and visual models.

![image](https://user-images.githubusercontent.com/68613171/167926535-c4a36f2c-7e29-43bf-abe5-507ebd32bc53.png)

**Figure 1:**  _To visualize the percolation model above, we can imagine each square constructing the system to be a site that is either open, letting water to flow through, or closed, ceasing water flow.  We initially start with a grid fully composed of closed sites. Each site is then uniformly at random being chosen to be open. If there leads a path where the open top site is connected to one of the open bottom sites, then the system can be determined as a system that percolates_

## Significance
The main challenges of writing a program that can efficiently determine the percolation threshold after running an n-by-n grid through a series of trials comes from creating a Percolation object as well as any instance methods that do not interfere with the **immutability** of the object itself.  Thus, the source code ensures immutability of these objects and instance methods.  Another strategy in solving this problem was to do as much **caching** as possible when constructing each Percolation grid object. By precomputing certain values and caching these values as instances, the time complexity and run time was greatly reduced throughout debugging the program since the statistical measures required lots of computation over many trials.

### Monte Carlo Simulation :bulb:	
To further elaborate on how the Monte carlo simulation estimates the percolation threshold, the program runs an experiment that:

_1._ Initializes all sites to be blocked 

_2._ Chooses a site among one of the closed sites uniformly at random and then opens it until the system percolates

_3._ The fraction of sites that are opened when the system percolates out of the total n-by-n grid spaces or sites provides an estimate of the percolation threshold value

By looking at the source code in percolation stats, this experiment is then computed several times through different trials and averages throughout to obtain a more accurate estimate of the percolation threshold for a specific n-by-n input.  Some important statistical attributes that were used to characterize the system were: mean, standard deviation, and the low and high enpoint of the 95% confidence threshold.

### Percolation Statistics - Estimating the Threshold Value 


#### Performance :chart_with_upwards_trend: :stopwatch:
Since the goal of this project is to model an n-by-n grid, each method in the implementation takes time proportional to n<sup> 2 </sup>.  All instance methods in the Percolation class also take constant time to operate.  Exceptions are also handled and catched.
