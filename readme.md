Readme
====
### Requirement

__Description__

You're given the task of writing a simple console version of a drawing program. 
At this time, the functionality of the program is quite limited but this might change in the future. 
In a nutshell, the program should work as follows:

 1. Create a new canvas

 2. Start drawing on the canvas by issuing various commands

 3. Quit

```
Command			Description

C w h           Should create a new canvas of width w and height h.

L x1 y1 x2 y2   Should create a new line from (x1,y1) to (x2,y2). Currently only horizontal or vertical lines are supported. Horizontal and vertical lines will be drawn using the 'x' character.

R x1 y1 x2 y2   Should create a new rectangle, whose upper left corner is (x1,y1) and lower right corner is (x2,y2). Horizontal and vertical lines will be drawn using the 'x' character.

B x y c         Should fill the entire area connected to (x,y) with "colour" c. The behavior of this is the same as that of the "bucket fill" tool in paint programs.
Q               Should quit the program.
```

__Sample I/O__

Below is a sample run of the program. User input is prefixed with enter command:

enter command: C 20 4

```
----------------------
|                    |
|                    |
|                    |
|                    |
----------------------
```

enter command: L 1 2 6 2

```
----------------------
|                    |
|xxxxxx              |
|                    |
|                    |
----------------------
```

enter command: L 6 3 6 4

```
----------------------
|                    |
|xxxxxx              |
|     x              |
|     x              |
----------------------
```

enter command: R 14 1 18 3

```
----------------------
|             xxxxx  |
|xxxxxx       x   x  |
|     x       xxxxx  |
|     x              |
----------------------
```

enter command: B 10 3 o

```
----------------------
|oooooooooooooxxxxxoo|
|xxxxxxooooooox   xoo|
|     xoooooooxxxxxoo|
|     xoooooooooooooo|
----------------------
```

enter command: Q

### Usage

#### Approach I

1. Turn on terminal

2. Build the project

	Note: while creating the package, the test cases will be executed. If you want to skip the test, please add `-DskipTests=true` at the end of command

	``` 
	mvn clean package
	```

3. Execute the program

	```
	java -cp target/drawing-1.0-SNAPSHOT.jar Main
	```

4. Enter commands

	e.g. enter the command to create a canvas of width = 1 and height = 1.

	```
	C 1 1
	```

#### Approach II

1. Open the project with IDE

2. Build the project and run application


### Assumption

1. Canvas should be large enough to draw. That is, the value of width and height should be greater than 0.

2. When the create new canvas command is entered, the previously created canvas is discarded.

3. Drawing cannot be done on the line or border of the canvas, i.e. the value of x and y should be larger than 0 and smaller than or equal to user defined width and height.

### System behavior / limitation

1. Canvas size should not exceed `Integer.MAX_VALUE`, 2147483647.

2. When the invalid command is given, the error message is shown and the operation is aborted and show the current canvas if it is already created.

3. When the number of command arguments is larger than required number, the extra arguments are ignored.