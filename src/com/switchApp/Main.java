package com.switchApp;


import com.switchApp.GiantBomb.GiantBombGameLoader;
import com.switchApp.GiantBomb.GiantBombLoader;
import com.switchApp.GiantBomb.GiantBombPlatformLoader;
import com.switchApp.Search.Search;

import java.io.Console;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        //load all games platforms
        GiantBombPlatformLoader platfromloader = new GiantBombPlatformLoader();
        platfromloader.loadAll("","");
        //load all nintendo games
        GiantBombGameLoader gameloader = new GiantBombGameLoader();
        gameloader.loadAll(PlatformCache.getInstance().getPlatformID("Nintendo Entertainment System")+"","Nintendo Entertainment System");
        gameloader.loadAll(PlatformCache.getInstance().getPlatformID("Super Nintendo Entertainment System")+"","Super Nintendo Entertainment System");
        gameloader.loadAll(PlatformCache.getInstance().getPlatformID("Nintendo 64")+"","Nintendo 64");

        Scanner scanner = new Scanner(System.in);
        System.out.print("Do you want to use AND boolean Query Mode? (Y/N):\t");
        String booleanMode = scanner.nextLine();
        boolean useAnd=false;
        if (booleanMode.toLowerCase().equals("y")) {
            useAnd=true;
        }

        do {
            showInterface(useAnd);
        }while(true);
    }

    /*
        SHOW COMMAND LINE INTERFACE
     */
    public static void showInterface(boolean useAnd) {
        Scanner scanner = new Scanner(System.in);
        System.out.println();
        System.out.println();
        System.out.print("Enter your search or press CTRL-C to exit:\t");
        String search = scanner.nextLine();
        List<Game> results = Search.search(search,useAnd);
        System.out.println("*********** SHOWING "+results.size()+ " RESULTS *********** :\t");
        for (Game game:results) {
            System.out.println(game.toString());
        }
        System.out.print("*********** END OF RESULTS *********** :\t");



    }
}
