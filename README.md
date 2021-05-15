# LOCTool
LOCTool is a tool (in Java) that measures the physical length of a Java project. Source lines of code (SLOC), also known as lines of code (LOC), is a common software metric to indicate the efforts for developing and maintaining software.

In this code, they are 5 metrics that are calculated:
1.	Total number of Java files
This is calculated by reading all the file names that end with .java extension. They are stored in ** and this will give the value of total java files in any given directory. 
2.	Total number of distinct java files
To calculate the number of distinct files in the given directory, it need to evaluate the content in the files. Just measuring the size and name does not help. So SHA hashing is performed to generate the unique hash values of each file[1]. This hashvalues would be same for identical files. The identical files are not considered in this metric, just considering all the unique files based on their respective hash values serves the purpose.
3.	Total number of Blank lines in the distinct files
This is obtained by trimming the lines and checking if they are empty. 
4.	Total number of Comment lines in the distinct files
Following all the comment conventions, the comment lines are considered. The comment lines that are appended with a code line are not considered. 
5.	Total number of code lines in the file.  
Any line which is neither a comment nor a code line nor a blank line is considered as a code line in this scenario[2]. 

References:
1.http://www.assafelovic.com/
2.http://www.stackoverflow.com/
