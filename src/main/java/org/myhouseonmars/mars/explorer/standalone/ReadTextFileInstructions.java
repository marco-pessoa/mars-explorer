/**
 * 
 */
package org.myhouseonmars.mars.explorer.standalone;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.myhouseonmars.mars.explorer.domain.Mission;
import org.myhouseonmars.mars.explorer.service.MissionService;
import org.myhouseonmars.mars.explorer.service.impl.MissionServiceImpl;

/**
 * Programa simples para ler instrucoes de um arquivo texto e submete-las a um
 * conjunto de sondas.
 * 
 * @author Marco Pessoa
 *
 */
public class ReadTextFileInstructions {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		if (args.length < 1) {
			System.out.println("ERRO: Informe o caminho completo de um arquivo texto válido");
			return;
		}

		try (BufferedReader reader = Files.newBufferedReader(Paths.get(args[0]))) {
			List<String> strInstructions = new ArrayList<>();

			String line;
			while ((line = reader.readLine()) != null) {
				strInstructions.add(line);
			}

			MissionService missionService = new MissionServiceImpl();
			Mission mission = missionService.createMission(strInstructions);
			List<String> missionReport = missionService.executeMission(mission);

			System.out.println("Posicao Final de cada sonda (X Y):");
			for (String finalPosition : missionReport) {
				System.out.println(finalPosition);
			}

		} catch (NoSuchFileException nsf) {
			System.out.println("ERRO: Arquivo inexistente: " + nsf.getFile());
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println();

	}

}
