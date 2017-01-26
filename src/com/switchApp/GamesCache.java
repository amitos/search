package com.switchApp;

import java.util.HashMap;

/**
 * Created by Amit on 7/20/16.
 * Manages the games in an in-memory cache based on key : game ID value: game object
 */
public class GamesCache {

    //map game id to game entity
    HashMap<Integer,Game> games = new HashMap<Integer,Game>();

    private static GamesCache instance = null;

    public void addGame(int id, Game game) {
        games.put(id,game);

    }

    public Game getGame(int id) {
        return games.get(id);
    }

    protected GamesCache() {
        // Exists only to defeat instantiation.
    }

    public static GamesCache getInstance() {
        if(instance == null) {
            instance = new GamesCache();
        }
        return instance;
    }


}
