package com.utfpr.restfulclient;

import java.util.List;
import java.util.Locale;
import java.util.concurrent.ExecutionException;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.ListFragment;
import android.support.v13.app.FragmentPagerAdapter;
import android.util.Log;

import com.utfpr.restfulclient.helper.Callback;
import com.utfpr.restfulclient.helper.NetworkHandler;
import com.utfpr.restfulclient.model.Category;
import com.utfpr.restfulclient.model.Post;

public class SectionsPagerAdapter extends FragmentPagerAdapter {

	private List<Category> categories;
	private MainActivity activity;

	public SectionsPagerAdapter(FragmentManager fm, MainActivity activity) {
		super(fm);
		this.activity = activity;
		try {
			getCategories();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Log.i("houston",
				"constructor@sectionsPagerAdapter: after getCategories()");
	}

	private void getCategories() throws InterruptedException,
			ExecutionException {

		NetworkHandler rest = NetworkHandler.getInstance();
		rest.syncReadList(StaticReferences.SERVER_ADDRESS + "categories",
				Category[].class, new Callback<List<Category>>() {

					@Override
					public void callback(List<Category> categories) {
						if (categories != null) {
							SectionsPagerAdapter.this.categories = categories;
							Log.i("houston",
									"callback - getCategories@SectionsPagerAdapter"
											+ SectionsPagerAdapter.this.categories
													.toString());

							SectionsPagerAdapter.this.notifyDataSetChanged();
							activity.updateTabs();
						}
					}
				});
	}

	@Override
	public Fragment getItem(int position) {
		// getItem is called to instantiate the fragment for the given page.
		// Return a PlaceholderFragment (defined as a static inner class
		// below).
		return PlaceholderFragment.newInstance(position + 1,
				categories.get(position));
	}

	@Override
	public int getCount() {
		if (categories != null) {
			Log.i("houston",
					"getCount@SectionsPagerAdapter: " + categories.toString());
			return categories.size();
		}
		Log.i("houston", "getCount@SectionsPagerAdapter");
		return 3;
	}

	@Override
	public CharSequence getPageTitle(int position) {
		Locale l = Locale.getDefault();
		try {
			return categories.get(position).getName().toUpperCase(l);
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends ListFragment {

		private static List<Post> posts;
		private static PlaceholderFragment fragment;
		private static final String ARG_SECTION_NUMBER = "section_number";

		public static PlaceholderFragment newInstance(int sectionNumber,
				Category category) {
			fragment = new PlaceholderFragment();

			try {
				getPosts(category);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ExecutionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			Log.i("houston",
					"newInstance@PlaceholderFragment: should be after setFragmentAdapter");
			// Log.i("houston",
			// "newInstance@PlaceholderFragment: should be after callback - getPosts");
			// Bundle args = new Bundle();
			// args.putInt(ARG_SECTION_NUMBER, sectionNumber);
			// fragment.setArguments(args);
			return fragment;
		}

		public PlaceholderFragment() {
		}

		private static void getPosts(Category category)
				throws InterruptedException, ExecutionException {
			NetworkHandler rest = NetworkHandler.getInstance();
			rest.syncReadList(StaticReferences.SERVER_ADDRESS + "categories/"
					+ category.getId() + "/posts", Post[].class,
					new Callback<List<Post>>() {

						@Override
						public void callback(List<Post> t) {
							posts = t;
							setFragmentAdapter();
							Log.i("houston",
									"callback - getPosts@PlaceholderFragment: "
											+ posts.size());
						}
					});
		}

		private static void setFragmentAdapter() {
			Log.i("houston",
					"setFragmentAdapter@PlaceholderFragment: should be after callback - getPosts");
		}
	}
}