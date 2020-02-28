package com.springbootrestJWT.dto;

import java.io.Serializable;

// TODO: Auto-generated Javadoc
/**
 * The Class PersonneDto.
 */
public class PersonneDto implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The num. */
	private Long num;
	
	/** The nom. */
	private String nom;
	
	/** The prenom. */
	private String prenom;

	/**
	 * Gets the num.
	 *
	 * @return the num
	 */
	public Long getNum() {
		return num;
	}

	/**
	 * Sets the num.
	 *
	 * @param num the new num
	 */
	public void setNum(Long num) {
		this.num = num;
	}

	/**
	 * Gets the nom.
	 *
	 * @return the nom
	 */
	public String getNom() {
		return nom;
	}

	/**
	 * Sets the nom.
	 *
	 * @param nom the new nom
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}

	/**
	 * Gets the prenom.
	 *
	 * @return the prenom
	 */
	public String getPrenom() {
		return prenom;
	}

	/**
	 * Sets the prenom.
	 *
	 * @param prenom the new prenom
	 */
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	/**
	 * Instantiates a new personne dto.
	 *
	 * @param num the num
	 * @param nom the nom
	 * @param prenom the prenom
	 */
	public PersonneDto(Long num, String nom, String prenom) {
		super();
		this.num = num;
		this.nom = nom;
		this.prenom = prenom;
	}

	/**
	 * Instantiates a new personne dto.
	 *
	 * @param nom the nom
	 * @param prenom the prenom
	 */
	public PersonneDto(String nom, String prenom) {
		super();
		this.nom = nom;
		this.prenom = prenom;
	}

	/**
	 * Instantiates a new personne dto.
	 *
	 * @param num the num
	 * @param prenom the prenom
	 */
	public PersonneDto(Long num, String prenom) {
		super();
		this.num = num;
		this.prenom = prenom;
	}

	/**
	 * To string.
	 *
	 * @return the string
	 */
	@Override
	public String toString() {
		return "PersonneDto [" + num + "] nom=" + nom + ", prenom=" + prenom + "]";
	}

}
