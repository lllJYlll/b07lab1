package lab1;


public class Driver {
	public static void main(String [] args) {
		// CHANGE
		Polynomial p = new Polynomial();
		System.out.println(p.evaluate(3));
		Polynomial p1 = new Polynomial(new double[]{6, -2, 5}, new int[]{0, 1, 3});
		
		//Should print "s(2) = 9.0"
		System.out.println("p1(2) = " + p1.evaluate(1));
        Polynomial p2 = new Polynomial(new double[]{5, -5}, new int[]{0, 2});
		Polynomial s = p1.add(p2);
		
		//Should print "s(2) = 27"
		System.out.println("s(2) = " + s.evaluate(2));
		
		//Should print "1 is a root of s"
		if(p2.hasRoot(1))
			System.out.println("1 is a root of s");
		else 
			System.out.println("1 is not a root of s");

		//Should print "p1(1) * p2(1) = 0.0"
		Polynomial product = p1.multiply(p2);
		System.out.println("p1(1) * p2(1) = " + product.evaluate(1));
	}
}