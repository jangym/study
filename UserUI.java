package Ch06.di;

import java.util.Scanner;

public class UserUI {
	private Scanner sc = new Scanner(System.in);
	private User user = new User();
	private UserDisplay disp;

	public UserUI() {
		disp = new UserDisplay(user);

	}

	public void menu() {
		int ch;

		while (true) {
			do {
				System.out.println("1.입력 2.출력 3.종료 → ");
				ch = sc.nextInt();
			} while (ch < 1 || ch > 3);

			if (ch == 3) {
				return;
			}

			switch (ch) {
			case 1:
				input();
				break;
			case 2:
				print();
				break;
			}

		}
	}

	public void input() {
		System.out.println("\n데이터 입력");

		UserVO vo = new UserVO();
		System.out.print("이름? ");
		vo.setName(sc.next());
		System.out.println("나이? ");
		vo.setAge(sc.nextInt());

		user.append(vo);

		System.out.println("등록이 완료되었습니다.\n");
	}

	public void print() {
		System.out.println("\n데이터 출력");
		
		disp.printAll();
	}

}
