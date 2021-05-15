# LOCTool
**LOCTool** is a tool (in Java) that measures the physical length of a Java project. Source lines of code (SLOC), also known as lines of code (LOC), is a common software metric to indicate the efforts for developing and maintaining software.There are several tools (e.g. CLOC, pygount, Understand, etc.) that can measure LOC. 

In this code, they are 5 metrics that are calculated:
1.	Total number of Java files
This is calculated by reading all the file names that end with .java extension. They are stored in ** and this will give the value of total java files in any given directory. 
1.	Total number of distinct java files
To calculate the number of distinct files in the given directory, it need to evaluate the content in the files. Just measuring the size and name does not help. So SHA hashing is performed to generate the unique hash values of each file[1]. This hashvalues would be same for identical files. The identical files are not considered in this metric, just considering all the unique files based on their respective hash values serves the purpose.
1.	Total number of Blank lines in the distinct files
This is obtained by trimming the lines and checking if they are empty. 
1.	Total number of Comment lines in the distinct files
Following all the comment conventions, the comment lines are considered. The comment lines that are appended with a code line are not considered. 
1.	Total number of code lines in the file.  
Any line which is neither a comment nor a code line nor a blank line is considered as a code line in this scenario[2]. 

##### Sample output 
![Output_1](https://user-images.githubusercontent.com/47337879/118347223-8a0a4d80-b50f-11eb-8075-6eeb15527fed.jpg)

How to run the program:  
(*you need the Maven (3.6.3) and Java (13.0.2) versions*)
- Clean the project and generate an executable JAR with dependencies.  
 * `mvn clean`
 * `mvn install`
- Now, there should be a new folder called target and the executable JAR with
dependencies named assignment-1.0.jar should be inside the target folder.
- Now, run the jar file and get the results 👍

References:  
1.http://www.assafelovic.com/  
2.http://www.stackoverflow.com/
