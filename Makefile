run: runFDTests runBDTests

runFDTests: FrontendDeveloperTests.class
	java -jar ../junit5.jar -cp . -c FrontendDeveloperTests

Frontend.class: Frontend.java
	javac Frontend.java

FrontendDeveloperTests.class: FrontendDeveloperTests.java Frontend.class
	javac -cp .:../junit5.jar FrontendDeveloperTests.java

runBDTests: BackendDeveloperTests.class
	java -jar ../junit5.jar -cp . -c BackendDeveloperTests

Backend.class: Backend.java
	javac -cp .:../junit5.jar Backend.java
	
BackendDeveloperTests.class: BackendDeveloperTests.java Backend.class
	javac -cp .:../junit5.jar BackendDeveloperTests.java

clean:
	rm -f *.class
