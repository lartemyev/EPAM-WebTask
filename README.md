# EPAM Testing task for Automation Engineer

## done by Leonid Artemiev

### How to execute:
* mvn test via maven (it's configured to run tests from testng.xml)
* run via testng.xml

### How to run in parallel:
* pom.xml has "<parallel>both</parallel>" that mean that it supports parallelization by methods and classes by default
* just run via testng.xml or maven (as described above)

### Supported items:
This is Test Automation task done by Leonid Artemiev with usage of: 
* TestNG
* PageObject pattern with example usage of Page Factory
* Maven builder
* TestNG Parametrization
* Logging
* Parallelization
* Reporting

### Report generation instructions
* Reporting was implemented using com.relevantcodes.extentreports package
* Report file is automatically generated by Log.print("...") call to Reports\Report.html file