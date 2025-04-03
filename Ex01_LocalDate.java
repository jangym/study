package ch07.unit09;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class Ex01_LocalDate {

	public static void main(String[] args) {
		// 시스템 날짜
		String s;

		LocalDate today = LocalDate.now();
		System.out.println("현재 날짜 : " + today);
		s = today.toString();
		System.err.println(s);

		// 날짜 설정 (2025-05-05)
		LocalDate targetDate = LocalDate.of(2025, 5, 5);
		// LocalDate targetDate = LocalDate.of(2025, 5, 32); // 런타임 오류
		// LocalDate targetDate = LocalDate.of(2025, 2, 29); // 런타임 오류
		System.out.println("설정 날짜 : " + targetDate);

		LocalTime currTime = LocalTime.now();
		System.out.println("현재 시간 : " + currTime);

		// 시간 설정
		LocalTime targetTime = LocalTime.of(6, 30, 0, 0);
		System.out.println("설정 시간 : " + targetTime);

		// 시스템 날짜와 시간
		LocalDateTime now = LocalDateTime.now();
		System.out.println(now);
		// 2025-04-03T17:05:54.342488700
		String[] ss = now.toString().split("T");
		System.out.println(ss[0] + " " + ss[1]);

		// 날짜와 시간 설정
		LocalDateTime targetDateTime = LocalDateTime.of(2025, 5, 5, 10, 10, 0, 0);
		System.out.println("설정 날짜 및 시간 : " + targetDateTime);
		// 초와 나노 초는 0 출력 불가
	}

}
