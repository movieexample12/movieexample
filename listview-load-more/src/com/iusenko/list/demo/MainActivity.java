package com.iusenko.list.demo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import com.example.listview_load_more.R;

import android.app.ListActivity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends ListActivity implements OnScrollListener {
	private static final String TAG = MainActivity.class.getSimpleName();

	private ListView list;
	private StringAdapter adapter;
	private View footer;
	private LoadMoreAsyncTask loadingTask = new LoadMoreAsyncTask();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		adapter = new StringAdapter();
		footer = getLayoutInflater().inflate(R.layout.list_view_footer, null);

		list = getListView();
		list.addFooterView(footer); // it's important to call 'addFooter' before 'setAdapter'
		list.setAdapter(adapter);
		list.setOnScrollListener(this);

		loadingTask.execute(0);
	}

	public Collection<String> generate(int startIndex, int count) {
		List<String> l = new ArrayList<String>(count);
		for (int i = 0; i < count; i++) {
			l.add("Item " + (startIndex + i));
		}
		return l;
	}

	@Override
	public void onScrollStateChanged(AbsListView view, int scrollState) {
	}

	@Override
	public void onScroll(AbsListView view, int firstVisible, int visibleCount, int totalCount) {
		boolean loadMore = firstVisible + visibleCount >= totalCount;

		if (loadMore && loadingTask.getStatus() == AsyncTask.Status.FINISHED) {
			loadingTask = new LoadMoreAsyncTask();
			loadingTask.execute(totalCount);
		}
	}

	private class StringAdapter extends ArrayAdapter<String> {
		private List<String> data = new ArrayList<String>();

		public StringAdapter() {
			super(MainActivity.this, android.R.layout.simple_list_item_1, new String[0]);
		}

		public void add(Collection<String> data) {
			this.data.addAll(data);
		}

		@Override
		public int getCount() {
			return data.size();
		}

		@Override
		public int getPosition(String item) {
			return data.indexOf(item);
		}

		@Override
		public String getItem(int position) {
			return data.get(position);
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			TextView view = (TextView) super.getView(position, convertView, parent);
			view.setText(data.get(position));
			view.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_launcher, 0, 0, 0);
			return view;
		}
	}

	private class LoadMoreAsyncTask extends AsyncTask<Integer, Void, Collection<String>> {
		@SuppressWarnings("unchecked")
		@Override
		protected Collection<String> doInBackground(Integer... params) {
			try {
				Thread.sleep(1000);
				Collection<String> data = generate(params[0].intValue(), 20);
				return data;
			} catch (Exception e) {
				Log.e(TAG, "Loading data", e);
			}
			return Collections.EMPTY_LIST;
		}

		@Override
		protected void onPostExecute(Collection<String> data) {
			if (data.isEmpty()) {
				Toast.makeText(MainActivity.this, getString(R.string.error_loading_items), Toast.LENGTH_SHORT).show();
				return;
			}

			adapter.add(data);
			adapter.notifyDataSetChanged();
			int index = list.getFirstVisiblePosition();
			int top = (list.getChildAt(0) == null) ? 0 : list.getChildAt(0).getTop();
			list.setSelectionFromTop(index, top);
		}
	}
}