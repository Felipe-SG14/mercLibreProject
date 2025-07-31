# mercLibreProject

# 🧪 Running Tests

This project uses **Apache Maven** as the build and dependency management tool.
Below are the instructions to run the tests defined in the project.

---

## ✅ Prerequisites

Before running tests, ensure the following tools are installed:

- **Git**
- **Java Development Kit (JDK)**
- **Apache Maven**
- **Google Chrome** or **Microsoft Edge**

You can verify installation by running:

```bash
java --version
mvn --version
git --version
```

## ✅ How to run the project

After making sure that the necessary tools are installed, 
the first thing to do is clone the project in some working directory.

```bash
git clone https://github.com/Felipe-SG14/mercLibreProject.git
```
After cloning the repository, you must position yourself in the folder where the **pom.xml** file is located, 
which defines part of the project configuration.

```bash
cd mercLibreProject
```

You can check if you are in the correct folder using the following command:

```bash
ls
```
If the pom.xml file is displayed then you are in the correct working directory.

To run the project, use the following command:

```bash
mvn test
```
By default, the project is configured to run with Chrome, however, 
the project can take the "driver" as an environment variable,
the available options are: Chrome and Edge

Examples:

```bash
mvn test -Ddriver=Chrome
```
```bash
mvn test -Ddriver=Edge
```

Keep in mind that if you are using Windows CMD, 
the command can be the following (in case you want to pass the environment variable):

```bash
mvn test "-Ddriver=Edge"
```

📝 **Note**: If the environment variable is not passed, the test case will be executed in Chrome

Finally, the project generates an HTML report, which is located at the following address:
**src/testOutput**

This report is named: **ExtentReport.html**

If you want to open the report with the command line, you can use the following command:

```bash
start src/testOutput/ExtentReport.html
```

This command will open the report in your default browser.