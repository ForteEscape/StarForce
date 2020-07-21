package src;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Process{ // main
    static List<EquipInfo> equipmentList = new ArrayList<>();

    static double[] starForcePercentage = 
    {
        0.95, 0.90, 0.85, 0.85, 0.80,
        0.75, 0.70, 0.65, 0.60, 0.55,
        0.50, 0.45, 0.40, 0.35, 0.30,
        0.30, 0.30, 0.30, 0.30, 0.30,
        0.30, 0.30, 0.03, 0.02, 0.01
    };

    static double[] destroyPercentage = 
    {
        0.00,   0.00,   0.00,   0.00,   0.00,
        0.00,   0.00,   0.00,   0.00,   0.00,
        0.00,   0.00,   0.006,  0.013,  0.014,
        0.021,  0.021,  0.021,  0.028,  0.028,
        0.07,   0.07,   0.194,  0.294,  0.396
    };

    static int[] starForceMoney_140lv = 
    {
        110800, 220500, 330300, 440000, 549800, 
        659600, 769300, 879100, 988800, 1098600,
        8895400, 11250800, 13964700, 17057900, 20550500,
        24462200, 28812500, 33620400, 38904500, 44683300,
        50974700, 57796700
    };

    static int[] starForceMoney_150lv = 
    {
        136000, 271000, 406000, 541000, 676000,
        811000, 946000, 1081000, 1216000, 1351000,
        5470800, 6919400, 8588400, 10490600, 12638500,
        30087200, 35437900, 41351400, 47850600, 54958200,
        62696400, 71087200
    };

    static int[] starForceMoney_160lv =
    {
        164800, 328700, 492500, 656400, 820200,
        984000, 1147900, 1311700, 1475600, 1639400,
        6639400, 8397300, 10422900, 12731500, 15338200,
        36514500, 43008300, 50185100, 58072700, 66698700,
        76090000, 86273300
    };

    static int[] starForceMoney_200lv =
    {
        321000, 641000, 961000, 1281000, 1601000,
        1921000, 2241000, 2561000, 2881000, 3201000,
        12966500, 16400100, 20356300, 24865300, 29956500,
        71316500, 83999600, 98016700, 113422300, 130270000,
        148612400, 168501500
    };

    static boolean isStarCatch = false;
    static boolean isSaveDestroy = false;

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        Scanner command = new Scanner(System.in);

        int goalValue; // goal starforce stage
        int equipLevel;
        int stimulationNum;

        System.out.println("StarForce Stimulation Application");
        System.out.println("input command");

        while(true){ // command line
            System.out.print("$ ");
            String cmd = command.nextLine();

            if(cmd.equals("stimulation")){
                System.out.print("goalValue : ");
                goalValue = sc.nextInt();

                System.out.print("equipLevel : ");
                equipLevel = sc.nextInt();

                System.out.print("Do starcatch : ");
                String doStarcatch = command.nextLine();
                if(doStarcatch.equals("Yes")){
                    isStarCatch = true;
                }

                System.out.print("Do save destroy : ");
                String doSave = command.nextLine();
                if(doSave.equals("Yes")){
                    isSaveDestroy = true;
                }

                System.out.print("how many times : ");
                stimulationNum = sc.nextInt();

                startStimulation(goalValue, equipLevel, isStarCatch, isSaveDestroy, stimulationNum);
            }
            else if(cmd.equals("exit")){
                break;
            }
        }

        sc.close();
        command.close();
    }

    static void startStimulation(int goalValue, int equipLevel, boolean isStarCatch, boolean isSaveDestroy, int stimulationNum){
        for(int i = 0; i<stimulationNum; i++){
            equipmentList.add(new EquipInfo(equipLevel));

            calcStarforce(equipmentList.get(i), goalValue, equipLevel);
        }
    }

    static void calcStarforce(EquipInfo targetEquip, int goalLv, int equipLevel){
        Random rand = new Random();
        int chanceTimeCount = 0; // chancetime

        while(targetEquip.sfLevel != goalLv && targetEquip.sfLevel < goalLv){ // 강화 부분
            int starForceSeed = rand.nextInt(100);
            boolean isdesroyed = false;

            if(targetEquip.sfLevel >= 17){
                isSaveDestroy = false;
            }

            if(starForceSeed >= starForcePercentage[targetEquip.sfLevel] * 100){ // fail start
                if(targetEquip.sfLevel >= 12){ // calc destroy
                    if(isSaveDestroy == false){
                        int destroySeed = rand.nextInt(1000);

                        if(destroySeed < destroyPercentage[targetEquip.sfLevel] * 1000){
                            System.out.println("destroy");
                            isdesroyed = true;
                        }
                    }
                }

                addMeso(targetEquip, targetEquip.equipmentLv); // add money

                if(isdesroyed == false){
                    targetEquip.sfLevel--;
                }
                else{
                    targetEquip.sfLevel = 12;
                    targetEquip.spendEquip++;
                }

                chanceTimeCount++;

            } // fail end
            else{ // success
                addMeso(targetEquip, targetEquip.equipmentLv); // add money
                targetEquip.sfLevel++;
            }
        }
    }

    static void addMeso(EquipInfo targetEquip, int equipmentLv){
        switch(equipmentLv){
            case 140 : 
                targetEquip.spendMeso += starForceMoney_140lv[targetEquip.sfLevel];
                break;

            case 150 : 
                targetEquip.spendMeso += starForceMoney_150lv[targetEquip.sfLevel];
                break;
            
            case 160 :
                targetEquip.spendMeso += starForceMoney_160lv[targetEquip.sfLevel];
                break;
            
            case 200 :
                targetEquip.spendMeso += starForceMoney_200lv[targetEquip.sfLevel];
                break;
        }
    }
}