package test;

public class MyBuggy {

	String codeGr = "gr";

	public Integer operation(int i1, int i2, String type) {

		MyBuggy myinst = new MyBuggy();
		if ("*".equals(type)) {
			return i1 * i2;

		}

		if ("+".equals(type)) {
			return i1 + i2;

		}

		if ("-".equals(type)) {
			return i1 - i2;

		}

		if ("gr".toUpperCase().equals(type)) {

			if (i2 > i1)
				return i2;

			if (i1 >= i2)
				return i1;

		}
		if ("absmult".equals(type)) {
			// Returns the multiplication of two positive values
			return myinst.toPositive(i1) * myinst.toPositive(i2);// Buggy method invocation
		}

		return null;
	}

	public int toPositive(int n) {
		if (n >= 0)
			return n;
		else
			return n * -1;

	}

	public int toNegative(int n) {
		if (n <= 0)
			return n;
		else
			return n * -1;

	}

}
