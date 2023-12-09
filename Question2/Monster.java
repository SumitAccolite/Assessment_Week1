package org.example;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Random;

public class Monster {
    private String monsterName;
    private String eyeColor ;
    private String featherColor ;
    private String magic;
    int size;
    int strength;
    public Monster(String monsterName, String eyeColor, String featherColor,String magic, int size, int strength) {
        this.monsterName = monsterName ;
        this.eyeColor = eyeColor;
        this.featherColor = featherColor;
        this.magic = magic;
        this.size = size;
        this.strength = strength;
    }
    public String getMonsterName() {
        return monsterName;
    }

    public String getEyeColor() {
        return eyeColor;
    }

    public String getFeatherColor() {
        return featherColor;
    }

    public String getMagic() {
        return magic;
    }

    public int getSize() {
        return size;
    }

    public int getStrength() {
        return strength;
    }

    @Override
    public String toString() {
        return monsterName+"={" +
                "eyeColor='" + eyeColor + '\'' +
                ", featherColor='" + featherColor + '\'' +
                ", magic='" + magic + '\'' +
                ", size=" + size +
                ", strength=" + strength +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Monster monster = (Monster) o;
        return Objects.equals(monsterName, monster.monsterName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(monsterName, eyeColor, featherColor, magic, size, strength);
    }

    public KidMonster createKidWith(Monster monster) {
        try {
            Class<?> kidMonsterClass = Class.forName("org.example.KidMonster");
            Constructor<?> constructor = kidMonsterClass.getConstructor(String.class, String.class, String.class, String.class, int.class, int.class, String.class, String.class);
            Random random = new Random();
            boolean randBool;
            Object[] args = new Object[]{
                    random.nextBoolean() ? this.monsterName : monster.getMonsterName(),
                    random.nextBoolean() ? this.eyeColor : monster.getEyeColor(),
                    random.nextBoolean() ? this.featherColor : monster.getFeatherColor(),
                    random.nextBoolean() ? this.magic : monster.getMagic(),
                    random.nextBoolean() ? this.size : monster.getSize(),
                    random.nextBoolean() ? this.strength : monster.getStrength(),
                    this.monsterName,
                    monster.getMonsterName()
            };

            //Used reflection to make an instance of the KidMonster Class
            KidMonster kidMonster = (KidMonster) constructor.newInstance(args);
            return kidMonster;
        } catch (ClassNotFoundException | NoSuchMethodException | IllegalAccessException | InvocationTargetException | InstantiationException e)
        {
            e.printStackTrace();
            return null;
        }
    }

    static void displayMonster(Map<String, List<Monster>> msMap){
        System.out.println("Monsters Created are:\n");
        for (Map.Entry<String, List<Monster>> entry : msMap.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }
}

