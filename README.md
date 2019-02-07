# PrintCalc
A simple but extensible print job cost calculator (challenge)<br>
By Hernan Rojas.<br>
Last edited: April 24th, 2017.

# Challenge statement
To reduce printing waste, a school will charge for printing as follows:<br>

Paper size A4, job type single sided:<br>
 * 15 cents per black and white page
 * 25 cents per colour page

Paper size A4, job type double sided:<br>
 * 10 cents per black and white page
 * 20 cents per colour page

Write a program in Java that helps the system administrator to calculate the print costs. It takes a list of A4 print jobs and calculates the cost of each job, given the total number of pages, number of colour pages and whether printing is double sided.<br>
Support for other paper sizes will be added in the future.<br>

The application should:<br>
 * Read print jobs from a file (see attached file for an example.)
 * Output the job details and job cost for each job to the console
 * Output the total cost of all jobs to the console
 
 # Challenge solution
My aim was to deliver a simple solution that focused on extensibility and demonstrated a good knowledge of Java 8 and Test Driven Development. I provided a set of tests and an executable class that receives the input csv file through command line arguments. The solution is built and the tests are run using maven.

## Design Decisions
 * I made the solution so new paper sizes could be added easily. For this, I defined the `PrintableJob` interface, that ensures all implementations (one for each paper size) provide the methods `getJobCost()` and `getDetails()`, to satisfy the requirements in the challenge statement.
 * All components where developed following TDD. Each class has its corresponding test class that illustrates that.
 * An implementation for paper size A4 is provided. The `A4PrintJob` class is able to calculate its own cost and display its own details. The class `A4PrintJobCostConf` provides the prices for all variations of an A4 print job (single or double sided, colour or monochrome). New prices can be used instead of the default ones, something that could be useful in the future.
 * The class `PrintJobParser` parses String arrays into `PrintableJobs`. It recognizes the print job type from the headers and constructs `PrintableJobs` accordingly (`A4PrintJob`, for instance).
 * The class `CsvFileLoader` reads a CSV file from the /resources folder and turns each line to a String array, that will be passed later to `PrintJobParser`.
 * `PrintCalc` is the application's entry point. It gets the CSV file from the command line arguments, uses `CsvFileLoader` and `PrintJobParser` to get a List of Printable Jobs. Then, it is capable of displaying information for each (including cost) and calculating the total cost of all print jobs.
 * To add support for a new paper size, a developer should create a package under `com.p4p3rcut.printcalc.papersize` and add to it a class than implements `PrintableJob`. Finally, the developer should modify `PrintJobParser` to 1) add to the `paperSizeHeaders` map the headers that uniquely identify the new print job and 2) return instances of the new print job implementation in the `parse` method. That's all :).

## File Listing
```
/PrintCalc
│ .gitignore                                    Git ignore file, avoids tracking of the /target folder
│ pom.xml                                       Maven POM file, for dependency handling and launching
│ README.md                                     This readme file
│
└─src
  ├───main
  │ ├─java
  │ │ └─com
  │ │   └─p4p3rcut
  │ │     └─printcalc
  │ │       │ PrintCalc.java                     Main application entry point
  │ │       │
  │ │       ├─papersize
  │ │       │ │ PrintableJob.java                Interface to all printable jobs implementations (one for each paper size) 
  │ │       │ │ PrintJobParser.java              Detects paper size from the headers in the CSV file and parses PrintableJobs
  │ │       │ │
  │ │       │ └─a4
  │ │       │     A4PrintJob.java                A4 paper size printable job implementation
  │ │       │     A4PrintJobCostConf.java        Cost configuration for A4 printable jobs
  │ │       │
  │ │       └─util
  │ │           CsvFileLoader.java               Reads a CSV file and returns a List of String[]
  │ │
  │ └─resources
  │     sample.csv                               Sample input file
  │
  └─test
    ├─java
    │ └─com
    │   └─p4p3rcut
    │     └─printcalc
    │       │ PrintCalcTest.java                 Tests for the PrintCalc class
    │       │
    │       ├─papersize
    │       │ │ PrintJobParserTest.java          Tests for the PrintJobParser class
    │       │ │
    │       │ └─a4
    │       │     A4PrintJobCostConfTest.java    Tests for the A4PrintJobCostConf class
    │       │     A4PrintJobParseMethodTest.java Tests for the A4PrintJob.parse method
    │       │     A4PrintJobTest.java            Tests for the A4PrintJob class
    │       │
    │       └─util
    │           CsvFileLoaderTest.java           Tests for the CSVFileLoader class
    │
    └─resources
        empty.csv                                Sample input file, empty
        newsize.csv                              Sample input file, emulating a new paper size to be supported
        sample.csv                               Sample input file
```


## How to build and run
Apache Maven 3.x is needed to build and run the tests. To achieve this, launch the command line and run: 
```
> mvn clean install
```

After this, launch the application by typing:
```
> mvn exec:java -Dexec.args="sample.csv" -quiet
```

Thanks for reading this file.
