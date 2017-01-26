package com.switchApp.GiantBomb;

/**
 * Created by Amit on 7/20/16.
 * Base class loader from GiantBomb site
 */
public abstract class GiantBombLoader {


    public String apiKey = "84e4fdf8957ddf84247c3ea012a4773ffead8156";
    public String baseAPIUrl = "http://www.giantbomb.com/api/";


    //load information by batches
    public void loadAll(String ext,String extString) {

        int offset =0;
        int pageSize=100;
        GiantBombLoaderResult result=null;
        int i=0;
        System.out.print("Loading.");
        do {
            ;
            result = loadDataByOffset(getUrl(offset),ext);
            offset+=pageSize;
            i+=result.currentNumOfResults;
            System.out.print("..");
        }while (!result.isCompleted(pageSize));
        System.out.println("Loaded "+i + " " + extString + " " + getAPIName() + " entities");
    }

    //get the specific url
    public String getUrl(int offset){
        return baseAPIUrl+getAPIName()+"/?api_key="+apiKey+"&format=json&offset="+offset;
    }
    //virtual function to load by batches
    abstract public  GiantBombLoaderResult loadDataByOffset(String url,String ext);

    //get the specific data api
    abstract public  String getAPIName();


}
