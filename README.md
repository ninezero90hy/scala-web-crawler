# scala-web-crawler
A web crawler written in scala.

### For Running the program
Use the following command to run the program, this script looks for the sbt , if it is present then run's it and if not
then first downloads it and then run the program.
`./run.sh`

### Reasoning and trade offs

We used Akka for developing this solution.As it can easily be scaled by using the Router and supervisor.
To reduce the complexity the url's that do not contain `wiprodigital.com` are not visited.

### What could be done with more time

 With more time we can add the functionality of scaling it using Router and Supervisor. 
 With more time we can also add the test cases for akka actors and add the functionality for getting the images reference as well.
 
 

