package com.switchApp.Search;

import com.switchApp.Game;

import java.util.*;

/**
 * Created by Amit on 7/20/16.
 * Manages the inverted Index
 * Support data structure for ranked searched.
 */
public class InvertedIndexBuilder {

    public static void addGameToIndex(Game game) {
        //build the inverted Index
        List<String> terms = Utils.getTerms(game.name);
        HashMap<String,DocStructure> mapTermCount = new  HashMap<String,DocStructure>();
        for (String term:terms) {
            DocStructure docStructure = mapTermCount.get(term);
            if (docStructure==null){
                docStructure = new DocStructure(game.ID,1);
                mapTermCount.put(term,docStructure);
            }else {
                docStructure.count++;
            }

        }

        for(Map.Entry<String, DocStructure> entry : mapTermCount.entrySet()) {
            InvertedIndex.getInstance().addTerm2(entry.getKey(),entry.getValue());
            InvertedIndex.getInstance().addTerm(entry.getKey(),entry.getValue().game);
        }

    }


}
