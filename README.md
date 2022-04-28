# Twitter
Explore Twitter APIs

Run application

Choose 1 to view Timeline
Choose 2 to Tweet

# To use jacoco for code coverage, 

1. add following plugin in build.gradle

    apply plugin: "jacoco"

2. run below commands 

   ./gradlew clean
   ./gradlew build

3. code coverage report will be seen on 

   file://<user-directory>build/reports/tests/test/index.html


# Configuration files
1. Create application.yml file in "main/resources/" directory
2. Add profiles as local in application.yml
3. Copy configuration values from properties file to yml file
4. To set related properties from properties file, create a YamlConfig class
5. In the properties file, set "spring.profiles.active" to local
6. Implement CommandLineRunner in Main class to print values on commandLine once application runs
7. Autowire these config files to wherever required