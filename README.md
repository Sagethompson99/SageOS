# SageOS
Java program that simulates lifecycles of processes in an OS

# Running the Program
SageOS is written in Java. To open the program, simply import the project into Eclipse or another Java IDE, open the Runner class, and execute it

Upon executing the Runner class, a new window like this should display on your screen. If not, see troubleshooting tips
Instructions:
1. Select a program by clicking one of the program buttons (the button shoud turn blue
when slelected)
2. Select the text box which says “Enter number of processes to run...” and enter the
number of processes you would like to create from the selected program
3. Press the “Go!” button
4. That’s it! The processes should then show up on the right side of the screen

After following the above instruction, the table should populate with processes and the CPU class will execute each process and update as process instructions are executed. During execution.

After all processes have terminated, feel free to create new processes by following the same steps mentioned above.

Note: Please let all processes enter the “terminated” state before trying to create more new processes. 
I have not implemented a feature to end current processes if the user starts more processes while others are executing, so the program will not act as expected
