package org.example;

import java.util.List;
import java.util.Map;

class KidMonster extends Monster {
    private String monsterParent1;
    private String monsterParent2;
    public KidMonster(String monsterName, String eyeColor, String featherColor, String magic, int size, int strength, String monsterParent1,String monsterParent2) {
        super(monsterName, eyeColor, featherColor, magic, size, strength);
        this.monsterParent1 = monsterParent1 ;
        this.monsterParent2 = monsterParent2 ;
    }

    @Override
    public String toString() {
        return "{" +
                "Parent1=" + monsterParent1 +
                ", Parent2=" + monsterParent2 +
                "eyeColor='" + getEyeColor() + '\'' +
                ", featherColor='" + getFeatherColor() + '\'' +
                ", magic='" + getMagic() + '\'' +
                ", size=" +getSize() +
                ", strength=" + getStrength() +
                "}\n";
    }

   static void displayKid(List<KidMonster> kdList){
       System.out.println("\nThe kids Breeded are:");
       System.out.println(kdList);
    }
}
