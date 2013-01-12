package com.listmovie.movies;

import java.io.Serializable ;
import java.net.URL ;

import com.google.api.client.util.Key ;

public class StudioAndroid implements Serializable {

        private static final long serialVersionUID = -3712697550558073084L;

        /**
         * The url of the Studio.
         */
        @Key
        public URL url;
        /**
         * The name of the Studio.
         */
        @Key
        public String name;

}
