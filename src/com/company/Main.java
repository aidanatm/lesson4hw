package com.company;

import java.util.Random;

public class Main {

    public static int bossHealth = 700;
    public static int bossDamage = 100;
    public static String bossDefenceType = "";
    public static int[] heroesHealth = {260, 270, 300, 270, 450};
    public static int[] heroesDamage = {10, 20, 10, 10, 5};
    public static String[] heroesAttackType = {"Physical", "Magical", "Mental", "Medic", "Golem"};



    public static void heroesHit() {
        for (int i = 0; i < heroesDamage.length; i++) {
            if (heroesHealth[i] > 0 && bossHealth > 0 && !heroesAttackType[3].equals(heroesAttackType[i])) {
                //живы ли герои и жив ли босс и 270 не равен 270
                bossHealth = Math.max(bossHealth - heroesDamage[i], 0);
            } else {
                System.out.println(heroesAttackType[i] + " не атаковал");
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
        System.out.println("Golem health: " + heroesHealth[4]);
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
        golem();
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

    private static void golem() {
        if (heroesHealth[4] > 0) {
            heroesHealth[4] -= bossDamage;
            for (int i = 0; i < heroesAttackType.length; i++) {
                if (!heroesAttackType[i].equals(heroesAttackType[4]) && heroesHealth[i] > 0)
                    heroesHealth[4] -= bossDamage / 5;
            }
        }
    }


    public static void bossHit() {
        for (int i = 0; i < heroesHealth.length; i++) {
            // Цикл, отвечает за то, чтобы он прошелся по всем игрокам
            if (heroesHealth[i] > 0 && bossHealth > 0) {
//                Если герои и босс живы
                //                    Если урон босса меньше нуля, то он засчитывается как ноль, (не отрицательное число)
                if (!heroesAttackType[i].equals(heroesAttackType[4])) {

                    if (heroesHealth[4] > 0)
                        heroesHealth[i] = Math.max(heroesHealth[i] - bossDamage - (bossDamage / 5), 0);
                    else heroesHealth[i]=Math.max(heroesHealth[i]- bossDamage,0 );

                }
//                Иначе он просто ударяет
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


/*
1. Принимает на себя 1/5 часть удара от босса по другим игрокам.

2.
 */

