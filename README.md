# Song play tracker tests
These tests have been done with language java and used build tool as maven

**Pre-requisite - Setup**
1. Download IntelliJ IDE
2. Download Java 11
3. Setup environment variables for the JDK

**Running Web Tests**
1. Clone the repo
2. Run the 'SongManagementTest' located at src/test/java/com/testcompany/songtracker/SongManagementTest.java
3. SongManagementTest has the following scenarios covered:
i) Recently played songs of the user points correctly
ii) If any song played repeatedly the latest one played considered in the song tracker
iii) Remove recently played songs when the store becomes full.
iv) Assert error if attempt to play a song with the same user,but song not provided
v) Assert error if attempt to get recently played songs for a non-existing user



