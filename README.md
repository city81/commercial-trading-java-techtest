# Anagram Finder
A simple command line utility for finding anagrams in a specified file

## Software required to run this
* Java 17

## Building and Running the tests
```
./gradlew clean build
```

## Running the program
```
./gradlew bootRun --args="example2.txt" 
```
where example2.txt is the text file that we want to search for anagrams


## TODO

* Add logging
* Add more comments
* Add more tests
* Maybe add a Collector interface to the AnagramCollector so different ways of 
    parsing the list can be injected into the AnagramFinderProcessor