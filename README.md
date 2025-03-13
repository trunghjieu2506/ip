# CorgiManager

CorgiManager is a simple command-line chatbot application that helps you manage a todo list. You can add tasks, mark or unmark them as done, delete tasks, and  search your list of tasks by keywords. CorgiManager stores your tasks in a local file so your list remains available the next time you run the application.

---

## Features

1. **Task Management**
   - **Add** simple tasks (`todo`), tasks with deadlines (`deadline`), or scheduled events (`event`).
   - **Mark** tasks as done or **unmark** them if you made a mistake.
   - **Delete** tasks when they are no longer needed.
   - **List** all tasks along with their statuses.

2. **Search**
   - **Find** tasks in your list based on keywords.

3. **Local Storage**
   - Your tasks are saved to a local file `tasks.dat` in a hidden folder named `.corgimanager` in your home directory. CorgiManager automatically loads your tasks upon startup.

**Example command prompts**
- todo <task_name>: add a task to the list.
- list: show list of tasks.
- mark <task_number>: mark a task as done.
- unmark <task_number>: unmark a task. 
- deadline <task_name> /by <date>: create a task with a deadline.
- event <event_name> /from <start_date_time> /to <end_date_time>: create an event.
---

## Getting Started

### Prerequisites

- Java 17 
- A terminal or command prompt to run the application.

### Installation

1. **Download** CorgiManager.jar file from v.02 release
2. Open a terminal where CorgiManager.jar is located, run java -jar CorgiManager.jar

## License
This project is distributed under no specific license by default. You can add or change the license as you see fit for your own usage or distribution.
