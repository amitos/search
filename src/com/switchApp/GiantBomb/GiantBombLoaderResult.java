package com.switchApp.GiantBomb;

/**
 * Created by Amit on 7/20/16.
 * Entity that represent the GiantBomb API query result
 */
public class GiantBombLoaderResult {

    enum STATUS {LOADER_OK, LOADER_ERROR};
    public STATUS status = STATUS.LOADER_ERROR;
    public int currentNumOfResults=0;
    public int totalNumOfResults=0;

    public boolean isCompleted(int pageSize){
        if (status==STATUS.LOADER_ERROR){
            return true;
        }
        if (currentNumOfResults==0 || currentNumOfResults<pageSize) {
            return true;
        }

        return false;
    }

}
