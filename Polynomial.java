package lab1;
import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;

class Polynomial{
	private double[] coefficients;
	private int[] exponents;
	
	public Polynomial() {
		coefficients = new double[] {0};
		exponents = new int[] {0};
	}
	
	public Polynomial(double[] coefficients, int[] exponents) {
		this.coefficients = coefficients;
		this.exponents = exponents;
	}
	
	public Polynomial(File file) throws Exception {
		BufferedReader reader = new BufferedReader(new FileReader(file));
		String line = reader.readLine();
		reader.close();
	
		double[] resultCoefficients = new double[line.length()];
		int[] resultExponents = new int[line.length()];
		
		String[] term = line.split("\\+");
		int count = 0;
		
		for (int i=0; i<term.length; i++) {
			String[] terms =term[i].split("-");
			String[] firstTerm = terms[0].split("x");
			if (firstTerm.length == 0) {
				resultCoefficients[count] = Double.parseDouble(firstTerm[0]);
				resultExponents[count] = 0;
			}
			else {
				resultCoefficients[count] = Double.parseDouble(firstTerm[0]);
				resultExponents[count] = Integer.parseInt(firstTerm[1]);
			}
			count++;
			for (int j=1; j<terms.length; j++) {
				String[] aTerm = terms[j].split("x");
				resultCoefficients[count] = -1.0 * Double.parseDouble(aTerm[0]);
				resultExponents[count] = Integer.parseInt(aTerm[1]);
				count++;
			}
		}
		coefficients = new double[count];
		exponents = new int[count];
		
		for (int i=0; i<count; i++) {
			coefficients[i]=resultCoefficients[i];
			exponents[i]=resultExponents[i];
		}
		
	}
	
	public Polynomial add(Polynomial other) {
		double[] resultCoefficients = new double[coefficients.length + other.coefficients.length];
		int[] resultExponents = new int[coefficients.length + other.coefficients.length];
		
		int i=0, j=0, k=0;
		
		while(i<coefficients.length && j< other.coefficients.length) {
			if(exponents[i]==other.exponents[j]) {
				resultCoefficients[k] = coefficients[i] + other.coefficients[j];
				resultExponents[k] = exponents[i] + other.exponents[j];
				i++;
				j++;
			}
			else if(exponents[i]<other.exponents[j]) {
				resultCoefficients[k] = coefficients[i];
				resultExponents[k] = exponents[i];
				i++;
			}
			else {
				resultCoefficients[k] = other.coefficients[j];
				resultExponents[k] = other.exponents[j];
				j++;
			}
			k++;
		}
		

		while(i<coefficients.length) {
			resultCoefficients[k] = coefficients[i];
			resultExponents[k] = exponents[i];
			i++;
			k++;
		}
		
		while(i<other.coefficients.length) {
			resultCoefficients[k] = other.coefficients[i];
			resultExponents[k] = other.exponents[i];
			j++;
			k++;
		}
		double[] resultCoefficientsb = new double[k];
		int[] resultExponentsb = new int[k];
		
		for (i=0; i<k; i++) {
			resultCoefficientsb[i]=resultCoefficients[i];
			resultExponentsb[i]=resultExponents[i];
		}
		
		return new Polynomial(resultCoefficientsb, resultExponentsb);
	}
	
	public double evaluate(double value) {
		double result = 0;
		
		for (int i=0; i<coefficients.length; i++) {
			result += coefficients[i] * Math.pow(value, exponents[i]);
		}
		
		return result;
	}
	
	public boolean hasRoot(double value) {
		return evaluate(value) == 0;
	}
	
	public Polynomial multiply(Polynomial other) {
		double[] resultCoefficients = new double[coefficients.length + other.coefficients.length];
		int[] resultExponents = new int[coefficients.length + other.coefficients.length];
		
		int count =0;
		
		for (int i=0; i<coefficients.length; i++) {
			for (int j=0; j<other.coefficients.length; j++) {
				double coefficient = coefficients[i] * other.coefficients[j];
				int exponent = exponents[i]+ other.exponents[j];
				
				boolean found = false;
				for (int k=0; k<count; k++) {
					if (resultExponents[k]==exponent) {
						resultCoefficients[k]+=coefficient;
						found = true;
						break;
					}
				}
				if(!found) {
					resultCoefficients[count]=coefficient;
					resultExponents[count]=exponent;
					count++;
				}
			}
		}
		
		for(int i=0; i<count-1; i++) {
			for (int j=i+1; j<count; j++) {
				if(resultExponents[i]<resultExponents[j]) {
					int tempExponent = resultExponents[i];
					resultExponents[i] = resultExponents[j];
					resultExponents[j] = tempExponent;
					
					double tempCoefficients = resultCoefficients[i];
					resultCoefficients[i] = resultCoefficients[j];
					resultCoefficients[j] = tempCoefficients;
				}
			}
		}
		double[] resultCoefficientsb = new double[count];
		int[] resultExponentsb = new int[count];
		
		for (int i=0; i<count; i++) {
			resultCoefficientsb[i]=resultCoefficients[i];
			resultExponentsb[i]=resultExponents[i];
		}
		
		return new Polynomial(resultCoefficientsb, resultExponentsb);
	}
	
	public void saveToFile(String file) throws Exception{
		FileWriter writer = new FileWriter(file);
		for (int i=0; i<exponents.length; i++) {
			writer.write((int)coefficients[i]);
			if(exponents[i]>0) {
				writer.write('x');
				if (exponents[i]>1) {
					writer.write(exponents[i]);
				}
			}
		}
		writer.close();
	}
	
}