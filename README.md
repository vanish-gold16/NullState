# 🖥️ NullState

> Text-based RPG adventure in Cyberpunk verse built in Java  
> JSON-driven world, modular commands, dialogue system, and save/load mechanics.

---

## 📌 Overview

**NullState** is a console-based RPG game where the player explores locations, interacts with NPCs, manages inventory, and makes dialogue decisions.

The architecture focuses on:
- Clean command pattern implementation
- JSON-based data storage
- Modular DAO layer
- Dialogue trees with branching options
- Persistent save system

---

## ⚙️ Features

### 🎮 Gameplay
- Move between locations
- Examine environment
- Interact with NPCs
- Dialogue system with choices
- Attack and hack mechanics
- Inventory management
- Item usage
- Status tracking

### 💾 Persistence
- Save & load game state
- JSON-based storage system

### 🧠 Architecture
- Command Pattern for user input handling
- DAO layer for reading JSON game data
- Clear separation of:
  - `models`
  - `commands`
  - `dao`
  - `main`
  - `jsons`

---

## 🗂 Project Structure
commands/ # Player command implementations

dao/ # Data access layer (JSON loading)

jsons/ # Game data files

main/ # Core engine & UI logic

models/ # Game entities


---

## 🛠 Tech Stack

- Java
- Gson (for JSON parsing)

---

## 🚀 How to Run

### Using IntelliJ IDEA
1. Open the project.
2. Make sure Gson library is attached.
3. Run:


---

## 🎯 Available Commands

Examples of supported player commands:
jdi

vstup

exit

utok

hackni

mluv

vezmi

poloz

pouzij

inventar

status

help


# Note
Educational project, non-commercial usage only
