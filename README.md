# Digital14CodingTask

Project Name : digital14
Used  Tools : SpringBoot 2.7.3, JDK 11, junit jupiter, STS IDE
Main Class : DemoApplication

About:
In this project I used Builder Design Pattern to build Extendable String Writer with many Operations.
Builder Class can accept any type of StringWriters with custom handling.
You can add or remove many number Of Operations for applying to the input string.



* Some key Classes :

DemoApplication: main Class [starting point]

WriterFactory: Factory for creating customized Writers with custom operations as needed.
WriterBuilder: Builder for any type of writer with any number of operations. you can add, remove operations using it as well.

Writer<abstract> : parent class of any writer. you can add, remove operations using it as well to facilitate for user
Operation<interface> : interface for any operation that has operation type and execute function.

FileWriter: write and read from file.
StringWriter: write in console. it can read from its custom cache as well.


