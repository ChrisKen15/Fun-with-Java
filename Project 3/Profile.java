package project3;

/**
 * Profile class that tracks the name of an account holder.
 */
public class Profile {
	private String fname;	// first name
	private String lname;	// last name
	
	/**
	 * Creates a profile of a person for an account.
	 * @param first_name of person
	 * @param last_name of person
	 */
	public Profile (String first_name, String last_name) {
		fname = first_name;
		lname = last_name;
	}
	
	/**
	 * Gets a string representation of the profile's first name and last name.
	 * @return first name and last name
	 */
	public String getName() {
		String name = "";
		name = name.concat(fname);
		name = name.concat(" ");
		name = name.concat(lname);
		return name;
	}
	
	/**
	 * Gets a string representation of the profile's last name.
	 * @return last name
	 */
	public String gLName() {
		return this.lname;
	}
	
	/**
	 * Gets a string representation of the profile's first name.
	 * @return first name
	 */
	public String gFName() {
		return this.fname;
	}
}
