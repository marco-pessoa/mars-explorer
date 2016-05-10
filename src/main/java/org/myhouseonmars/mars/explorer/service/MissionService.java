/**
 * 
 */
package org.myhouseonmars.mars.explorer.service;

import java.util.List;

import org.myhouseonmars.mars.explorer.domain.Mission;
import org.myhouseonmars.mars.explorer.domain.Position;

/**
 * Servico responsavel pela execucao de missoes exploratorias.
 * 
 * @author Marco Pessoa
 *
 */
public interface MissionService {

	/**
	 * Executa uma missao exploratoria retornando, ao seu termino, uma lista com a posicao final de cada
	 * uma das sondas.
	 * @param mission Uma missao exploratoria.
	 * @return Lista com as posicao final de cada uma das sondas no planalto. 
	 */
	List<Position> executeMission(Mission mission);
	
}
