package com.switchApp.Search;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Amit on 7/20/16.
 * Utility Class
 */
public class Utils {
    public static String[] stopWords= {"a", "an", "the"};

    /**
     * Remove Stop words
     * @param terms
     * @return
     */
    public static List<String> removeStopWords(List<String> terms) {
        List stopWordList = new ArrayList(Arrays.asList(Utils.stopWords));
        terms.removeAll(stopWordList);
        return terms;
    }

    /**
     * Get terms from an input - convert to lower case, remove special characters and remove stop words
     * @param name
     * @return
     */
    public static List<String> getTerms(String name) {

        List<String> terms = null;
        //convert to lowercase and remove special characters
        String lowercaseName = name.toLowerCase().replaceAll("[^\\dA-Za-z ]", "");;

        if (lowercaseName.trim().length()==0){
            return new ArrayList<String>();
        }
        String[] termsArr = lowercaseName.split(" ");

        terms = new ArrayList(Arrays.asList(termsArr));
        //remove stop words
        return Utils.removeStopWords(terms);

    }
}
