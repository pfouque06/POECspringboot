package com.restapi.one2many.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "adresse")
public class Adresse extends AuditModel {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	@Column(name = "rue")
	private String rue;
	@Column(name = "ville")
	private String ville;
	@Column(name = "code_postal")
	private String codePostal;
//	@ManyToOne(cascade = CascadeType.ALL)
//	@JoinColumn(name = "personne_id")
//	private Personne personne;

	public Adresse() {
		super();
	}

	public Adresse(String rue, String ville, String codePostal) {
		super();
		this.rue = rue;
		this.ville = ville;
		this.codePostal = codePostal;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}
	
	public String getRue() {
		return rue;
	}

	public void setRue(String rue) {
		this.rue = rue;
	}

	public String getVille() {
		return ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}

	public String getCodePostal() {
		return codePostal;
	}

	public void setCodePostal(String codePostal) {
		this.codePostal = codePostal;
	}

//	public Personne getPersonne() {
//		return personne;
//	}
//
//	public void setPersonne(Personne personne) {
//		this.personne = personne;
//	}

	@Override
	public String toString() {
		return "[" + id + "] Adresse: " + rue + ", " + ville + ", " + codePostal;
	}

}
