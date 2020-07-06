package src;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Process{
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

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        Scanner command = new Scanner(System.in);

        int goalValue; // goal starforce stage
        int equipLevel;
        int stimulationNum;
        boolean isStarCatch = false;
        boolean isSaveDestroy = false;

        System.out.println("StarForce Stimulation Application");
        System.out.println("input command");

        while(true){
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
        
    }
}