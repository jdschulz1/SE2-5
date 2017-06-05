package model;
/**
 * A general user of the software system for courier tracking at ACME.
 */
public class User {

	public User(String name, String userName, String password, String role, String email){
		this.setName(name);
		this.setUserName(userName);
		this.setPassword(password);
		this.role = role;
		this.setEmail(email);
	}
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
	 * Gets the assigned role for the current User.
	 * @return
	 */
	public String getRole(){
		return this.role;
	}
	
	/**
	 * Sets the role for the current User.
	 * @param role
	 */
	public void setRole(String role){
		this.role = role;
	}
	
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}