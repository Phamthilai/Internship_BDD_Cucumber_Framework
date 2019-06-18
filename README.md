# Java-BDD-TestNG

### Objective
This framework is built base on Selenium with Cucumber BDD framework
Follow the document in Documents folder for more details about framework

### Prerequisites

- Java 8 installation
- Java for cucumber plugin
- Maven installation
- IntelliJ IDEA


### How to develop test scripts"

1. Create feature file   (locate at  "/test/resources")
2. Create java step definition class bases on feature file    ( locate at "/test/java/stepDefs")
3. Create java page class  (locate at "/test/java/pages")

### How to execute test scripts using Maven"

Option 1: via IDEA

     Create Run confiuration build with following required parameters 
     
        - Non- parallel
        
              test -Dtest=TestRunner -Dbrowser=chrome -f pom.xml
	 
        - Parallel
        
              test -Dtest=TestRunner_Parallel -Dbrowser=chrome -f pom.xml

Option 2: Via CLI

    - Non- parallel
     
       mvn test -Dtest=TestRunner -Dbrowser=chrome
	 
    - Parallel
    
       mvn test -Dtest=TestRunner_Parallel -Dbrowser=chrome	 

Note: 
    If you want to change configuration inside cummber options, please use parameter "Dcucumber.option"
    
    Example: mvn test -test=TestRunner -Dbrowser=chrome  -Dcucumber.options="-tags @tagname"
    
  
