package com.firstspringstartproject02.dto;

import java.io.Serializable;

public class CommentaireDto implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private String libelle;
	private String sujet;
	private String description;
	private String date;

	public CommentaireDto() {
	}

	public CommentaireDto(Long id, String libelle, String sujet, String description, String date) {
		super();
		this.id = id;
		this.libelle = libelle;
		this.sujet = sujet;
		this.description = description;
		this.date = date;
	}

	public CommentaireDto(String libelle, String sujet, String description, String date) {
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
	public String toString() {
		return "CommentaireDto [" + id + " libelle=" + libelle + ", sujet=" + sujet + ", description=" + description
				+ ", date=" + date + "]";
	}

	
	
}
