package utils;

public enum Weight {
	 	KILOGRAMME("Kg"),
		GRAMME("gr"),
		POUND("pnd");

	 	private final String value;

		Weight(String string) {
			this.value=string;
		}
		
		public String getValue() {
		        return value;
		 }

}
