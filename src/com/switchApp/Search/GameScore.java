package com.switchApp.Search;

/**
 * Created by Amit on 7/21/16.
 */
public class GameScore implements Comparable<GameScore>{

        public  int game;
        public double score;

        GameScore(double _score,int _game) {
            score = _score;
            game = _game;
        }

    public int compareTo(GameScore another) {
        if (this.score<another.score){
            return 1;
        }else{
            return -1;
        }
    }

}
