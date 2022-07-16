package com.devsuperior.dscatalog.entities;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "tb_product")
public class Product implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	
	@Column(columnDefinition = "TEXT")
	private String description;
	private Double price;
	private String imgUrl;
	
	@Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE") //Define que será armazenado a data no padrão UTC
	private Instant date;
	
	@ManyToMany //Mapeamento da associação de PRODUTOS com suas CATEGORIAS muitos para muitos N-N se fosse muitos para um N-1 Utilizaria a Annotations ManyToOne e a configuraç~~ao da chave estrangeira seria @JoinColumn(name = "nome da coluna que sera a chave estrangeira")
	@JoinTable(name = "tb_product_category", //Essa Annotation JoinTable cria a tabela que FAZ a associação dos produtos com suas categorias, por meio da CRIAÇÃO de uma outra tabela armezenando o id das duas entidades
			joinColumns = @JoinColumn(name = "product_id"), //JoinColumns estabele qual a chave estrangeira relacionada com a classe atual PRODUCT
			inverseJoinColumns = @JoinColumn(name = "category_id")) //inverseJoinColumns estabelece a outra chave estrangeira que faz referencia de muitos para muitos N-N, ela identifica qual a entidade que terá essa associação pelo tipo definido na coleção SET
	private Set<Category> categories = new HashSet<>(); //Utilizado a coleção SET porque ela não aceita repetições

	public Product() {
	}
	
	public Product(Long id, String name, String description, Double price, String imgUrl, Instant date) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.price = price;
		this.imgUrl = imgUrl;
		this.date = date;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public Instant getDate() {
		return date;
	}

	public void setDate(Instant date) {
		this.date = date;
	}

	public Set<Category> getCategories() {
		return categories;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Product other = (Product) obj;
		return Objects.equals(id, other.id);
	}
}
