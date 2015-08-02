A super quick and dirty blink program for Intel Edison on mini-breakout board. 
This will oscillate the J19-6 pin, you can test this with your volt meter, 
or wire up a circuit that lights an LED on when it sees 1.8v and then attach 
it to J19-6 (signal) and J19-3(gnd)

These are the pins to see this work on the mini-breakout board 

(looking at it from the bottom side, USB plugs to left, writing right side up)
          
o o o o o o o o o o o o o o J17 
o o o o o o o o o o o o o o J18
o o o o o o o o S o o G o o J19
o o o o o o o o o o o o o o J20

How to Run:

1. Set up networking/ssh on your edison (see intel's site for details)
2. clone the repository, 
3. cd into the top level
4. run 

gradlew build
scp blink-1.0-SNAPSHOT.jar root@192.168.3.161:~/

5. Ssh into your edison
6. Run the following commands:

wget --no-cookies --no-check-certificate --header "Cookie: gpw_e24=http%3A%2F%2Fwww.oracle.com%2F; oraclelicense=accept-securebackup-cookie" "http://download.oracle.com/otn-pub/java/jdk/8u51-b16/jdk-8u51-linux-i586.tar.gz"
tar xzvf jdk-8u51-linux-i586.tar.gz
jdk1.8.0_51/bin/java -jar blink-1.0-SNAPSHOT.jar