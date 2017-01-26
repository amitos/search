package com.switchApp.Search;

import com.switchApp.Game;
import com.switchApp.GamesCache;

import java.util.*;

/**
 * Created by Amit on 7/20/16.
 * SEARCH class based on keywords
 */
public class Search {


    /**
     * using the inverted cache to fetch search result and perform intersection between the results retreived by each keyword
     * @param terms
     * @return
     */
    public  static List<Game> search(String terms,boolean useAND) {
        List<String> termsList= Utils.getTerms(terms);

        List<Integer> results = new ArrayList<Integer>();
        for (String term:termsList) {

            List<Integer> docsTerm = InvertedIndex.getInstance().getDocuments(term);

            if (useAND && !results.isEmpty()) {
                results.retainAll(docsTerm);
            }else {
                results.addAll(docsTerm);
            }

        }

        return sortSearch(results,termsList);
    }



    public static List<Game> sortSearch(List<Integer> results, List<String> termsList) {
        //remove duplicates
        Set<Integer> noDups = new HashSet<Integer>();
        noDups.addAll(results);
        results = new ArrayList<Integer>(noDups);

        double [] searchTermVec = new double[termsList.size()];
        Arrays.fill(searchTermVec, 1.0);


        List<GameScore> listGameScore = new ArrayList<GameScore>();
        for (Integer gameID:results) {

            double [] docVec = new double[termsList.size()];
            int index=0;
           //calculate score for the document
           for (String term: termsList) {
               if (! Utils.getTerms(GamesCache.getInstance().getGame(gameID).name).contains(term)) {
                   docVec[index]=0.1;
               }else{
                   docVec[index]=InvertedIndex.getInstance().getDocuments2(term).count; //number of words in this document
               }
               index++;
           }
           //calculate score
           double score = cosineSimilarity(searchTermVec,docVec);

            GameScore gameScore = new GameScore(score,gameID);
            listGameScore.add(gameScore);
        }

        Collections.sort(listGameScore);
        List<Integer> sortedResults = new ArrayList<Integer>();
        for (GameScore gameScore: listGameScore) {
//            System.out.println("score :" +gameScore.score +  ", game : "+ GamesCache.getInstance().getGame(gameScore.game));
            sortedResults.add(gameScore.game);
        }

        return getResults(sortedResults);
    }

    public static double cosineSimilarity(double[] vectorA, double[] vectorB) {
        double dotProduct = 0.0;
        double normA = 0.0;
        double normB = 0.0;
        for (int i = 0; i < vectorA.length; i++) {
            dotProduct += vectorA[i] * vectorB[i];
            normA += Math.pow(vectorA[i], 2);
            normB += Math.pow(vectorB[i], 2);
        }
        return dotProduct / (Math.sqrt(normA) * Math.sqrt(normB));
    }
    /**
     * Retrieves the search result game information
     * @param ids
     * @return
     */
    public static List<Game> getResults(List<Integer> ids) {
        List<Game> games= new ArrayList<Game>();
        for(int gameID:ids) {
            games.add(GamesCache.getInstance().getGame(gameID));
        }
        return games;
    }


    //@TBD
    public static List<Integer> sortByRanking() {
        return null;
    }

}
