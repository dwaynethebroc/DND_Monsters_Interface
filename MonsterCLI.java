/*
TO RUN in Terminal:
javac -cp gson-2.10.1.jar MonsterCLI.java Monster.java
java -cp .:gson-2.10.1.jar MonsterCLI
*/

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class MonsterCLI {
    // Entry point of the program
    public static void main(String[] args){
        try {
            // Initialize Gson object for JSON parsing
            Gson gson = new Gson();

            // Read the JSON file containing monster data
            FileReader reader = new FileReader("monsters.json");

            // Define the type of object expected from JSON parsing (List of Monster objects)
            Type listType = new TypeToken<List<Monster>>() {}.getType();

            // Parse JSON to List<Monster>
            List<Monster> monsters = gson.fromJson(reader, listType);

            // Create a Scanner object for user input
            Scanner scanner = new Scanner(System.in);

            // Control variable for the main program loop
            boolean running = true;

            // Main interactive loop to show menu and handle user choices
            while (running) {
                System.out.println("\n=== D&D Monster Tool ===");
                System.out.println("1) Random Monster Encounter");
                System.out.println("2) Build a Monster");
                System.out.println("3) Random Dice Roll");
                System.out.println("0) Exit");
                System.out.print("Choose an option: ");

                // Read user input from console
                String input = scanner.nextLine();

                // Switch statement to handle different menu options
                switch (input) {
                    case "1":
                        // Display a randomly selected monster
                        randomMonsterEncounter(monsters);
                        break;
                    case "2":
                        // Build a custom monster by combining parts from three monsters
                        buildMonster(monsters);
                        break;
                    case "3":
                        // Simulate rolling dice based on user input
                        randomDiceRoll(scanner);
                        break;
                    case "0":
                        // Exit the program
                        running = false;
                        System.out.println("Goodbye!");
                        break;
                    default:
                        // Handle invalid input
                        System.out.println("Invalid option, try again.");
                }
            }
            // Close scanner resource when done
            scanner.close();
        } catch (Exception e) {
            // Print stack trace for debugging if an exception occurs
            e.printStackTrace();
        }
    }

    // Method to display a random monster's full stats and details
    public static void randomMonsterEncounter(List<Monster> monsters){
        try{
            // Create a random object for selection
            Random rand = new Random();

            // Select a random monster from the list
            Monster m = monsters.get(rand.nextInt(monsters.size()));

            // Print monster basic information
            System.out.println("🧟‍♂️ RANDOM MONSTER ENCOUNTER 🧙‍♀️");
            System.out.println("\nName: " + m.name + "\n");
            System.out.println("Type: " + m.meta);
            System.out.println("Armor Class: " + m.armorClass);
            System.out.println("Hit Points: " + m.hitPoints);
            System.out.println("Speed: " + m.Speed + "\n");

            // Print monster ability scores with modifiers
            System.out.println("STR: " + m.STR + " " + m.STR_mod);
            System.out.println("DEX: " + m.DEX + " " + m.DEX_mod);
            System.out.println("CON: " + m.CON + " " + m.CON_mod);
            System.out.println("INT: " + m.INT + " " + m.INT_mod);
            System.out.println("WIS: " + m.WIS + " " + m.WIS_mod);
            System.out.println("CHA: " + m.CHA + " " + m.CHA_mod + "\n");

            // Print other monster features
            System.out.println("Saving Throws: " + m.savingThrows);
            System.out.println("Damage Immunities: " + m.damageImmunities);
            System.out.println("Skills: " + m.Skills);
            System.out.println("Senses: " + m.Senses);
            System.out.println("Languages: " + m.Languages);
            System.out.println("Challenge: " + m.Challenge);

            // Traits and Actions may contain HTML, so strip it and format with bullets
            System.out.println("\nTraits:\n" + stripHtml(m.Traits));
            System.out.println("\nActions:\n" + stripHtml(m.Actions));

            // If legendary actions exist, print them too
            if (m.Legendary_Actions != null && !m.Legendary_Actions.isEmpty()) {
                System.out.println("\nLegendary Actions:\n" + stripHtml(m.Legendary_Actions));
            }
            System.out.println("");
        }   
        catch (Exception e){
            e.printStackTrace();
        }
    }

    // Method to create a new "built" monster by mixing parts from three random monsters
    public static void buildMonster(List<Monster> monsters) {
        Random rand = new Random();

        // Select three random monsters independently
        Monster m1 = monsters.get(rand.nextInt(monsters.size()));
        Monster m2 = monsters.get(rand.nextInt(monsters.size()));
        Monster m3 = monsters.get(rand.nextInt(monsters.size()));

        // Combine parts of their names to form a new monster name
        String frankenName = combineNames(m1, m2, m3);

        // Combine and customize the meta/type description
        String combinedMeta = combineMetaCustom(m1, m2, m3);

        // Print the newly built monster info, mixing attributes from the three monsters
        System.out.println("\n🧩 BUILT MONSTER 🧩");
        System.out.println("Name: " + frankenName + "\n");
        System.out.println("Type: " + combinedMeta);
        System.out.println("Armor Class: " + m1.armorClass);
        System.out.println("Hit Points: " + m1.hitPoints);
        System.out.println("Speed: " + m1.Speed + "\n");

        System.out.println("STR: " + m2.STR + " " + m2.STR_mod);
        System.out.println("DEX: " + m2.DEX + " " + m2.DEX_mod);
        System.out.println("CON: " + m2.CON + " " + m2.CON_mod);
        System.out.println("INT: " + m2.INT + " " + m2.INT_mod);
        System.out.println("WIS: " + m2.WIS + " " + m2.WIS_mod);
        System.out.println("CHA: " + m2.CHA + " " + m2.CHA_mod + "\n");

        System.out.println("Saving Throws: " + m3.savingThrows);
        System.out.println("Skills: " + m3.Skills);
        System.out.println("Damage Immunities: " + m3.damageImmunities);
        System.out.println("Senses: " + m3.Senses);
        System.out.println("Languages: " + m3.Languages);
        System.out.println("Challenge: " + m3.Challenge);

        System.out.println("\nTraits:\n" + stripHtml(m3.Traits));
        System.out.println("\nActions:\n" + stripHtml(m3.Actions));

        // Print legendary actions if they exist
        if (m3.Legendary_Actions != null && !m3.Legendary_Actions.isEmpty()) {
            System.out.println("\nLegendary Actions:\n" + stripHtml(m3.Legendary_Actions));
        }
    }

    // Method to simulate rolling dice, e.g. "2d8"
    public static void randomDiceRoll(Scanner scanner) {
        System.out.print("Enter dice roll (e.g. 1d6, 2d10): ");
        String roll = scanner.nextLine().trim().toLowerCase();

        // Validate input format using regex
        if (!roll.matches("\\d+d\\d+")) {
            System.out.println("Invalid format. Use NdM (e.g. 2d8)");
            return;
        }

        // Parse number of dice (n) and sides per die (m)
        String[] parts = roll.split("d");
        int n = Integer.parseInt(parts[0]);
        int m = Integer.parseInt(parts[1]);

        Random rand = new Random();
        int total = 0;

        // Roll the dice n times and accumulate total
        System.out.print("Rolling: ");
        for (int i = 0; i < n; i++) {
            int rollValue = rand.nextInt(m) + 1; // random number between 1 and m
            total += rollValue;
            System.out.print(rollValue + (i < n - 1 ? " + " : ""));
        }
        System.out.println("\nTotal: " + total);
    }

    // Helper method to remove HTML tags and format text with bullet points
    public static String stripHtml(String html) {
        if (html == null) return "";

        // Step 1: Replace some HTML tags with newlines or remove them
        String cleaned = html
            .replaceAll("(?i)<br\\s*/?>", "\n")          // Replace <br> with newline
            .replaceAll("(?i)</p>", "\n")                // End paragraph with newline
            .replaceAll("(?i)<p[^>]*>", "")              // Remove opening <p> tags
            .replaceAll("(?i)<em[^>]*>", "")             // Remove <em> tags
            .replaceAll("(?i)</em>", "")                 
            .replaceAll("(?i)<strong[^>]*>", "")         // Remove <strong> tags
            .replaceAll("(?i)</strong>", "")             
            .replaceAll("&nbsp;", " ")                   // Convert &nbsp; to space
            .replaceAll("<[^>]+>", "")                   // Remove any remaining HTML tags
            .trim();

        // Step 2: Add bullet points before each non-empty line
        String[] lines = cleaned.split("\n");
        StringBuilder formatted = new StringBuilder();
        for (String line : lines) {
            if (!line.trim().isEmpty()) {
                formatted.append("  • ").append(line.trim()).append("\n");
            }
        }

        return formatted.toString().trim();
    }

    // Combine the names of three monsters into one new name by slicing thirds of each
    public static String combineNames(Monster m1, Monster m2, Monster m3) {
        // Get names, or empty string if null
        String n1 = m1.name != null ? m1.name : "";
        String n2 = m2.name != null ? m2.name : "";
        String n3 = m3.name != null ? m3.name : "";

        // Slice first third of first name, middle third of second, last third of third
        String slice1 = safeSlice(n1, 0, n1.length() / 3);
        String slice2 = safeSlice(n2, n2.length() / 3, 2 * n2.length() / 3);
        String slice3 = safeSlice(n3, 2 * n3.length() / 3, n3.length());

        // Concatenate slices to form new name
        return slice1 + slice2 + slice3;
    }

    // Helper method to safely get a substring without throwing errors
    private static String safeSlice(String str, int start, int end) {
        if (str == null || str.isEmpty()) return "";
        if (start > str.length()) return "";
        if (end > str.length()) end = str.length();
        if (start < 0) start = 0;
        if (start >= end) return "";
        return str.substring(start, end);
    }

    // Helper method to remove the first word from a string
    private static String removeFirstWord(String str) {
        if (str == null || str.isEmpty()) return "";
        String[] words = str.trim().split("\\s+", 2);
        return words.length > 1 ? words[1] : "";
    }

    /*
    Combine meta information ("Size Type, Alignment") from three monsters into a new string:
    - Take Size from monster 3 (first word of left part)
    - Take Alignment from monster 2 (right part after comma)
    - Take Type by removing size word from left parts of all three, then combine them
    */
    public static String combineMetaCustom(Monster m1, Monster m2, Monster m3) {
        Random rand = new Random();

        // Split each monster's meta into left (size + type) and right (alignment) parts by comma
        String[] parts1 = m1.meta != null ? m1.meta.split(",") : new String[] {"", ""};
        String[] parts2 = m2.meta != null ? m2.meta.split(",") : new String[] {"", ""};
        String[] parts3 = m3.meta != null ? m3.meta.split(",") : new String[] {"", ""};

        // Extract size from monster 3's left part (assumed first word)
        String size3 = parts3.length > 0 ? parts3[0].trim().split(" ")[0] : "";

        // Extract alignment from monster 2's right part (after comma)
        String alignment2 = parts2.length > 1 ? parts2[1].trim() : "";

        // Extract full type strings (size + type) from all three monsters
        String type1 = parts1.length > 0 ? parts1[0].trim() : "";
        String type2 = parts2.length > 0 ? parts2[0].trim() : "";
        String type3 = parts3.length > 0 ? parts3[0].trim() : "";

        // Remove size (first word) from each to isolate just the type(s)
        type1 = removeFirstWord(type1);
        type2 = removeFirstWord(type2);
        type3 = removeFirstWord(type3);

        // Combine the three types into a single string with spaces
        String combinedType = (type1 + " " + type2 + " " + type3).trim().replaceAll("\\s+", " ");

        // Construct final combined meta string: size + combined types + alignment
        String combinedMeta = size3 + " " + combinedType + ", " + alignment2;
        combinedMeta = combinedMeta.trim().replaceAll("\\s+", " ");

        return combinedMeta;
    }
}
