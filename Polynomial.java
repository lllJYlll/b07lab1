class Polynomial{
	private double[] coefficients;
	
	public Polynomial() {
		coefficients = new double[] {0};
	}
	
	public Polynomial(double[] coefficients) {
		this.coefficients = new double[coefficients.length];
		for (int i=0; i<coefficients.length; i++) {
			this.coefficients[i]=coefficients[i];
		}
	}
	public Polynomial add(Polynomial other) {
		int len = Math.max(this.coefficients.length, other.coefficients.length);
		double[] result = new double[len];
		
		for (int i=0; i<this.coefficients.length; i++) {
			result[i] = this.coefficients[i];
		}
		
		for (int i=0; i<other.coefficients.length; i++) {
			result[i] += other.coefficients[i];
		}
		
		return new Polynomial(result);
	}
	
	public double evaluate(double value) {
		double result = 0;
		
		for (int i=0; i<coefficients.length; i++) {
			result += coefficients[i] * Math.pow(value, i);
		}
		
		return result;
	}
	public boolean hasRoot(double value) {
		return evaluate(value) == 0;
	}
}