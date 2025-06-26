import com.google.gson.annotations.SerializedName;

public class Monster {
    public String name;
    public String meta;

    @SerializedName("Armor Class")
    public String armorClass;

    @SerializedName("Hit Points")
    public String hitPoints;

    public String Speed;

    public String STR;
    public String STR_mod;

    public String DEX;
    public String DEX_mod;

    public String CON;
    public String CON_mod;

    public String INT;
    public String INT_mod;

    public String WIS;
    public String WIS_mod;

    public String CHA;
    public String CHA_mod;

    @SerializedName("Saving Throws")
    public String savingThrows;

    public String Skills;

    @SerializedName("Damage Immunities")
    public String damageImmunities;

    public String Senses;
    public String Languages;
    public String Challenge;
    public String Traits;
    public String Actions;

    public String Legendary_Actions;
    public String img_url;
}
