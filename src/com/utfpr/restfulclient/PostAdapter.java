package com.utfpr.restfulclient;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.utfpr.restfulclient.model.Post;

public class PostAdapter extends ArrayAdapter<Post> {

	private List<Post> posts;
	private final Context context;

	public PostAdapter(Context context, int textViewResourceId,
			List<Post> objects) {
		super(context, textViewResourceId, objects);
		this.context = context;
		this.posts = objects;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		View post_view = convertView;

		if (post_view == null) {
			LayoutInflater infl = (LayoutInflater) context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			post_view = infl.inflate(R.layout.posts_adapter, parent);

			ViewHolder holder = new ViewHolder();
			holder.title = (TextView) post_view.findViewById(R.id.tvTitle);
			holder.date = (TextView) post_view.findViewById(R.id.tvDate);
			holder.excerpt = (TextView) post_view.findViewById(R.id.tvExcerpt);

			post_view.setTag(holder);
		}

		ViewHolder holder = (ViewHolder) post_view.getTag();
		Post post = posts.get(position);
		holder.title.setText(post.getTitle());
		holder.excerpt.setText(post.getExcerpt());
		// TODO holder.date.setText(post.getDate());

		return post_view;
	}

	public class ViewHolder {
		TextView title;
		TextView date;
		TextView excerpt;
		int position;
	}
}
