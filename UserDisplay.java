package Ch06.di;

public class UserDisplay {
	private User user;

	// 인자가 하나인 생성자만 존재
	public UserDisplay(User user) {
		this.user = user;
	}

	public void printAll() {
		UserVO[] list = user.listUser();

		System.out.println("이름\t나이\t결과");
		System.out.println("--------------------");
		for (int i = 0; i < user.getCount(); i++) {
			System.out.print(list[i].getName() + "\t");
			System.out.print(list[i].getAge() + "\t");
			System.out.println(list[i].getAdult());
		}
		System.out.println();
	}

}
