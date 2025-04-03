package ch07.unit07;

import java.text.DecimalFormat;

public class Ex03_DecimalFormat {

	public static void main(String[] args) {
		String s;

		// DecimalFormat : 패턴형식으로 숫자를 문자열로 변환
		DecimalFormat df = new DecimalFormat("#,##0");
		s = df.format(12345.536895); // 12,346
		System.out.println(s);

		s = df.format(3); // 3
		System.out.println(s);

		DecimalFormat df2 = new DecimalFormat("#,###.##");
		s = df2.format(12345.678);// 12,345.68
		System.out.println(s); 
		s = df2.format(12345); // 12,345
		System.out.println(s);
		s = df2.format(0);
		System.out.println(s); // 0
		s = df2.format(0.5);
		System.out.println(s); // 0.5
		System.out.println();

		DecimalFormat df3 = new DecimalFormat("0.##");
		s = df3.format(12345.678); // 12,345.68
		System.out.println(s);
		s = df3.format(0.5); // 0.5
		System.out.println(s);
		s = df3.format(0.0); // 0
		System.out.println(s);
		System.out.println();

		DecimalFormat df4 = new DecimalFormat("#,##0.0#");
		s = df4.format(12345.678); // 12,345.68
		System.out.println(s);
		s = df4.format(3.0); // 3.0
		System.out.println(s);
		s = df4.format(0.0); // 0.0
		System.out.println(s);
		System.out.println();

		DecimalFormat df5 = new DecimalFormat("#,###.0#");
		s = df5.format(0.0); // .0
		System.out.println(s);
		System.out.println();

		DecimalFormat df6 = new DecimalFormat("000.##");
		s = df6.format(1234.457); // 1234.46
		System.out.println(s);
		s = df6.format(1234.4); // 1234.4
		System.out.println(s);
		s = df6.format(5); // 005
		System.out.println(s);
		s = df6.format(0.5); // 000.5
		System.out.println(s);

	}

}
