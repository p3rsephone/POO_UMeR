
/**
 * Write a description of class Pessoa here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Pessoa
{
    // Instance variables
    private String email;
	private String name;
	private String password;
	private String address;
	private String birthday;

    /**
     * Constructors
     */
    public Pessoa() {
        this("n/a", "n/a", "n/a", "n/a", "n/a");
    }

	public Pessoa(String mail, String nam, String pass, String addr, String birth) {
		this.email = mail;
		this.name = nam;
		this.password = pass;
		this.address = addr;
		this.birthday = birth;
	}

	public Pessoa(Pessoa p) {
		this(p.getMail(), p.getName(), p.getPassword(), p.getAddress(), p.getBirthday());
	}

    /**
	 * Getters
	 */
}
