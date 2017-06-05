package model;
/**
 * A general user of the software system for courier tracking at ACME.
 */
public class User {

	/**
	 * The legal name of the user.
	 */
	private String name;
	/**
	 * User name for authentication to the Delivery Tracker.
	 */
	private String userName;
	/**
	 * Password for authentication to the Delivery Tracker.
	 */
	private String password;
	/**
	 * Role for the user of the Delivery Tracking sytem that determines permissions on the system. ?
	 */
	private String role;
	/**
	 * Email address for the User.
	 */
	private String email;

	/**
	 * Checks the password to the one stored for a user and if valid returns true.
	 */
	public boolean checkPassword() {
		// TODO - implement User.checkPassword
		throw new UnsupportedOperationException();
	}

	/**
	 * A user enters their old password and the new password and the password is set to the new password if and only if the old password is correct.
	 * @param old_pass The old password the User wishes to change.
	 * @param new_pass The new password the User wishes to change to.
	 */
	public boolean setPassword(String old_pass, String new_pass) {
		// TODO - implement User.setPassword
		throw new UnsupportedOperationException();
	}

}