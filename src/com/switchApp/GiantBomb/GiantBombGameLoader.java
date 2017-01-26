package com.switchApp.GiantBomb;

import com.switchApp.Game;
import com.switchApp.GamesCache;
import com.switchApp.Search.InvertedIndexBuilder;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Amit on 7/20/16.
 * Concrete class to load games from Giant Bomb Site
 */
public class GiantBombGameLoader extends GiantBombLoader {


    public String getAPIName() {
         return "games";
     }

    public  GiantBombLoaderResult loadDataByOffset(String urlStr,String ext) {
        GiantBombLoaderResult resultLoader = new GiantBombLoaderResult();
        try {

            if (urlStr ==null ) {
                return resultLoader;
            }else{
                urlStr+="&platforms="+ext;
            }
            URL url = new URL(urlStr);
            int i=0;
            try (InputStream is = url.openStream();
                 JsonReader rdr = Json.createReader(is)) {
                JsonObject obj = rdr.readObject();

                //check return result
                if (obj.getString("error").equals("OK")) {
                    resultLoader.status = GiantBombLoaderResult.STATUS.LOADER_OK;
                    resultLoader.currentNumOfResults = obj.getInt("number_of_page_results");
                    resultLoader.totalNumOfResults = obj.getInt("number_of_total_results");
                }else
                {
                    resultLoader.status = GiantBombLoaderResult.STATUS.LOADER_ERROR;
                    return resultLoader;
                }
                JsonArray results = obj.getJsonArray("results");
                //convert json result to entities and load them to cache and to the inverted index
                for (JsonObject result : results.getValuesAs(JsonObject.class)) {
                    i++;

                    int id = 0;
                    if (!result.isNull("id")) {
                        id = result.getInt("id");
                    }
                    String name="";
                    if (!result.isNull("name")) {
                        name = result.getString("name");
                    }
                    String description="";
                    if (!result.isNull("description")) {
                        description = result.getString("description");
                    }
                    String characters="";

                    Game game = new Game(id,name,description,characters);
                    //build the cache
                    GamesCache.getInstance().addGame(id,game);
                    InvertedIndexBuilder.addGameToIndex(game);
                }

            }
            return resultLoader;
        } catch (MalformedURLException mue) {
            System.out.println("Ouch - a MalformedURLException happened.");
            mue.printStackTrace();
            System.exit(1);
        } catch (IOException ioe) {
            System.out.println("Oops- an IOException happened.");
            ioe.printStackTrace();
            System.exit(1);
        } finally {
            return resultLoader;
        } // end of 'finally' clause
    }

}
