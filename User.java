package Ch06.di;

public class User {
	private UserVO[] list;
	private int count;

	public User() {
		count = 0;
		list = new UserVO[10];
	}

	public int append(UserVO vo) {
		if (count >= list.length) {
			System.out.println("등록 불가!");
			return count;
		}

		list[count++] = vo;

		return count;
	}

	public int getCount() {
		return count;
	}

	public UserVO[] listUser() {
		return list;
	}

}
