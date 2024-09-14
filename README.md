# Mini Java Compiler

- ##### Author:

  Hugo Perreault Gravel

- ##### Description:
This project is a mini compiler written in Java that reads and interprets files containing code in a simple, custom format. It converts the code into executable Java code and demonstrates compiler design principles. The project uses the Visitor Design Pattern to handle tasks such as code execution and Java class creation. Test files can be placed in the testFiles/ directory, allowing users to add and test new files with the compiler.

## Prerequisites

- Java
- Maven

## Usage

Compile:

```
make
```

Start the application:
(the compiler will run on the files located in the testFiles/ directory)

```
make run
```

Clean build

```
make clean
```
