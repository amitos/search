package com.switchApp.Search;

import java.util.HashMap;
import java.util.ArrayList;

/**
 * Created by Amit on 7/20/16.
 * the inverted index key: term value: list of all games which contain the term
 */
public class InvertedIndex {

    HashMap<String,ArrayList<Integer>> index = new HashMap<String,ArrayList<Integer>>();
    HashMap<String,TermStructure> index2 = new HashMap<String,TermStructure>();

    private static InvertedIndex instance = null;

    public void addTerm(String term,int documentID) {
        ArrayList list = index.get(term);
        if (list==null) {
            list = new ArrayList();
            index.put(term,list);
        }
        list.add(documentID);

    }

    /**
     * Manage data structure for future ranked search
     * @param term
     * @param docStruct
     */
    public void addTerm2(String term,DocStructure docStruct) {
        TermStructure termStruct = index2.get(term);
        if (termStruct==null) {
            termStruct = new TermStructure();
            index2.put(term,termStruct);
        }
        termStruct.count+=docStruct.count;
        termStruct.list.add(docStruct);

    }

    public TermStructure getDocuments2(String term) {
        TermStructure termStruct = index2.get(term);

        return termStruct;
    }


    public ArrayList<Integer> getDocuments(String term) {
        ArrayList list = index.get(term);
        if (list==null) {
            list = new ArrayList();
        }
        return list;
    }

    protected InvertedIndex() {
        // Exists only to defeat instantiation.
    }

    public static InvertedIndex getInstance() {
        if(instance == null) {
            instance = new InvertedIndex();
        }
        return instance;
    }

}
