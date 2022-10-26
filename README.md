# puzzleSolver
This application used to find number of solutions to a puzzle

## Prerequisite
Following tools required,
 * java 8 or greater
 * Maven 3

To run unit test
 * `mvn clean test`

Build and run the app using maven
 * `mvn package`
 * java -jar target/<package-name>
   Example :- `java -jar target/puzzlesolver-1.0-SNAPSHOT.jar`

 `Note` :- Make sure input file name called `puzzle.txt` should be on the same path.

 Input File
   * First line should contain number of puzzle pieces
   * Pieces should be separate by new line 
     Example piece format :- `SIOS`