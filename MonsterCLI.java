import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Random;

public class MonsterCLI {
    public static void main(String[] args){
        try{
            Gson gson = new Gson();
            FileReader reader = new FileReader('monsters.json');
            Type listType = new TypeToken<List<Monster>>() {}.getType();
            List<Monster> monsters = gson.fromJson(reader, listType);

            //Pick random monster from list
            Random rand = new Random();
            Monster m = monsters.get(rand.nextInt(monsters.size()));

            // Print monster details
            System.out.println("🧟‍♂️ RANDOM MONSTER ENCOUNTER 🧙‍♀️");
            System.out.println("Name: " + m.name);
            System.out.println("Type: " + m.meta);
            System.out.println("Armor Class: " + m.Armor_Class);
            System.out.println("Hit Points: " + m.Hit_Points);
            System.out.println("Speed: " + m.Speed);
            System.out.println("STR: " + m.STR + " " + m.STR_mod);
            System.out.println("DEX: " + m.DEX + " " + m.DEX_mod);
            System.out.println("CON: " + m.CON + " " + m.CON_mod);
            System.out.println("INT: " + m.INT + " " + m.INT_mod);
            System.out.println("WIS: " + m.WIS + " " + m.WIS_mod);
            System.out.println("CHA: " + m.CHA + " " + m.CHA_mod);
            System.out.println("Saving Throws: " + m.Saving_Throws);
            System.out.println("Skills: " + m.Skills);
            System.out.println("Damage Immunities: " + m.Damage_Immunities);
            System.out.println("Senses: " + m.Senses);
            System.out.println("Languages: " + m.Languages);
            System.out.println("Challenge: " + m.Challenge);
            System.out.println("\nTraits:\n" + stripHtml(m.Traits));
            System.out.println("\nActions:\n" + stripHtml(m.Actions));
            if (m.Legendary_Actions != null && !m.Legendary_Actions.isEmpty()) {
                System.out.println("\nLegendary Actions:\n" + stripHtml(m.Legendary_Actions));
            }
            System.out.println("\nImage URL: " + m.img_url);
        }   
        catch (Exception e){
            e.printStackTrace();
        }
    }

    //HTML Stripper for cleaner output
    public static String stripHtml(String html) {
        return html.replaceAll("<[^>]+>", "").replaceAll("&nbsp;", " ").trim();
    }
}