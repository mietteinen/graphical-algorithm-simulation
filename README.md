# Graphical Algorithm Simulation

This program is intended to be a learning tool for anyone that is interested in exploring data structures and algorithms. It visualizes how different algorithms tackle the problems they are presented with, for now just in terms of sorting a list of numbers.

# Author

Made by Tomi Miettinen (@mietteinen), a second-year Computer Science student at Tampere University.

# How to build and run

## Prerequisites

Before building the application, ensure that you have existing Java and Maven installations. Follow the instructions below to acquire them.

<details>
<summary>Linux</summary>

1. Install the dependencies via your system's package manager:

   <p><h5> Debian/Ubuntu </h5></p>

    ```shell
    sudo apt-get install default-jre maven
    ```
   <p><h5> Red Hat </h5></p>

   ```shell
   sudo yum install java-11-openjdk maven
   ```

2. Run `java --version` and `mvn --version` to make sure the tools are working as they should.
    - You may have to restart your shell session by reopening the terminal.
</details>

<details>
<summary>Windows</summary>

1. <b>Install Java</b>
    - Download the Java installer from [Oracle](https://www.oracle.com/java/technologies/javase-downloads.html).
    - Run the installer and follow given instructions.
&nbsp;
2. <b>Install Maven</b>
    - Download the Maven binaries from [the Apache Maven website](https://maven.apache.org/download.cgi).
    - Extract the contents of the zip file to a directory of your choosing (e.g. `C:\Program Files\Apache\maven`).
    - Add the `bin` (e.g. `C:\Program Files\Apache\maven\bin`) directory to PATH.
</details>

## Build Instructions

1. Clone or download the project from [GitHub](https://github.com/mietteinen/graphical-algorithm-simulation) to your local machine. To clone the project, `cd` to a folder of your choosing and type:

    ```shell
    git clone https://github.com/mietteinen/graphical-algorithm-simulation.git
    ```

2. Navigate to the project directory.

    ```shell
    cd graphical-algorithm-simulation
    ```

3. Run the following command to build the project using Maven:

    ```shell
    mvn clean package
    ```
    This command builds the project and generates a `.jar` file in the `target/` directory.

4. The `.jar` file can now be run with:

    ```shell
    java -jar target/graphical-algorithm-simulation-1.0.jar
    ```

## Additional Notes

- If you encounter any build issues or errors, make sure that you have the correct Java version (11) installed and that your environment variables (such as `PATH`) are properly set.

- If you have any questions or need further assistance, please feel free to open an issue in the [project repository](https://github.com/mietteinen/graphical-algorithm-simulation/issues).
