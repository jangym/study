package ch07.unit08;

import java.util.Arrays;
import java.util.Collections;

public class Ex01_Arrays {

	public static void main(String[] args) {
		// Arrays : 배열에 관련된 다양한 메소드 제공
		String s;
		String[] ss = new String[] { "서울", "부산", "대구", "인천", "광주", "대전", "울산", "세종" };

		s = Arrays.toString(ss);
		// 배열의 각 요소를 "[값1, 값2, ..., 값n]" 형식의 문자열로 반환
		System.out.println(s);

		// 오름차순 정렬
		Arrays.sort(ss);
		s = Arrays.toString(ss);
		System.out.println("오름차순 정렬: " + s);
		
		// 내림차순 정렬
		Arrays.sort(ss, Collections.reverseOrder());
		s = Arrays.toString(ss);
		System.out.println("내림차순 정렬: " + s);
		
		// Object[] oo = {"자바", 60, 90, 80};
		// Arrays.sort(oo); // 런타임 오류
			// 숫자나 문자열과 같이 다른 자료형으로 구성된 배열은 정렬 불가
	}

}
