package ch07.unit06;

public class Ex01_Math {

	public static void main(String[] args) {
		double a;
		
		System.out.println(Math.PI);
		
		// sin 30 결과 (각도는 라디안)
		a=Math.sin(30.0 * Math.PI / 180);
		System.out.println(a);
		
		// 제곱근
		a=Math.sqrt(2.0);
		System.out.println(a);

		// x를 넘지 않는 최대 정수 (결과는 실수형)
		System.out.println(Math.floor(12.77)+", "+Math.floor(-12.77));
			// 12.0 - 13.0
		
		// 크거나 같은 수 중 가장 적은 정수
		System.out.println(Math.ceil(12.77)+", "+Math.ceil(-12.77));
			// 13.0, -12.0
		
		// 3의 5승
		a = Math.pow(3.0, 5.0);
		System.out.println(a);
		
		// 0 <= 난수 < 1
		a = Math.random();
		System.out.println(a);
		
		// 1~45 사이의 난수 
		int n = (int)(Math.random()*45)+1;
		System.out.println(n);
	}

}
