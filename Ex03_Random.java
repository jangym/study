package ch07.unit06;

import java.util.Random;

public class Ex03_Random {

	public static void main(String[] args) {
		String s;

		s = toNumber5();
		System.out.println("다섯자리 난수 : " + s);

		s = toNumber3();
		System.out.println("서로 다른 세 자리 정수 : " + s);

	}

	public static String toNumber5() {
		// 다섯 자리 난수 (00000 ~ 99999)
		Random rnd = new Random();

		return String.format("%05d", rnd.nextInt(100000));
	}

	public static String toNumber3() {
		// 서로 다른 세 자리 난수 (123 ~ 987)
		StringBuilder sb = new StringBuilder();

		Random rnd = new Random();
		String n;

		while (sb.length() < 3) {
			n = Integer.toString(rnd.nextInt(9) + 1);
			if (sb.indexOf(n) >= 0) {
				continue;
			}

			sb.append(n);
		}

		return sb.toString();
	}

}
