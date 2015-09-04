package lab1;


// digits are placed in array 'digits[]' using method 4,
// the least significant digit (LSD) is at digits[NUM_DIGITS-1]
// negative sign '-' at digits[0]

public class HugeInteger {

	private static final int NUM_DIGITS = 40;
	private int digits[] = new int[NUM_DIGITS];
	private boolean positive;
	private int length;

	
	public HugeInteger(String num) {
		length = num.length();
		if ( num.charAt(0) == '-' ) {
			length--;
			positive = false;
		}else{
			positive = true;
		}
		
		for ( int i=0; i<length; i++ ) {
			digits[NUM_DIGITS-1-i] =
				num.charAt(num.length()-1-i) - (int)'0';
		}
	}
	
	public boolean isEqualTo(HugeInteger hi){
		
		boolean isEqualTo = false;

		if ( this.length == hi.length && 
				this.positive == hi.positive ) {
			
			for ( int i=0 ; i < this.length ; i++ ) {
				if ( this.digits[NUM_DIGITS-1-i] == hi.digits[NUM_DIGITS-1-i] ) {
					isEqualTo = true;
				} else {
					isEqualTo = false;
					i = this.length;
				} // end if
			} // end for
		} // end if

		return isEqualTo;
	}

	public boolean isNotEqualTo(HugeInteger hi) {
		return !this.isEqualTo(hi);
	}

	public boolean isGreaterThan(HugeInteger hi) {
		
		boolean isGreaterThan = false;
		
		if (this.length < hi.length) {
			isGreaterThan = false;
		} else if ( this.length > hi.length ) {
			isGreaterThan = true;
		} else if ( length == hi.length ) {
			for ( int i=length ; i > 0 ; i-- ) {
				if ( this.digits[NUM_DIGITS-i] >
					   hi.digits[NUM_DIGITS-i] ) {
					isGreaterThan = true;
					i = 0;
				}
			}
		}
		
		return isGreaterThan;
	}
	
	public boolean isLessThan(HugeInteger hi) {
		
		boolean isLessThan = false;
		
		if ( !this.isGreaterThan(hi) && !this.isEqualTo(hi) ) {
			isLessThan = true;
		}
		
		return isLessThan;
	}
	
	public boolean isGreaterThanOrEqualTo(HugeInteger hi) {
		boolean isGreaterThanOrEqualTo = false;
		
		if ( this.isGreaterThan(hi) || this.isEqualTo(hi) ) {
			isGreaterThanOrEqualTo = true;
		}
		
		return isGreaterThanOrEqualTo;
	}
	
	public boolean isLessThanOrEqualTo(HugeInteger hi) {
		boolean isLessThanOrEqualTo = false;
		
		if ( this.isLessThan(hi) || this.isEqualTo(hi) ) {
			isLessThanOrEqualTo = true;
		}
		
		return isLessThanOrEqualTo;
	}

	public String toString() {
		char charArray[] = null;
		
		if ( !positive ) {
			charArray = new char[length + 1];
			charArray[0] = '-';

			for ( int i=1 ; i < length+1 ; i++ ) {
				charArray[i] = (char)( (int)'0' + this.digits[NUM_DIGITS-1-length+i] );
			}
		} else if ( positive ) {
			charArray = new char[length];
			for ( int i=0 ; i < length ; i++ ) {
				charArray[i] = (char)( (int)'0' + this.digits[NUM_DIGITS-length+i] );
			}
		}
		if ( this.isZero() ) {
			charArray = new char[1];
			charArray[0] = '0';
		}
		String str = new String(charArray);
		return str;
	}

	public boolean sameSign(HugeInteger hi){
		if ( this.positive == hi.positive ) {
			return true;
		} else {
			return false;
		}
	}

	public void negate() {
		this.positive = !positive;
	}

	public boolean isZero() {
		boolean isZero = false;
		for (int i=0 ; i < NUM_DIGITS ; i++ ) {
			if ( digits[i] == 0 ) {
				isZero = true;
			} else {
				isZero = false;
				i = NUM_DIGITS;
			}
		}
		return isZero;
	}
	
	public void correctLength() {
		for (int i=0 ; i < NUM_DIGITS ; i++){
			if ( digits[i] != 0 && digits[i] != '-' ) {
				this.length = NUM_DIGITS - i;
				i = NUM_DIGITS;
			}
		}
	}

	private static int[] addArrayDigits( int[] array1, int[] array2 ) {
		
		for ( int i=0 ; i < NUM_DIGITS ; i++ ) {
			array1[NUM_DIGITS-1-i] += array2[NUM_DIGITS-1-i] ;
			if ( array1[NUM_DIGITS-1-i] > 9 ) {
				array1[NUM_DIGITS-1-i] -= 10;
				array1[NUM_DIGITS-2-i] += 1;
			}
		}
		return array1;
	}

	private static int[] subtractArrayDigits ( int[] array1 , int[] array2 ) {
		for ( int i=0 ; i < NUM_DIGITS ; i++ ) {
			array1[NUM_DIGITS-1-i] -= array2[NUM_DIGITS-1-i];
			if ( array1[NUM_DIGITS-1-i] < 0 ) {
				array1[NUM_DIGITS-1-i] += 10;
				array1[NUM_DIGITS-2-i] -= 1;
			}
		}
		return array1;
	}
	
	public void add(HugeInteger hi) {  // this += hi
		if ( this.isGreaterThan(hi) ) {	
			if ( this.sameSign(hi) ) {
				this.digits = addArrayDigits( this.digits , hi.digits );
			} else {
				this.digits = subtractArrayDigits( this.digits , hi.digits );
			}
		} else if ( this.isEqualTo(hi) ) {
				this.digits = addArrayDigits( this.digits , hi.digits );
		} else {
			if ( this.sameSign(hi) ) {
				this.digits = addArrayDigits( this.digits , hi.digits );
			} else {
				this.digits = subtractArrayDigits( hi.digits , this.digits );
				this.negate();
			}
		}
		this.correctLength();
		this.toString();
	}

	public void subtract(HugeInteger hi) {
		if ( this.isGreaterThan(hi) ) {
			if ( this.positive ) {
				this.digits = subtractArrayDigits ( this.digits , hi.digits );
			} else {
				this.digits = subtractArrayDigits( this.digits , hi.digits );
				this.negate();
			}
		} else if ( this.isLessThan(hi) ) {
			if ( this.positive ) {
				this.digits = subtractArrayDigits( hi.digits , this.digits );
				this.negate();
			} else {
				this.digits = subtractArrayDigits( this.digits , hi.digits );
			}
			this.correctLength();
			this.toString();
		}
	}
	
}

/*
// for calculation
public void add(HugeInteger hi){}// this+=hi
public void subtract(HugeInteger hi){}// this-=hi
public void multiply(HugeInteger hi){} // this*=hi(cannot call add or subtract)
public void negate(){}// positive=!positive
public boolean isZero(){}
public String toString(){} // the returned string does not include the leading zeros

*/
