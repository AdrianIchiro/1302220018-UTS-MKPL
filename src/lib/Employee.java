package lib;

import java.time.LocalDate;
import java.time.Month;
import java.util.LinkedList;
import java.util.List;

public class Employee {
	
	public enum Gender {
		MALE, FEMALE
	}
	
	private static final int BASE_SALARY_GRADE_1 = 3000000;
	private static final int BASE_SALARY_GRADE_2 = 5000000;
	private static final int BASE_SALARY_GRADE_3 = 7000000;
	private static final int FOREIGNER_SALARY = 4500000;
	private static final int FULL_YEAR_MONTH = 12;

	private String employeeId;
	private String firstName;
	private String lastName;
	private String idNumber;
	private String address;
	
	private int yearJoined;
	private int monthJoined;
	private int dayJoined;
	private int monthWorkingInYear;
	
	private boolean isForeigner;
	private Gender gender;
	
	private int monthlySalary;
	private int otherMonthlyIncome;
	private int annualDeductible;
	
	private String spouseName;
	private String spouseIdNumber;

	private List<String> childNames;
	private List<String> childIdNumbers;
	
	public Employee(String employeeId, String firstName, String lastName, String idNumber, String address, int yearJoined, int monthJoined, int dayJoined, boolean isForeigner, Gender gender) {
		this.employeeId = employeeId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.idNumber = idNumber;
		this.address = address;
		this.yearJoined = yearJoined;
		this.monthJoined = monthJoined;
		this.dayJoined = dayJoined;
		this.isForeigner = isForeigner;
		this.gender = gender;
		
		childNames = new LinkedList<String>();
		childIdNumbers = new LinkedList<String>();
	}
	
	/**
	 * Fungsi untuk menentukan gaji bulanan pegawai berdasarkan grade kepegawaiannya (grade 1: 3.000.000 per bulan, grade 2: 5.000.000 per bulan, grade 3: 7.000.000 per bulan)
	 * Jika pegawai adalah warga negara asing gaji bulanan diperbesar sebanyak 50%
	 */
	
	public void setMonthlySalary(int grade) {
		switch (grade) {
			case 1:
				this.monthlySalary = 3000000;
				break;
			case 2:
				this.monthlySalary = 5000000;
				break;
			case 3:
				this.monthlySalary = 7000000;
				break;
			default:
				this.monthlySalary = 0;
		}

		if (this.isForeigner) {
			this.monthlySalary = 4500000;
		}
	}
	
	public void setAnnualDeductible(int deductible) {	
		this.annualDeductible = deductible;
	}
	
	public void setAdditionalIncome(int income) {	
		this.otherMonthlyIncome = income;
	}
	
	public void setSpouse(String spouseName, String spouseIdNumber) {
		this.spouseName = spouseName;
		this.spouseIdNumber = idNumber;
	}
	
	public void addChild(String childName, String childIdNumber) {
		childNames.add(childName);
		childIdNumbers.add(childIdNumber);
	}
	
	public int getAnnualIncomeTax() {
		private int calculateMonthsWorkedInYear() {
		LocalDate current = LocalDate.now();
		if (current.getYear() == this.yearJoined) {
			return current.getMonthValue() - this.monthJoined;
		}
		return 12;
	}

	public int getAnnualIncomeTax() {
		this.monthWorkingInYear = calculateMonthsWorkedInYear();
		return TaxFunction.calculateTax(
			this.monthlySalary,
			this.otherMonthlyIncome,
			this.monthWorkingInYear,
			this.annualDeductible,
			this.spouseIdNumber.equals(""),
			this.childIdNumbers.size()
		);
	}
	
	public class Child {
		private String name;
		private String idNumber;

		public Child(String name, String idNumber) {
			this.name = name;
			this.idNumber = idNumber;
		}
	}
}
