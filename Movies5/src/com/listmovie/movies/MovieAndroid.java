package com.listmovie.movies;

import java.io.Serializable ;
import java.net.URL ;
import java.util.Date ;
import java.util.LinkedHashSet ;
import java.util.Set ;

import com.google.api.client.util.Key ;

import net.sf.jtmdb.CastInfo ;
import net.sf.jtmdb.Country ;
import net.sf.jtmdb.Genre ;
import net.sf.jtmdb.MovieImages ;
import net.sf.jtmdb.Studio ;

public class MovieAndroid implements Serializable {

        private static final long serialVersionUID = -6360810410868514356L;
        /**
         * The name of the movie.
         */
        @Key
        public String name;
        /**
         * The original name for the movie.
         */
        @Key
        public String originalName;
        /**
         * The alternative name for the movie.
         */
        @Key
        public String alternativeName;
        /**
         * The ID of the movie.
         */
        @Key
        public int ID;
        /**
         * The imdb ID of the movie.
         */
        @Key
        public String imdbID;
        /**
         * The url of the movie.
         */
        @Key
        public URL url;
        /**
         * The movie overview.
         */
        @Key
        public String overview;
        /**
         * The rating of the movie.
         */
        @Key
        public double rating;
        /**
         * The release date of the movie.
         */
        @Key
        public Date releasedDate;
        /**
         * The images of the movie.
         */
        @Key
        public MovieImages images;
        /**
         * Is the movie translated.
         */
        @Key
        public boolean translated;
        /**
         * Is the movie for adult audiences only.
         */
        @Key
        public boolean adult;
        /**
         * The language of the Movie.
         */
        @Key
        public String language;
        /**
         * The type of the Movie.
         */
        @Key
        public String movieType;
        /**
         * The json string that created this Movie object.
         */
        @Key
        public String jsonOrigin;
        /**
         * The movie certification.
         */
        @Key
        public String certification;
        /**
         * The version of the Movie.
         */
        @Key
        public int version;
        /**
         * The votes for this Movie.
         */
        @Key
        public int votes;
        /**
         * The date of the last modification.
         */
        @Key
        public Date lastModifiedAt;

        /**
         * Denotes whether the movie object is reduced.
         */
        @Key
        public boolean isReduced;

        // Only in full profile.

        /**
         * The movie tagline. Not present in reduced form.
         */
        @Key
        public String tagline;
        /**
         * The movie runtime. Not present in reduced form.
         */
        @Key
        public int runtime;
        /**
         * The movie budget. Not present in reduced form.
         */
        @Key
        public int budget;
        /**
         * The movie revenue. Not present in reduced form.
         */
        @Key
        public long revenue;
        /**
         * The movie homepage. Not present in reduced form.
         */
        @Key
        public URL homepage;
        /**
         * The movie trailer. Not present in reduced form.
         */
        @Key
        public URL trailer;
        /**
         * The movie cast. Not present in reduced form.
         */
        @Key
        public Set<CastInfo> cast = new LinkedHashSet<CastInfo>();
        /**
         * The movie Genres. Not present in reduced form.
         */
        @Key
        public Set<Genre> genres = new LinkedHashSet<Genre>();
        /**
         * 
         * The movie Studios. Not present in reduced form.
         */
        @Key
        public Set<Studio> studios = new LinkedHashSet<Studio>();
        /**
         * The movie Countries. Not present in reduced form.
         */
        @Key
        public Set<Country> countries = new LinkedHashSet<Country>(); 
}
