package za.co.fnb.common;

public enum Denomination {
	
	HUNDRED_RANDS("R100", "100"),
	FIFTY_RANDS("R50", "50"),
	TWENTY_RANDS("R20", "20"),
	TEN_RANDS("R10", "10"),
	FIVE_RANDS("R5", "5"),
	TWO_RANDS("R2", "2"),
	ONE_RANDS("R1", "1"),
	FIFTY_CENTS("50Cent", "0.5"),
	TWENTY_CENTS("20Cent", "0.2"),
	TEN_CENTS("10Cent", "0.1"),
	FIVE_CENTS("5Cent", "0.05");

	private String denominator;
	private String value;
	
	Denomination(String denominator, String value){
		this.denominator = denominator;
		this.value = value;
	}

	public String getDenominator() {
		return denominator;
	}

	public String getValue() {
		return value;
	}
}
