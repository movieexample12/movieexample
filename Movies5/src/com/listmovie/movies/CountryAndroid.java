package com.listmovie.movies;

import java.io.Serializable ;
import java.net.URL ;

import com.google.api.client.util.Key ;

public class CountryAndroid implements Serializable {

        private static final long serialVersionUID = 5337925489671786943L;

        /**
         * The url of the Country.
         */
        @Key
        public URL url;
        /**
         * The name of the Country.
         */
        @Key
        public String name;
        /**
         * The code of the Country;
         */
        @Key
        public String code;
}
