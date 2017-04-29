
/**
 * Write a description of class Human here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public abstract class Human {
    // Instance variables
    private String email;
	private String name;
	private String password;
	private String address;
	private String birthday;

    /**
     * Constructors
     */

	public Human(String mail, String name, String pass, String addr, String birth) {
		this.email = mail;
		this.name = name;
		this.password = pass;
		this.address = addr;
		this.birthday = birth;
	}

	public Human(Human p) {
		this(p.getMail(), p.getName(), p.getPassword(), p.getAddress(), p.getBirthday());
	}

    /**
	 * Getters
	 */

	public String getMail() {
 		return this.email;
 	}

 	public String getName() {
 		return this.name;
 	}

 	public String getPassword() {
 		return this.password;
 	}

 	public String getAddress() {
 		return this.address;
 	}

 	public String getBirthday() {
 		return this.birthday;
 	}

}
