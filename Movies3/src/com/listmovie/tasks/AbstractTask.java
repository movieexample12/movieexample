package com.listmovie.tasks;

import android.os.AsyncTask;

public abstract class AbstractTask<T1, T2, T3> extends AsyncTask<T1, T2, T3>{

	public Boolean taskEnded = Boolean.FALSE;
	@Override
	protected void onPostExecute(T3 result) {
		taskEnded = Boolean.TRUE;
		super.onPostExecute(result);
	}
	
}
