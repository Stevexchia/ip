# 🐷 Piggy: Your Friendly Task Manager 🐷

Welcome to **Piggy**, a chatbot designed to help you manage your tasks efficiently! Whether you need to keep track of todos, deadlines, or events, Piggy is here to make your life easier. Let's dive into how you can use Piggy to stay organized.

---

## 🚀 Quick Start

1. **Download** the latest version of Piggy from the JAR file.
2. **Run** the application by executing the JAR file.
3. **Type** commands in the command box and press `Enter` to execute them.
4. Refer to the [Features](#Features) section below for a list of commands.

---

## 🎯 Features

### 1. **Add a Todo Task**
Add a simple todo task to your list.

**Format:**  
`todo <description>`

**Example:**  
`todo Read book`

---

### 2. **Add a Deadline Task**
Add a task with a deadline.

**Format:**  
`deadline <description> /by <yyyy-MM-dd HHmm>`

**Example:**  
`deadline Return book /by 2023-12-31 1800`

---

### 3. **Add an Event Task**
Add a task that occurs during a specific time period.

**Format:**  
`event <description> /from <yyyy-MM-dd HHmm> /to <yyyy-MM-dd HHmm>`

**Example:**  
`event Team meeting /from 2023-11-01 1400 /to 2023-11-01 1600`

---

### 4. **List All Tasks**
View all tasks in your list.

**Format:**  
`list`

---

### 5. **Mark a Task as Done**
Mark a task as completed.

**Format:**  
`mark <task number>`

**Example:**  
`mark 1`

---

### 6. **Unmark a Task**
Mark a task as not completed.

**Format:**  
`unmark <task number>`

**Example:**  
`unmark 1`

---

### 7. **Delete a Task**
Remove a task from your list.

**Format:**  
`delete <task number>`

**Example:**  
`delete 1`

---

### 8. **Find Tasks by Keyword**
Search for tasks that match a keyword.

**Format:**  
`find <keyword>`

**Example:**  
`find book`

---

### 9. **Filter Tasks by Date**
Search for tasks that occur on a specific date.

**Format:**  
`filter <yyyy-MM-dd>`

**Example:**  
`filter 2023-12-31`

---

### 10. **Exit the Application**
Exit the Piggy chatbot.

**Format:**  
`bye`

---

## 📋 Command Summary

| Command         | Format                                                              | Example                                                        |
|-----------------|---------------------------------------------------------------------|----------------------------------------------------------------|
| Add Todo        | `todo <description>`                                                | `todo Read book`                                               |
| Add Deadline    | `deadline <description> /by <yyyy-MM-dd HHmm>`                      | `deadline Return book /by 2023-12-31 1800`                     |
| Add Event       | `event <description> /from <yyyy-MM-dd HHmm> /to <yyyy-MM-dd HHmm>` | `event Team meeting /from 2023-11-01 1400 /to 2023-11-01 1600` |
| List Tasks      | `list`                                                              | `list`                                                         |
| Mark Task       | `mark <task number>`                                                | `mark 1`                                                       |
| Unmark Task     | `unmark <task number>`                                              | `unmark 1`                                                     |
| Delete Task     | `delete <task number>`                                              | `delete 1`                                                     |
| Find by Keyword | `find <keyword>`                                                    | `find book`                                                    |
| Find by Date    | `find date <yyyy-MM-dd>`                                            | `find date 2023-12-31`                                         |
| Exit            | `bye`                                                               | `bye`                                                          |

---

## ⚠️ Alerts and Tips

### **Alerts**
> [!WARNING]
> **Invalid Command**: If you enter an invalid command, Piggy will display an error message. For example:
> ```
> Oink, oink! Unknown command! Please try again, or I'll start snorting!
> ```

> [!WARNING]
> **Task Not Found**: If you try to mark, unmark, or delete a task that doesn't exist, Piggy will notify you:
> ```
> Oops, that task number doesn’t exist in my mud!
> ```

### **Tips**
> [!TIP]
> Use the `list` command frequently to keep track of your tasks.

> [!TIP]
> Piggy automatically saves your tasks, so you don't need to worry about losing them!

---

## ❓ FAQ

### **Q: Can I edit a task after adding it?**
A: Currently, Piggy does not support editing tasks. You can delete the task and add a new one with the correct details.

### **Q: Where are my tasks stored?**
A: Tasks are stored in a file named `tasks.txt` in the same directory as the application.

### **Q: What happens if I close the application without typing `bye`?**
A: Piggy will still save your tasks, but it's always a good idea to use the `bye` command to ensure everything is saved properly.

---

## 🛠️ Troubleshooting

### **Issue: Tasks are not saving**
- Ensure you have write permissions in the directory where Piggy is running.
- Check if the `tasks.txt` file exists. If not, Piggy will create it automatically.

### **Issue: Application crashes**
- Ensure you are using the correct command formats.
- If the issue persists, delete the `tasks.txt` file and restart the application.

---

## 🙏 Acknowledgements

- This project is inspired by the [Duke project](https://github.com/nus-cs2103-AY2324S1/ip).
- Built with ❤️ by **Steve**.

---
