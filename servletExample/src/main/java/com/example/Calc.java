package com.example;

public class Calc {

		// 계산 결과 보관을 위한 변수, 초기값 0
		private int result;
		
		public Calc() {
			result = 0;
		}
		
		public Calc(int num1, int num2, String op) {
			// 연산자에 따른 계산 수행
			if(op.equals("+")) {
				result = num1 + num2;
			} else if (op.equals("-")){
				result = num1 - num2;
			} else if (op.equals("x")) {
				result = num1 * num2;
			} else if (op.equals("/")) {
				if (num2!=0)
					result = num1 / num2;
				else 
					result = 0;
			}
		}
		
		// 결과 변수를 리턴하는 메서드
		public int getResult() {
			return result;
		

	}

}
