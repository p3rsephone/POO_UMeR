import java.awt.geom.Point2D;
import java.util.Date;

/**
 * Write a description of class Human here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class User {

    /** Variáveis de Instância */

    private String email;
	private String name;
	private String password;
	private String address;
	private Date birthday;
	private Point2D.Float position;


    /** Construtores */

	/**
	 * Constrói um novo user a partir dos diferentes parametros fornecidos
	 * @param email email
	 * @param name 	nome
	 * @param pass	password
	 * @param addr 	morada
	 * @param birth Dia de nascimento
	 * @param position Posição
	 */
	public User(String email, String name, String password, String address, Date birthday, Point2D.Float position){
		this.email = email;
		this.name = name;
		this.password = password;
		this.address = address;
		this.birthday = new Date(birthday.getDate());
		this.position.setLocation(position.getX(), position.getY());
	}

	/**
	 * Constroi um user a partir de um já definido
	 * @param p
	 */
	public User(User p){
		this(p.getEmail(), p.getName(), p.getPassword(), p.getAddress(), p.getBirthday(), p.getPosition());
	}

	/**
	 * Constroi um user sem parametros (tudo a null e 0,0)
	 */
	public User(){
		this.email = null;
		this.name = null;
		this.password = null;
		this.address = null;
		this.birthday = new Date(null);
		this.position = new Point2D.Float();
		this.position.setLocation(0,0);
	}


    /** Metodos de Intância */

	/**
	 * Retorna o email de um user
	 * @return email
	 */
	public String getEmail(){
 		return this.email;
 	}

	/**
	 * Retorna o nome de um user
	 * @return name
	 */
 	public String getName(){
 		return this.name;
 	}

	/**
	 * Retorna o password de um user
	 * @return password
	 */
 	public String getPassword(){
 		return this.password;
 	}

	/**
	 * Retorna a morada de um user
	 * @return address
	 */
 	public String getAddress(){
 		return this.address;
 	}

	/**
	 * Retorna a data de aniversário de um user
	 * @return birthday
	 */
 	public Date getBirthday(){
 		return new Date(this.birthday.getDate());
 	}

	/**
	 * Retorna a posição de um user
	 * @return position
	 */
	public Point2D.Float getPosition(){
 		return this.position;
	}

	/**
	 * Altera o email de um user
	 * @param email Novo email
	 */
	public void setEmail(String email){
 		this.email = email;
	}

	/**
	 * Altera o nome de um user
	 * @param name Novo nome
	 */
	public void setName(String name){
 		this.name = name;
	}

	/**
	 * Altera a password de um user
	 * @param password Nova password
	 */
	public void setPassword(String password){
		this.password = password;
	}

	/**
	 * Altera a morada de um user
	 * @param address Novo morada
	 */
	public void setAddress(String address){
		this.address = address;
	}

	/**
	 * Altera o aniversário de um user
	 * @param birthday Novo aniversário
	 */
	public void setBirthday(Date birthday){
		this.birthday = new Date(birthday.getDate());
	}

	/**
	 * Altera a posição de um user
	 * @param position Nova posição
	 */
	public void setPosition(Point2D.Float position){
		this.position = position;
	}


	/**
	 * toString de um user
	 * @return String
	 */
	public String toString(){
		return 	"Utilizador - " 		+ this.name 		+ "\n" +
				"eMail : " 				+ this.email 		+ "\n" +
				"Password : " 			+ this.password 	+ "\n" +
				"Morada : " 			+ this.address 		+ "\n" +
				"Data de nascimento : " + this.birthday 	+ "\n" +
				"Posição : " 			+ this.position 	+ "\n";
	}

	/**
	 * Compara dois utilizadores
	 * @param u utilizador
	 * @return É o mesmo utilizador (true) ou não (false)
	 */
	public boolean equals(User user){
		return this.email.equals(user.getEmail());
	}

	/**
	 * Faz a cópia de um user
	 * @return Cópia de User
	 */
	public User clone(){
		return new User(this);
	}

}
