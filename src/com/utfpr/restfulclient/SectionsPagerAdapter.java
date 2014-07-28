package com.utfpr.restfulclient;

import java.util.List;
import java.util.Locale;
import java.util.concurrent.ExecutionException;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v13.app.FragmentPagerAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.utfpr.restfulclient.helper.Callback;
import com.utfpr.restfulclient.helper.NetworkHandler;
import com.utfpr.restfulclient.model.Category;

public class SectionsPagerAdapter extends FragmentPagerAdapter {

	private List<Category> categories;

	public SectionsPagerAdapter(FragmentManager fm) {
		super(fm);
		try {
			getCategories();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			Log.i("constructor@SectionsPagerAdapter", e.getMessage());
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			Log.i("constructor@SectionsPagerAdapter", e.getMessage());
		}
		Log.i("constructor@sectionsPagerAdapter", "hai!");
	}

	private void getCategories() throws InterruptedException, ExecutionException {
		
		NetworkHandler rest = NetworkHandler.getInstance();
		rest.readList(StaticReferences.SERVER_ADDRESS + "categories",
				Category[].class, new Callback<List<Category>>() {

					@Override
					public void callback(List<Category> categories) {
						if (categories != null) {
							SectionsPagerAdapter.this.categories = categories;
							Log.i("callback - getCategories@SectionsPagerAdapter",
									SectionsPagerAdapter.this.categories
											.toString());
						}
					}
				});

	}

	@Override
	public Fragment getItem(int position) {
		// getItem is called to instantiate the fragment for the given page.
		// Return a PlaceholderFragment (defined as a static inner class
		// below).
		return PlaceholderFragment.newInstance(position + 1);
	}

	@Override
	public int getCount() {
		Log.i("getCount@SectionsPagerAdapter", "hai!");
		try {
			Log.i("getCount@SectionsPagerAdapter", categories.toString());
			return categories.size();
		} catch (Exception e) {
			return 3;
		}
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
	public static class PlaceholderFragment extends Fragment {
		/**
		 * The fragment argument representing the section number for this
		 * fragment.
		 */
		private static final String ARG_SECTION_NUMBER = "section_number";

		/**
		 * Returns a new instance of this fragment for the given section number.
		 */
		public static PlaceholderFragment newInstance(int sectionNumber) {
			PlaceholderFragment fragment = new PlaceholderFragment();
			Bundle args = new Bundle();
			args.putInt(ARG_SECTION_NUMBER, sectionNumber);
			fragment.setArguments(args);
			return fragment;
		}

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_main, container,
					false);
			return rootView;
		}
	}
}