# SageOS
Java program that simulates lifecycles of processes in an OS

# Running the Program
SageOS is written in Java. To open the program, simply import the project into Eclipse or another Java IDE, open the Runner class, and execute it

! [application view image](https://ibb.co/K0yQ80s)

Upon executing the Runner class, a new window like this should display on your screen. If not, see troubleshooting tips
Instructions:

1. Select a program by clicking one of the program buttons (the button shoud turn blue
when slelected)
2. Select the text box which says “Enter number of processes to run...” and enter the
number of processes you would like to create from the selected program
3. Select a process scheduling algorithm
4. Press the “Execute” button
5. That’s it! The processes should then show up on the right side of the screen

! [image of application during execution](https://ibb.co/NT6hHFW)

After following the above instruction, the table should populate with processes and the CPU class will execute each process and update as process instructions are executed. The application should look like this during execution.

After all processes have terminated, feel free to create new processes by following the same steps mentioned above.

Note: Please let all processes enter the “terminated” state before trying to create more new processes. 
I have not implemented a feature to end current processes if the user starts more processes while others are executing, so the program will not act as expected

# Analytics Window

! [analytic window image](https://ibb.co/HP3vMP6)

In addition to the main window, a smaller window will appear when the program is run. This window simply displays live information about the CPU and memory as processes execute. The window should look like this

