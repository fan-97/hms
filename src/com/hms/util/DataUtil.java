package com.hms.util;

import com.hms.entity.Chef;
import com.hms.entity.Room;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class DataUtil {
    private static Chef[] chefs;
    private static Room[] rooms;
    public static final int FLOOR = 10;

    static {
        // prepared data
        chefs = new Chef[4];
        chefs[0] = new Chef();
        chefs[0].setName("Karen Adam");
        chefs[0].setWordDay(new int[]{DayOfWeek.MONDAY.getValue(), DayOfWeek.FRIDAY.getValue()});
        chefs[0].setMenu(new String[]{"shrimp soup", "cauliflower and mushroom stew", "spicy chicken nuggets", "steamed cod fish", "turkey burger", "veggie burger", "fried egg"});
        chefs[1] = new Chef("Hari Philip"
                , new int[]{DayOfWeek.WEDNESDAY.getValue(), DayOfWeek.THURSDAY.getValue(), DayOfWeek.FRIDAY.getValue(), DayOfWeek.SATURDAY.getValue(), DayOfWeek.SUNDAY.getValue()}
                , new String[]{"chicken curry", "chicken masala", "mutton Korma", "keema curry", "mushroom tikka", "fried egg", "curry rice"});
        chefs[2] = new Chef("Thalia Hensley"
                , new int[]{DayOfWeek.MONDAY.getValue(), DayOfWeek.THURSDAY.getValue(), DayOfWeek.SATURDAY.getValue()}
                , new String[]{"tofu teriyaki", "shrimp tempura", "yaki udon", "chicken katsu", "salmon sashimi", "fried egg", "curry rice"});
        chefs[3] = new Chef("Nisha Moss"
                , new int[]{DayOfWeek.THURSDAY.getValue(), DayOfWeek.SATURDAY.getValue(), DayOfWeek.SUNDAY.getValue()}
                , new String[]{"black pepper beef", "pork chowmein", "sweet & sour pork", "gongbao chicken", "pork jiaozi", "soy glazed pork chops", "curry rice"});

        rooms = new Room[13];
        rooms[0] = new Room("%s01", "Large double bed");
        rooms[1] = new Room("%s02", "Large double bed");
        rooms[2] = new Room("%s03", "Large single bed");
        rooms[3] = new Room("%s04", "Large single bed");
        rooms[4] = new Room("%s05", "Small single bed");
        rooms[5] = new Room("%s06", "Small single bed");
        rooms[6] = new Room("%s07", "Small single bed");
        rooms[7] = new Room("%s08", "Small single bed");
        rooms[8] = new Room("%s09", "Large single bed");
        rooms[9] = new Room("%s10", "Large single bed");
        rooms[10] = new Room("%s11", "Large double bed");
        rooms[11] = new Room("%s12", "Large double bed");
        rooms[12] = new Room("%s13", "VIP Room");
    }

    public static Room[] getRooms() {
        return rooms;
    }

    public static Chef[] getChefs(int dayOfWeek) {
        List<Chef> result = new ArrayList<>();
        for (Chef chef : chefs) {
            for (int weekday : chef.getWordDay()) {
                if(dayOfWeek == weekday) {
                    result.add(chef);
                    break;
                }
            }
        }
        return result.toArray(new Chef[0]);
    }

    public static Room getRoom(String roomNumber) {
        if (roomNumber != null) {
            for (int i = 0; i < rooms.length; i++) {
                if (rooms[i].getNumber().substring(2).equals(roomNumber.substring(1))) {
                    return rooms[i];
                }
            }
        }
        return null;
    }
}
