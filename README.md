The  goal is  to  gain  experience  with  both  TCP  and  UDP  socket  programming  in  a  client‐server  environment.

instruction: 
Place client.java, server.java, and makefile into the same folder.
Then run "make" in command line. 
client.java and server.java are complied after "make" is called.
Open two terminals, all under the same folder.
Then run "java Server" in command line in one of the terminals to run server program.
When the n_port is printed by server, then we can use n_port to run client program in the other terminal.

Syntax: "java Client 127.0.0.1 n_port any_string"

Example 1, n_port = 45678 and the string is "abcd"
then run 
java Client 127.0.0.1 45678 "abcd" 
in command line.

Example 2, n_port = 87654 and the string is "hello world"
then run 
java Client 127.0.0.1 87654 "hello world" 
in command line.


jdk version: Java 6
