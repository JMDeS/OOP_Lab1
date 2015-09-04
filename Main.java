package lab1;

import java.util.Scanner;

public class Main {
	

	public static void main(String args[]) {
		HugeInteger h1, h2;
		String num;
		Scanner scan = new Scanner(System.in);
		
		System.out.print("Please enter the first huge integer (h1): ");
		num = scan.nextLine();
		h1 = new HugeInteger(num);

		System.out.print("Please enter the second huge integer (h2): ");
		num = scan.nextLine();
		h2 = new HugeInteger(num);

		if (h1.isEqualTo(h2)) {
			System.out.println("h1 is equal to h2.");
		}
		if (h1.isNotEqualTo(h2)) {
			System.out.println("h1 is not equal to h2.");
		}
		if (h1.isGreaterThan(h2)) {
			System.out.println("h1 is greater than h2.");
		}
		if (h1.isLessThan(h2)) {
			System.out.println("h1 is less than to h2.");
		}
		if (h1.isGreaterThanOrEqualTo(h2)) {
			System.out.println("h1 is greater than or equal to h2.");
		}
		if (h1.isLessThanOrEqualTo(h2)) {
			System.out.println("h1 is less than or equal to h2.");
		}

		h1.add(h2);
		System.out.printf("h1+h2=%s\n", h1);
		h1.subtract(h2);
		h1.subtract(h2);
		System.out.printf("h1-h2=%s\n", h1);
/*		h1.add(h2);
		h1.multiply(h2);
		System.out.printf("h1*h2=%s\n", h1);
*/
	}
}
