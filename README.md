# Global File Writer

Global File Writer is a program written in Java that allows you to easily import the contents of a text file into any application on your computer. With its point-and-click feature, it makes the process of copy and pasting text simple and efficient.

## Features

- Reads text files
- Writes the contents of the text file into any application on the host machine
- User-friendly point-and-click interface

## Requirements

- Java SE Development Kit (JDK) 8 or later
- A Java IDE, such as Eclipse or IntelliJ IDEA
- `system-hook` Library 

## Installation

1. Clone the repository to your local machine using the following command:

```sh 
$ git clone https://github.com/RyanMcGrath1/Global-File-Writer.git
```

2. Import the project into your preferred Java IDE.

3. Run the program from your IDE.

## Usage

Upon running the program, the user can pass the file path as the first argument and it will begin. If no argument is passed the program will defer to the hard coded file path. After selecting the file, the user can middle mouse wheel click on the desired application to import the contents of the text file into. The program will automatically write the contents of the file into the selected application. 

The delimeter is set to the `\r` and will separate inputs accordingly. The interval at which contents are written can be easily modified internally. 

## Contributing

Contributions are welcome! If you have an idea for a new feature or find a bug, please open an issue or submit a pull request.

## Dependencies

This project uses the [System-Hook](https://github.com/kristian/system-hook) library. 

The `system-hook` dependency is created and maintained by [https://github.com/kristian](https://github.com/kristian). The ``system-hook`` dependency can be found as a `.jar` file [here](https://github.com/kristian/system-hook/releases), alternatively the `Maven` dependency can be found [here](https://github.com/kristian/system-hook#maven-dependency).
