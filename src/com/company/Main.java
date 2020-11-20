package com.company;

import java.util.Random;

public class Main {

    public static int bossHealth = 700;
    public static int bossDamage = 50;
    public static String bossDefenceType = "";
    public static int[] heroesHealth = {260, 270, 300, 270};
    public static int[] heroesDamage = {10, 20, 5, 10};
    public static String[] heroesAttackType = {"Physical", "Magical", "Mental", "Medic"};


    public static void heroesHit() {
        for (int i = 0; i < heroesDamage.length; i++) {
            if (heroesHealth[i] > 0 && bossHealth > 0 && heroesAttackType[3] != heroesAttackType[i]) {
                //живы ли героиб и жив ли босс и 270 не равен 270
                if (bossHealth - heroesDamage[i] < 0)
                    bossHealth = 0;
                else bossHealth = bossHealth - heroesDamage[i];
            } else {
                System.out.println(heroesAttackType[i]+" не атаковал");
            }
        }
    }

    public static void printStatistics() {
        System.out.println("-----------");
        System.out.println("СТАТИСТИКА");
        System.out.println("Boss health: " + bossHealth);
        System.out.println("Physical health: " + heroesHealth[0]);
        System.out.println("Magical health: " + heroesHealth[1]);
        System.out.println("Mental health: " + heroesHealth[2]);
        System.out.println("Medic health: " + heroesHealth[3]);
        System.out.println("-----------");

    }

    public static void main(String[] args) {
        while (!isFinished()) {
            round();
        }

    }

    public static void round() {
        printStatistics();
        System.out.println("Round was started");
        changeBossDefence();
        heroesHit();
        bossHit();
        medicsTreatment();

    }

    private static void medicsTreatment() {
        Random random = new Random();
        int jf = random.nextInt(10) + 1;

        if (heroesHealth[3] > 0) {
            for (int i = 0; i < heroesAttackType.length; i++) {
                // i=0; 0<4; 0+1
                if (heroesHealth[i] < 100 && heroesHealth[i] > 0 && heroesHealth[3] != heroesHealth[i]) {//
                    heroesHealth[i] = heroesHealth[i] + jf * heroesDamage[3];
                    System.out.println("Medic вылечил " + heroesAttackType[i] + " на " + jf * heroesDamage[3]);
                    break;
                }

            }

        }
    }

    public static void bossHit() {
        for (int i = 0; i < heroesHealth.length; i++) {
            if (heroesHealth[i] > 0 && bossHealth > 0) {
                if (heroesHealth[i] - bossDamage < 0) {
                    heroesHealth[i] = 0;
                } else heroesHealth[i] = heroesHealth[i] - bossDamage;
            }
        }
    }

    public static boolean isFinished() {
        if (bossHealth <= 0) {
            System.out.println("Heroes won!!!");
            return true;
        }
        if (heroesHealth[0] <= 0 && heroesHealth[1] <= 0 && heroesHealth[2] <= 0) {
            System.out.println("Boss won!!!");
            return true;
        }
        return false;
    }


    public static void changeBossDefence() {
        Random random = new Random();
        int randomIndex = random.nextInt(heroesAttackType.length);
        bossDefenceType = heroesAttackType[randomIndex];
        System.out.println("Boss defence type: " + bossDefenceType);
    }
}

