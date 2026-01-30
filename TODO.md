# TODO List for Fixing Application Startup Error

- [x] Identify the issue: Incorrect Spring Boot version (4.0.2) in pom.xml, which should be 3.2.0
- [x] Fix Spring Boot version in pom.xml from 4.0.2 to 3.2.0
- [x] Fix Java version in pom.xml from 25 to 17 for compatibility
- [x] Clean and rebuild the project (run `mvn clean compile`)
- [x] Run the application and verify it starts without the HolidayInstanceMapper bean error
- [ ] Test the /api-docs endpoint if needed
