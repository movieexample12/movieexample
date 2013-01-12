package com.listmovie.movies;

import java.io.Serializable ;
import java.net.URL ;

import com.google.api.client.util.Key ;

public class GenreAndroid implements Serializable {

        private static final long serialVersionUID = 1450989769066278063L;

        /**
         * The url of the Genre.
         */
        @Key
        public URL url;
        /**
         * The name of the Genre.
         */
        @Key
        public String name;
        /**
         * The ID of the Genre.
         */
        @Key
        public int ID;
}
