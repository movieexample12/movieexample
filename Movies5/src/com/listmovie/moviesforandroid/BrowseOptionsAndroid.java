package com.listmovie.moviesforandroid;

import net.sf.jtmdb.BrowseOptions ;

public class BrowseOptionsAndroid extends BrowseOptions {
    
    public String getBuiltQuery() {
        return buildQuery();
    }
    
}
