package com.listmovie.movies;

import java.io.Serializable ;
import java.net.URL ;

import com.google.api.client.util.Key ;

public class CastInfoAndroid implements Serializable {

        private static final long serialVersionUID = 8623103045084363150L;

        /**
         * The Url of the cast.
         */
        @Key
        public URL url;
        /**
         * The name of the cast.
         */
        @Key
        public String name;
        /**
         * The name of the character of the cast.
         */
        @Key
        public String characterName;
        /**
         * The job description of the cast.
         */
        @Key
        public String job;
        /**
         * The ID of the person.
         */
        @Key
        public int ID;
        /**
         * The ID of the cast.
         */
        @Key
        public int castID;
        /**
         * The thumbnail Url of the cast.
         */
        @Key
        public URL thumb;
        /**
         * The department of the job of the cast.
         */
        @Key
        public String department;
        /**
         * The json string that created this CastInfo object.
         */
        @Key
        public String jsonOrigin;

}
