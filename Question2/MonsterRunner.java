package org.example;
import java.util.*;


public class MonsterRunner {
    public static void main(String[] args) throws ClassNotFoundException{
        Set<Monster> msSet = new HashSet<>();
        List<KidMonster> kdList = new ArrayList<>();
        Scanner sc = new Scanner(System.in);//scanner class to take input and passed as function
        System.out.println("Number of Monsters to be Added: ");
        int n = sc.nextInt();
        //creates n monster as per user needs
        for (int i = 0 ; i < n ; i++){
            Monster monster = createMonster(sc , i);
            msSet.add(monster);//monster details are added to Hashset
        }
        //Only if there are 2 or more montsers breediing can take place
            if (msSet.size() >= 2) {
                //runs for monsters/2 times as kid have 2 parent
                for (int i = 0; i < msSet.size()-1; i++) {
                    for (int j = i+1; j <msSet.size();j++){
                        Monster[] msArray = msSet.toArray(new Monster[0]);
                        KidMonster kid = msArray[i].createKidWith(msArray[j]);
                    //System.out.println(kid);
                    //Kids are stored in map
                    kdList.add(kid);
                    }
                }
            }
            else {
                System.out.println("Not enough Monsters for Breeding!!!!!\n");
            }
            //created a map so that it can hold duplicate names
            Map<String, List<Monster>> msMap = new HashMap<>();
            //converted set to map
            for (Monster ms :msSet){
                String monsterName = ms.getMonsterName();
                // Get or create the list associated with the monsterName
                List<Monster> monsterList = msMap.computeIfAbsent(monsterName, k ->new ArrayList<>());
                monsterList.add(ms);
            }
            //printed map by using entry seyt

        //System.out.println(msSet.toString());
        Monster.displayMonster(msMap);
        KidMonster.displayKid(kdList);
    }
    //Used for creating the monsters
    private static Monster createMonster(Scanner sc , int i){
        System.out.println("Details for the "+(i+1)+"th monster:");
        System.out.println("Enter Monster Name: ");
        String name = sc.next();
        System.out.println("Enter the Eye Color of Monster: ");
        String eyeColor = sc.next();
        System.out.println("Enter Feather Color of Monster: ");
        String featherColor =sc.next();
        System.out.println("Enter Magic used by Monster: ");
        String magic = sc.next();
        System.out.println("Enter Size of Monster in metres: ");
        int size = sc.nextInt();
        System.out.println("Enter Strength of Monster: ");
        int strength = sc.nextInt();
        Monster ms = new Monster(name, eyeColor,featherColor,magic,size,strength);
        return ms;
    }
}