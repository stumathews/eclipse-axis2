/**
 * 
 */
package DAL.DEL;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Stuart
 *
 */
@Entity
@Table( name = "Product")
public class Product {
	private String name;
	private Long id;
	
	public Product(){}
	public Product(String name){
		setName(name);
	}
	
	@Column(name = "name")
	public String getName(){
		return this.name;
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getId(){
		return this.id;
		
	}
	private void setId(Long id){
		this.id = id;
	}
	
	
}
