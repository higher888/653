all: java-compile java-run
	
java-compile:
	javac -cp .:junit-4.8.2.jar TestM.java
	
java-run:
	java -cp .:junit-4.8.2.jar:hamcrest-core-1.3.jar org.junit.runner.JUnitCore TestM

clean:
	rm -rf *o A.class Impossible.class M.class TestM.class B.class
