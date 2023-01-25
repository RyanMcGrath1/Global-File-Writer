# Raw-File-Writer

[RawFileWriter.java](https://github.com/RyanMcGrath1/Raw-File-Writer/blob/main/RawFileWriter.java) provides a flexible low level File I/O system which can be used across several applications. The core of the project is centered around the [Java System Hook](https://github.com/kristian/system-hook) which is a global non-focusable keyboard and mouse listener. The ides for the project is to provide an easy to use 'point-and-click' style program to copy and paste text from a text file into any application.

The **system-hook** dependency can be found as a `.jar` file [here](https://github.com/kristian/system-hook/releases), alternatively the `Maven` dependency can be found within the `README.md` file in the first link.

The program can be hard coded to include the path to the file or, receive a file path as its first argument for ease of use. 
