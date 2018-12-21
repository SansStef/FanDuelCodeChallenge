# FanDuel Coding Challenge
-Stefano Sansone

# Build/Run:
I completed this challenge with a Java 8 Spring application.
The code can be easily imported into an Eclipse or other IDE
Gradle is used to manage dependencies

FanDuelCodingChallengeApplication.java is the main class for the API Creation part (run as a spring boot app)

fanduel.sql includes the sql commands for both parts of the Data Analysis task

DepthChartsMain.java is the main class for the Depth Charts part


# Notes:

1.) API Creation
To implement the same calls for other sports we'd need to do 3 main things.
First, add the sport to the Sport.java enum as this enum defines our supported sports.
Second, create associated .json data files and put them into src/main/resources/[SPORT] folder.
Third, in the repository classes we need to loop over the Sport enum values to load in the .json data files.

Also, if the new .json data files would have different structure then we would need to abstract the models
in a way to support the different structures.

The controllers handle determing the sport to be used based on the url used.

3.) Depth Charts:
To add support for more sports, just need to add the sport to the Sport.java enum with the supported positions
of that sport
