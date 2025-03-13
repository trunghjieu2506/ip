# Duke project template

This is a project template for a greenfield Java project. It's named after the Java mascot _Duke_. Given below are instructions on how to use it.

## Setting up in Intellij

Prerequisites: JDK 17, update Intellij to the most recent version.

1. Open Intellij (if you are not in the welcome screen, click `File` > `Close Project` to close the existing project first)
1. Open the project into Intellij as follows:
   1. Click `Open`.
   1. Select the project directory, and click `OK`.
   1. If there are any further prompts, accept the defaults.
1. Configure the project to use **JDK 17** (not other versions) as explained in [here](https://www.jetbrains.com/help/idea/sdk.html#set-up-jdk).<br>
   In the same dialog, set the **Project language level** field to the `SDK default` option.
1. After that, locate the `src/main/java/Duke.java` file, right-click it, and choose `Run Duke.main()` (if the code editor is showing compile errors, try restarting the IDE). If the setup is correct, you should see something like the below as the output:
   ```
   Hello from
    ____        _        
   |  _ \ _   _| | _____ 
   | | | | | | | |/ / _ \
   | |_| | |_| |   <  __/
   |____/ \__,_|_|\_\___|
   ```

**Warning:** Keep the `src\main\java` folder as the root folder for Java files (i.e., don't rename those folders or move Java files to another folder outside of this folder path), as this is the default location some tools (e.g., Gradle) expect to find Java files.

# CorgiManager

CorgiManager is a simple command-line chatbot application that helps you manage a todo list. You can add tasks, mark or unmark them as done, delete tasks, and even search your list of tasks by keywords. CorgiManager stores your tasks in a local file so your list remains available the next time you run the application.

Features

Task Management

Add simple tasks (todo), tasks with deadlines (deadline), or scheduled events (event).

Mark tasks as done or unmark them if you made a mistake.

Delete tasks when they are no longer needed.

List all tasks along with their statuses.

Search

Find tasks in your list based on keywords.

Local Persistence

Your tasks are saved to a local file tasks.dat in a hidden folder named .corgimanager in your home directory. CorgiManager automatically loads your tasks upon startup.

User-Friendly CLI

The application interacts with you via text prompts on the console, providing a straightforward and intuitive command interface.

Getting Started

Prerequisites

Java 8 or higher installed on your system.

A terminal or command prompt to run the application.

Installation

Clone or download this repository to your local machine.

Compile the Java classes. From your project’s root directory, run:

javac CorgiManager/*.java CorgiManager/command/*.java CorgiManager/exception/*.java CorgiManager/parser/*.java CorgiManager/storage/*.java CorgiManager/task/*.java CorgiManager/tasklist/*.java CorgiManager/ui/*.java

(Or use your preferred IDE to compile the project.)

Run the application:

java CorgiManager.CorgiManager

Usage

When you run the program, you will see a welcome message. You can then type in any of the supported commands to manage your task list. Below is a summary of the available commands.

Core Commands

List Tasks

list

Shows all tasks in your list with their indexes and statuses.

Add a ToDo Task

todo <task_name>

Creates a simple task without any date or time.

Add a Deadline

deadline <task_name> /by <date_time>

Creates a task with a deadline.

Example: deadline Finish report /by Monday 6pm

Add an Event

event <event_name> /from <start_date_time> /to <end_date_time>

Creates an event with a start and end time/date.

Example: event Conference /from Tuesday 9am /to Tuesday 5pm

Find Tasks by Keyword

find <keyword>

Searches your tasks for those containing the specified keyword.

Mark/Unmark a Task

mark <task_index>
unmark <task_index>

Marks or unmarks the task at the given 1-based index (as shown by the list command) as done or not done.

Delete a Task

delete <task_index>

Removes the task at the given 1-based index from your list.

Exit the Application

bye

Exits CorgiManager and saves your tasks automatically.

File Storage

By default, CorgiManager saves tasks to a file named tasks.dat stored in:

<User_Home_Directory>/.corgimanager/tasks.dat

When CorgiManager starts, it automatically reads from this file (if it exists) to restore any previously saved tasks.

Project Structure

CorgiManager/
├─ command/            // Command classes (Add, Delete, Mark, Unmark, etc.)
├─ exception/          // Custom exceptions for command handling
├─ parser/             // The Parser class, which interprets user commands
├─ storage/            // Logic for reading/writing tasks from/to file
├─ task/               // Classes representing different task types
├─ tasklist/           // The TaskList class, managing the in-memory collection of tasks
├─ ui/                 // The Ui class, handling user input/output
└─ CorgiManager.java   // Main entry point that ties everything together

Contributing

Contributions are welcome! If you’d like to enhance the chatbot or fix a bug:

Fork this repository.

Create a new feature branch (git checkout -b feature/awesome-enhancement).

Commit your changes (git commit -m 'Add some awesome enhancement').

Push the branch (git push origin feature/awesome-enhancement).

Open a Pull Request.

License

This project is distributed under no specific license by default. You can add or change the license as you see fit for your own usage or distribution.

Contact

If you have questions, suggestions, or encounter any issues, please open an issue in this repository.

Enjoy managing your tasks with CorgiManager!
