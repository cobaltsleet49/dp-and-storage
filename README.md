# ESEP: Data Processing and Storage Assignment

## Local Environment Setup

If you don't have Java installed yet, download the [Java SDK](https://www.python.org/downloads/](https://www.oracle.com/java/technologies/downloads/#jdk23-linux))
and follow the [installation instructions](https://www.java.com/en/download/help/download_options.html).

Follow these steps to run the in-memory database on your local machine:

1. **Clone the Repository**
   
   ```bash
   git clone https://github.com/cobaltsleet49/dp-and-storage.git
   ```
   
3. **Open the project in your IDE of choice and run** `Main.java`

  - The project folder name should be `dp-and-storage` and `Main.java` should be located inside the `src` folder.

  - The console will output the results of the sample test cases procured from the assignment Google Doc. The comments in the code provide the expected output, and they should match the actual output.
  
  - Feel free to write and run your own test cases.

3. **Optional: Run JUnit test cases**
  - You will need to add JUnit 5 as a dependency. Here are the instructions for setting up JUnit 5 in [IntelliJ](https://www.jetbrains.com/help/idea/junit.html#intellij)
     and [VSCode](https://code.visualstudio.com/docs/java/java-testing).
    
  - Once JUnit 5 is added, run the `JUnitTests.java` file inside the `src` folder to confirm that the sample test cases pass.
    
  - Feel free to write and run your own test cases; here is the [JUnit Documentation](https://junit.org/junit5/docs/current/user-guide/) for reference.


## Assignment Modifications Proposal
I think to make the assignment more complete, more functionality should be added like a `transfer(Person A, Person B, transferAmount)` method that uses the `put()` and `get()` methods to implement a complete transfer process.
One particular ambiguity/point of confusion was instruction 9 - when it said that the uncommitted changes should not be visible to get(), I was not sure if it meant to return `null` as a placeholder for a key undergoing
modification, or simply return the original value before begin_transaction() was called. Lastly, I suggest laying out a clear framework testing, e.g., what type of library to use, what test cases will be ran by the TAs, etc.
