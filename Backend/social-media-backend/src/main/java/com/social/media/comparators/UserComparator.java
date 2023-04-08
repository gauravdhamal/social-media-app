package com.social.media.comparators;

import java.time.LocalDateTime;
import java.util.Comparator;

import com.social.media.models.User;

public class UserComparator implements Comparator<User> {

	@Override
	public int compare(User user1, User user2) {
		if (user1.getPosts().size() > user2.getPosts().size()) {
			return -1;
		} else if (user1.getPosts().size() < user2.getPosts().size()) {
			return 1;
		} else {
			LocalDateTime localDateTime1 = user1.getCreated_at();
			LocalDateTime localDateTime2 = user2.getCreated_at();
			int result = localDateTime1.compareTo(localDateTime2);
			if (result < 0) {
				return -1;
			} else if (result > 0) {
				return 1;
			} else {
				return 0;
			}
		}
	}

}
