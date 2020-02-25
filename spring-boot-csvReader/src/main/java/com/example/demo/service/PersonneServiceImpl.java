package com.example.demo.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.springframework.stereotype.Service;

import com.example.demo.entities.Personne;
import com.opencsv.CSVReader;

@Service
public class PersonneServiceImpl implements PersonneService {

	private final ArrayList<Personne> personnes = new ArrayList<Personne>();

	@Override
	public ArrayList<Personne> findAll() {
		FileInputStream fis = null;

		try {

			String fileName = "src/main/resources/personne.csv";

			fis = new FileInputStream(new File(fileName));
			CSVReader reader = new CSVReader(new InputStreamReader(fis));
			String[] nextLine ;
			
			reader.readNext();
			while ((nextLine = reader.readNext()) != null) {
				Personne newPersonne = new Personne(Long.valueOf(nextLine[0]), nextLine[1], nextLine[2]);
				personnes.add(newPersonne);
			}

		} catch (FileNotFoundException ex) {
			ex.printStackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			try {
				if (fis != null) {
					fis.close();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}

		return personnes;
	}
}