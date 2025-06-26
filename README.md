# D\&D Monster CLI Tool

A simple command-line tool for Dungeons & Dragons players and Dungeon Masters to explore and generate monster stats from a JSON monster database.

## Purpose

This project is designed to:

* Provide quick access to detailed D\&D monster stats in your terminal.
* Randomly generate monster encounters with full stats for gameplay inspiration.
* Create new hybrid monsters by combining traits from three existing monsters, including a custom-generated name and type.
* Simulate dice rolls to help with gameplay without needing physical dice.

It's a lightweight, easy-to-use utility that leverages JSON data and the Gson library to parse monster information.

## Features

* **Random Monster Encounter:** Prints a fully detailed, randomly selected monster.
* **Build a Monster:** Mixes three random monsters to create a new "Frankenstein" monster with combined stats, traits, actions, and a blended name and type.
* **Random Dice Roll:** Roll any number and type of dice using standard notation (e.g., `2d6`, `1d20`).

## How It Works

* Monsters are loaded from a `monsters.json` file containing monster data in JSON format.
* Uses the Gson library to deserialize JSON into Java objects.
* Combines different monster attributes in creative ways to generate new hybrid monsters.
* Outputs clean, readable text in the terminal, stripping HTML from monster descriptions and formatting with bullet points.

## Getting Started

### Prerequisites

* Java 8 or higher
* Gson library (tested with gson-2.10.1.jar)
* A properly formatted `monsters.json` file with monster data
   (Sourced from https://gist.github.com/tkfu/9819e4ac6d529e225e9fc58b358c3479) credit to Jon Oster

### Running the Program

1. Compile the program:

   ```bash
   javac -cp gson-2.10.1.jar MonsterCLI.java Monster.java
   ```

2. Run the program:

   ```bash
   java -cp .:gson-2.10.1.jar MonsterCLI
   ```

3. Follow the on-screen prompts to explore monsters, build new ones, or roll dice.

## Project Structure

* `MonsterCLI.java`: Main application logic, user interface, and utility methods.
* `Monster.java`: Data model representing a monster (must match the JSON structure).
* `monsters.json`: JSON file containing monster data.

## Future Ideas

* Add more monster customization options.
* Support saving built monsters to a file.
* Expand dice rolling to include modifiers and advantage/disadvantage.
* Integrate with a GUI or web interface for ease of use.

## License

This project is open source and free to use.

---

If you want me to customize this further or add installation instructions or example JSON structure, just ask!
