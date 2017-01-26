package com.switchApp;

import java.util.HashMap;

/**
 * Created by Amit on 7/20/16.
 */
public class PlatformCache {

    //map game platform name to game platform ID
    HashMap<String,Integer> platformsNameToID = new HashMap<String,Integer>();
    //map game platform ID to game platform name
    HashMap<Integer,Platform> platforms = new HashMap<Integer,Platform>();

    private static PlatformCache instance = null;

    public void addPlatform(int id, Platform platform) {
        platforms.put(id,platform);
        platformsNameToID.put(platform.name,id);

    }

    public Platform getPlatform(int id) {
        return platforms.get(id);
    }

    public Integer getPlatformID(String name) {
        return platformsNameToID.get(name);
    }

    protected PlatformCache() {
        // Exists only to defeat instantiation.
    }

    public static PlatformCache getInstance() {
        if(instance == null) {
            instance = new PlatformCache();
        }
        return instance;
    }

}
