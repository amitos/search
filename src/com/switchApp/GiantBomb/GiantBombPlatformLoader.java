package com.switchApp.GiantBomb;

import com.switchApp.Game;
import com.switchApp.GamesCache;
import com.switchApp.Platform;
import com.switchApp.PlatformCache;
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
 * Concerte class to load game platform from Giant Bomb site
 */
public class GiantBombPlatformLoader extends GiantBombLoader {

    public String getAPIName() {
        return "platforms";
    }

    public  GiantBombLoaderResult loadDataByOffset(String urlStr,String ext) {
        GiantBombLoaderResult resultLoader = new GiantBombLoaderResult();
        try {
            if (urlStr == null) {
                return resultLoader;
            }

            URL url = new URL(urlStr);
            int i = 0;

            InputStream is = url.openStream();

            JsonReader rdr = Json.createReader(is);


            JsonObject obj = rdr.readObject();



            if (obj.getString("error").equals("OK")) {
                resultLoader.status = GiantBombLoaderResult.STATUS.LOADER_OK;
                resultLoader.currentNumOfResults = obj.getInt("number_of_page_results");
                resultLoader.totalNumOfResults = obj.getInt("number_of_total_results");

            } else {
                System.out.println("load error");
                resultLoader.status = GiantBombLoaderResult.STATUS.LOADER_ERROR;
                return resultLoader;
            }
            JsonArray results = obj.getJsonArray("results");
            for (JsonObject result : results.getValuesAs(JsonObject.class)) {
                i++;

                int id = 0;
                if (!result.isNull("id")) {
                    id = result.getInt("id");
                }
                String name = "";
                if (!result.isNull("name")) {
                    name = result.getString("name");
                }

                Platform platform = new Platform(id, name);
                //build the cache
                PlatformCache.getInstance().addPlatform(id, platform);


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

        }catch(Throwable t) {
            System.out.println("throwable");
            t.printStackTrace();
            System.exit(1);
        }finally {
//            System.out.println("in finally");

            return resultLoader;


        } // end of 'finally' clause
    }

}
