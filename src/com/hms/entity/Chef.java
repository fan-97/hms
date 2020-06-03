package com.hms.entity;

import java.util.Arrays;

public class Chef {
    private String name;
    private int []wordDay;
    private String [] menu;

    public Chef() {
    }

    public Chef(String name, int[] wordDay, String[] menu) {
        this.name = name;
        this.wordDay = wordDay;
        this.menu = menu;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int[] getWordDay() {
        return wordDay;
    }

    public void setWordDay(int[] wordDay) {
        this.wordDay = wordDay;
    }

    public String[] getMenu() {
        return menu;
    }

    public void setMenu(String[] menu) {
        this.menu = menu;
    }

    @Override
    public String toString() {
        return "Chef{" +
                "name='" + name + '\'' +
                ", wordDay=" + Arrays.toString(wordDay) +
                ", menu=" + Arrays.toString(menu) +
                '}';
    }
}
