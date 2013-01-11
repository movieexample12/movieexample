package com.listmovie.movies;

import android.os.AsyncTask;

public abstract class AbstractTask<T1, T2, T3> extends AsyncTask<T1, T2, T3>{

	public Boolean taskEnded = Boolean.FALSE;

}
