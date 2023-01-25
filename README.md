# Raw-File-Writer

[RawFileWriter.java](https://github.com/RyanMcGrath1/Raw-File-Writer/blob/main/RawFileWriter.java) provides a flexible low level File I/O system which can be used across several applications. The core of the project is centered around the [Java System Hook](https://github.com/kristian/system-hook) repository which is a global non-focusable keyboard and mouse listener. The `system-hook` dependency is created and maintained by [https://github.com/kristian](https://github.com/kristian). The idea for the project is to provide an easy to use 'point-and-click' style program to copy and paste text from a text file into any application.

The ``system-hook`` dependency can be found as a `.jar` file [here](https://github.com/kristian/system-hook/releases), alternatively the `Maven` dependency can be found [here](https://github.com/kristian/system-hook#maven-dependency). 

The program can be hard coded to include the path to the file or, receive a file path as its first argument for ease of use. To use the program click the middle mouse button wherever you would like the input stream to begin. Clicking the middle mouse button a second time will terminate the program. The program will work across any application that takes input from the keyboard. This program has been exclusively tested on Windows OS so support for Unix/Linux distributions is not guaranteed. 

