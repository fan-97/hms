package com.hms;

import com.hms.gui.MainMenu;
import com.hms.gui.MainMenuUI;
import com.hms.util.JDBCUtil;

public class MainApplication {
    public static void main(String[] args) {
//        new MainMenu().run();
        JDBCUtil.getConn();
        new MainMenuUI();
    }
}
