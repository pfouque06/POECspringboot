package com.example.demo.model;

//import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
//import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
//import lombok.Setter;
import lombok.ToString;
//import lombok.experimental.FieldDefaults;


//@FieldDefaults(level=AccessLevel.PRIVATE) : passe tous les champs en private ;
//@NoArgsConstructor : génère le constructeur sans argument et public ;
//@AllArgsConstructor : génère le constructeur avec tous les arguments et public (pour l'exemple) ;
//@Getter : génère tous les getters sur les champs ;
//@Setter : génère tous les setters sur les champs ;
//@EqualsAndHashCode(of=...) : génère equals et hashCode (et d'autres méthodes) sur les champs donnés ;
//@ToString(of=...) : génère toString sur les champs donnés.


//@FieldDefaults(level = AccessLevel.PRIVATE)
//@Getter
//@Setter
//@ToString(of = { "nom", "prenom"})
@Data
@ToString(of = { "nom", "prenom"})
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
public class Personne {
	
	Integer num;
	@NonNull
	String nom;
	@NonNull
	String prenom;
}
