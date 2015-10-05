# Assignment 2
In this assignment, you are required to write a comparison tool for two versions of a Java source file. Your program takes as input two .java files representing those two versions and reports the following atomic changes:

##Contributors
* Matt McKillip
* Mason Berhenke
* Kyle Long

##Usage
TODO after I/O is done

##Test cases
We have three sets of test cases- simple, medium, and difficult. The files are contained in the /testingFiles folder.

### Easy
* basicTest1.java
* basicTest2.java

######Output
-----
AM funct3

DM funct2

AF integer2

DF string1


### Medium
* mediumTest1.java
* mediumTest2.java

######Output
-----
AM funct3

DM funct1

CM funct2

AF string3

DF string1, string2, integer3

CFI integer1


### Difficult
* difficultTest1.java
* difficultTest2.java

######Output
-----
CM main, funct2, funct1, funct3

CFI integer1, integer2, string1, string2

