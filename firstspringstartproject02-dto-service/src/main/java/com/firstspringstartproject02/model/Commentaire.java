package com.firstspringstartproject02.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.firstspringstartproject02.dto.CommentaireDto;
import com.firstspringstartproject02.dto.PersonneDto;

/**
 * @author P. Fouque
 *
 */
@Entity
@Table(name = "commentaires")
public class Commentaire implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "libelle", nullable = false)
	private String libelle;
	@Column(name = "sujet", nullable = false)
	private String sujet;
	@Column(name = "description", nullable = false)
	private String description;
	@Column(name = "date", nullable = false)
	private String date;
	
	public Commentaire() {
	}

	public Commentaire(CommentaireDto commentaireDto) {
		this.setId(commentaireDto.getId());
		this.setLibelle(commentaireDto.getLibelle());
        this.setSujet(commentaireDto.getSujet());
        this.setDescription(commentaireDto.getDescription());
        this.setDate(commentaireDto.getDate());
        
    }
	
	public Commentaire(Long id, String libelle, String sujet, String description, String date) {
		super();
		this.id = id;
		this.libelle = libelle;
		this.sujet = sujet;
		this.description = description;
		this.date = date;
	}
	
	public Commentaire(String libelle, String sujet, String description, String date) {
		super();
		this.libelle = libelle;
		this.sujet = sujet;
		this.description = description;
		this.date = date;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	public String getSujet() {
		return sujet;
	}

	public void setSujet(String sujet) {
		this.sujet = sujet;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((libelle == null) ? 0 : libelle.hashCode());
		result = prime * result + ((sujet == null) ? 0 : sujet.hashCode());
		return result;
	}
	

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Commentaire other = (Commentaire) obj;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (libelle == null) {
			if (other.libelle != null)
				return false;
		} else if (!libelle.equals(other.libelle))
			return false;
		if (sujet == null) {
			if (other.sujet != null)
				return false;
		} else if (!sujet.equals(other.sujet))
			return false;
		return true;
	}

	
	
}
