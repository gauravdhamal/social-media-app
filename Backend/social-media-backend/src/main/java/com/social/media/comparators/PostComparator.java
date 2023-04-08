package com.social.media.comparators;

import java.util.Comparator;

import com.social.media.models.Post;

public class PostComparator implements Comparator<Post> {

	@Override
	public int compare(Post post1, Post post2) {
		if (post1.getLikes() > post2.getLikes()) {
			return -1;
		} else if (post1.getLikes() < post2.getLikes()) {
			return 1;
		} else {
			return 0;
		}
	}

}
