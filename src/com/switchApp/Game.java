package com.switchApp;

/**
 * Created by Amit on 7/20/16.
 * Game Entity
 */
public class Game {

    public String name;
    public String description;
    public String characters;
    public int ID;

    public Game(int _id, String _name, String _description,String _characters) {
        ID = _id;
        name= _name;
        description=_description;
        characters = _characters;

    }

    public String toString() {
        return ("ID: "+ID+", name: " + name );//+ ", description :" + description + ", characters :" + characters);

    }
}
