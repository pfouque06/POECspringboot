package com.firstspringstartproject02.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.firstspringstartproject02.dto.PersonneDto;

// TODO: Auto-generated Javadoc
/**
 * The Class Personne.
 *
 * @author P. Fouque
 */
@Entity
@Table(name = "personnes")
public class Personne implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The num. */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long num;
	
	/** The nom. */
	@Column(name = "nom", nullable = false)
	private String nom;
	
	/** The prenom. */
	@Column(name = "prenom", nullable = false)
	private String prenom;

	/**
	 * Instantiates a new personne.
	 */
	public Personne() {
	}
	
	/**
	 * Instantiates a new personne.
	 *
	 * @param personneDto the personne dto
	 */
	public Personne(PersonneDto personneDto) {
        this.setNum(personneDto.getNum());
        this.setNom(personneDto.getNom());
        this.setPrenom(personneDto.getPrenom());
        
    }

	/**
	 * Instantiates a new personne.
	 *
	 * @param num the num
	 * @param nom the nom
	 * @param prenom the prenom
	 */
	public Personne(Long num, String nom, String prenom) {
		super();
		this.num = num;
		this.nom = nom;
		this.prenom = prenom;
	}

	/**
	 * Instantiates a new personne.
	 *
	 * @param nom the nom
	 * @param prenom the prenom
	 */
	public Personne(String nom, String prenom) {
		this.nom = nom;
		this.prenom = prenom;
	}

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
	 * To string.
	 *
	 * @return the string
	 */
	@Override
	public String toString() {
		return "Personne [num=" + num + ", nom=" + nom + ", prenom=" + prenom + "]";
	}

	/**
	 * Hash code.
	 *
	 * @return the int
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nom == null) ? 0 : nom.hashCode());
		result = prime * result + ((num == null) ? 0 : num.hashCode());
		result = prime * result + ((prenom == null) ? 0 : prenom.hashCode());
		return result;
	}

	/**
	 * Equals.
	 *
	 * @param obj the obj
	 * @return true, if successful
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Personne other = (Personne) obj;
		if (nom == null) {
			if (other.nom != null)
				return false;
		} else if (!nom.equals(other.nom))
			return false;
		if (num == null) {
			if (other.num != null)
				return false;
		} else if (!num.equals(other.num))
			return false;
		if (prenom == null) {
			if (other.prenom != null)
				return false;
		} else if (!prenom.equals(other.prenom))
			return false;
		return true;
	}
	
	

}
