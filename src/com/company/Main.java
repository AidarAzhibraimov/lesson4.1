package com.company;

import java.util.Random;

public class Main {

    public static int roundNumber = 1;
    public static int bossHealth = 700;
    public static int bossDamage = 50;
    public static String bossDefence = "";
    public static String[] heroesAttackType = {
            "Physical", "Magical", "Kinetic", "Medic", "Golem"};
    public static int[] heroesHealth = {260, 270, 250, 350, 300};
    public static int[] heroesDamage = {15, 20, 25, 0, 10};

    public static void main(String[] args) {
        printStatistics();
        while (!isGameFinished()) {
            round();
        }
    }

    public static void chooseBossDefence() {
        Random random = new Random();
        int randomIndex = random.nextInt(heroesAttackType.length); // 0, 1, 2
        bossDefence = heroesAttackType[randomIndex];
        System.out.println("Boss choose " + bossDefence);
    }

    public static boolean isGameFinished() {
        if (bossHealth <= 0) {
            System.out.println("Heroes won!!!");
            return true;
        }
        /*if (heroesHealth[0] <= 0 && heroesHealth[1] <= 0
                && heroesHealth[2] <= 0) {
            System.out.println("Boss won!!!");
            return true;
        }
        return false;*/
        boolean allHeroesDead = true;
        for (int i = 0; i < heroesHealth.length; i++) {
            if (heroesHealth[i] > 0) {
                allHeroesDead = false;
                break;
            }
        }
        if (allHeroesDead) {
            System.out.println("Boss won!!!");
        }
        return allHeroesDead;
    }

    public static void round() {
        System.out.println("ROUND: " + roundNumber);
        chooseBossDefence();
        bossHits();
        heroesHit();
        hilt();
        printStatistics();
        roundNumber++;
    }

    public static void hilt(){
        Random random = new Random();
        int hiro = random.nextInt(3);

        if (heroesHealth[hiro] < 100 && heroesHealth[hiro] > 0 && heroesHealth[3] > 0) {
           Random hilt = new Random();
           int increased = hilt.nextInt(50) + 1;
           heroesHealth[hiro] = heroesHealth[hiro] + increased;
            System.out.println("Medic increased health by " + increased + " player " + heroesAttackType[hiro]);
        }
    }

    public static void heroesHit() {
        for (int i = 0; i < heroesDamage.length; i++) {
            if (heroesHealth[i] > 0 && bossHealth > 0) {
                if (heroesAttackType[i] == bossDefence) {
                    Random random = new Random();
                    int coeff = random.nextInt(11); // 0,1,2,3,4,5,6,7,8
                    if (bossHealth - heroesDamage[i] * coeff <= 0) {
                        bossHealth = 0;
                    } else {
                        bossHealth = bossHealth - heroesDamage[i] * coeff;
                    }
                    System.out.println("Critical Damage "
                            + heroesDamage[i] * coeff);
                } else {
                    if (bossHealth - heroesDamage[i] < 0) {
                        bossHealth = 0;
                    } else {
                        bossHealth = bossHealth - heroesDamage[i];
                    }
                }
            }
        }
    }

    public static void bossHits() {
        for (int i = 0; i < heroesHealth.length; i++) {
            if (heroesHealth[i] > 0) {
                if (heroesHealth[i] - bossDamage < 0) {
                    heroesHealth[i] = 0;
                } else {
                    heroesHealth[i] = heroesHealth[i] - bossDamage;
                }
            }
        }
    }

    /*
    ???????????????? 4-???? ???????????? Medic, ?? ???????????????? ???????? ?????????????????????? ???????????? ?????????? ?????????????? ????????????
    ???? N-?????? ???????????????????? ???????????? ???????????????? ???????????? ???????????? ???? ???????????? ??????????????, ????????????????
    ???????????????? ?????????? 100 ????????????. ?????????????? ???????????? ?????????? ???????????????? ???? ??????????, ?? ?????????? ???? ???? ??????
    ?????? ???????? ?????? ??????. ?????????? ???? ?????????????????? ?? ??????, ???? ???????????????? ???????? ???? ??????????.
    ?????? ???????? ?????????? ???????????? ???? ??????????.

    ???? ???? ??????????????????????????????????:
???????????????? n-???? ????????????, Golem, ?????????????? ?????????? ?????????????????????? ?????????? ???? ???????????? ????????.
?????????? ?????????????????? ???? ???????? 1/5 ?????????? ?????????? ???????????????????? ???? ?????????? ???? ???????????? ??????????????.
???????????????? n-???? ????????????, Lucky, ?????????? ???????? ?????????????????? ???? ???????????? ??????????.
???????????????? n-???? ????????????, Berserk, ?????????????????? ?????????? ?????????? ?????????? ???? ???????? ?? ???????????????????? ?????????????????????????????? ???????? ??
???????????? ?????????? ?? ???????????????????? ?????? ??????????
???????????????? n-???? ????????????, Thor, ???????? ???? ?????????? ?????????? ???????? ???????????????? ?????????? ???? 1 ??????????, ???????????????????? ????????
???????? ???????????????????? 1 ?????????? ?? ???? ?????????????? ???????? ????????????. // random.nextBoolean(); - true, false
??????????????: 05.09.2021 23 59
*/

    public static void printStatistics() {
        System.out.println("________________");
        System.out.println("Boss Health: " + bossHealth +
                "; Boss Damage: " + bossDamage);
        for (int i = 0; i < heroesHealth.length; i++) {
            System.out.println(
                    heroesAttackType[i] + " Health: "
                            + heroesHealth[i] +
                            "; " + heroesAttackType[i] + " Damage: "
                            + heroesDamage[i]);
        }
        System.out.println("________________");
    }
}
//???? ???? ?????????????????????????????????? ??????????????, ???? ?????????????? ?? ?????????????? ???? ???? ????????????
