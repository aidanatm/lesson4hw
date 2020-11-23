package com.company;

import java.util.Random;

public class Main {

    public static int bossHealth = 700;
    public static int bossDamage = 50;
    public static String bossDefenceType = "";
    public static int[] heroesHealth = {260, 270, 300, 270, 450, 300};
    public static int[] heroesDamage = {10, 20, 10, 10, 5, 10};
    public static String[] heroesAttackType = {"Physical", "Magical", "Mental", "Medic", "Golem", "Lucky"};


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
        System.out.println("Lucky health: " + heroesHealth[5]);
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
        lucky();
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
            //если голем жив
            if (heroesHealth[4] - bossDamage < 0)
//                если жизнь голема после удара отрицательное число
                heroesHealth[4] = 0;
//            то его здоровье равно 0
            else
                heroesHealth[4] = heroesHealth[4] - bossDamage;
            for (int i = 0; i < heroesAttackType.length; i++) {
//                подставляет каждого героя
                if (!heroesAttackType[i].equals(heroesAttackType[4]) && heroesHealth[i] > 0)
//                    если это не голем и герои живы
                    heroesHealth[4] -= bossDamage / 5;
//                то голем забирает 1/5 удара босса от игроков
                if (heroesHealth[4] - bossDamage < 0)
                    heroesHealth[4] = 0;

            }
        }
    }

    private static void lucky() {
//        Random random = new Random();
//        boolean blin = random.nextBoolean();
//        if (heroesHealth[5] > 0) {
//            if (!blin) {
//                if (heroesHealth[4] > 0) {
////                    если голем жив
//                    if (heroesHealth[5] - bossDamage + bossDamage / 5 < 0)
//                        // если отнять 40 со здоровья лаки  и это меньше нуля
//                        heroesHealth[5] = 0;
//                    //                то выходит ноль
//                    else
//                        heroesHealth[5] -= bossDamage + bossDamage / 5;
//
//                }
//                if (heroesHealth[4] < 0) {
//                     heroesHealth[5] = Math.max(heroesHealth[5] - bossDamage, 0);
//                }
//            } else {
//                System.out.println("Lucky уклонился от удара");
//
//            }
//        }
//    }
        Random random = new Random();
    int blin = random.nextInt(5);
    if (heroesHealth[5]>0 && heroesHealth[4] >0){
        if (blin == 1){
            heroesHealth[5]= heroesHealth[5]- bossDamage + bossDamage/5;
        }
    }if (heroesHealth[5]> 0 && heroesHealth[4]<0){

        }
    }

    public static void bossHit() {
        for (int i = 0; i < heroesHealth.length; i++) {
            // Цикл, отвечает за то, чтобы он прошелся по всем игрокам
            if (heroesHealth[i] > 0 && bossHealth > 0) {
//                Если герои и босс живы
                //                    Если урон босса меньше нуля, то он засчитывается как ноль, (не отрицательное число)
                if (!heroesAttackType[i].equals(heroesAttackType[4]) && !heroesAttackType[i].equals(heroesAttackType[5])) {

                    if (heroesHealth[4] > 0)
                        heroesHealth[i] = Math.max(heroesHealth[i] - bossDamage + (bossDamage / 5), 0);

                    else heroesHealth[i] = Math.max(heroesHealth[i] - bossDamage, 0);


                }
//                Иначе он просто ударяет
            }
        }
    }

    public static boolean isFinished() {
        int deadHeroes = 0;
        // количество мёртвых героев
        if (bossHealth <= 0) {
            System.out.println("Heroes won!!!");
            return true;
        }

        for (int i = 0; i < heroesAttackType.length; i++) {
            if (heroesHealth[i] <= 0) {
                // если герой мёртв
                deadHeroes++;
                //прибавляется 1 герой к умершим
            }
        }

        if (deadHeroes == heroesAttackType.length) {
            // если количество умерших героев равняется длине массива, то босс выйграл
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
1.Добавить игрока Lucky ✔️

2.Lucky имеет шанс уклонения от ударов босса
 */

