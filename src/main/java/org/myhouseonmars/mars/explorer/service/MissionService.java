/**
 * 
 */
package org.myhouseonmars.mars.explorer.service;

import java.util.List;

import org.myhouseonmars.mars.explorer.domain.Mission;

/**
 * Servico responsavel pela execucao de missoes exploratorias.
 * 
 * @author Marco Pessoa
 *
 */
public interface MissionService {

	/**
	 * Executa uma missao exploratoria retornando, ao seu termino, uma lista com
	 * a posicao final de cada uma das sondas.
	 * 
	 * @param mission
	 *            Uma missao exploratoria.
	 * @return Lista com as posicao final de cada uma das sondas no planalto.
	 */
	public List<String> executeMission(Mission mission);

	/**
	 * Cria uma missao ({@link Mission}) a partir de uma lista de instrucoes. As
	 * instrucoes devem seguir essa ordem: <br><br>
	 * A primeira linha da entrada de dados � a coordenada do ponto
	 * superior-direito da malha do planalto. Lembrando que a inferior esquerda
	 * sempre ser� (0,0). <br>
	 * <br>
	 * O resto da entrada ser� informa��o das sondas que foram implantadas. Cada
	 * sonda � representada por duas linhas. A primeira indica sua posi��o
	 * inicial e a segunda uma s�rie de instru��es indicando para a sonda como
	 * ela dever� explorar o planalto. <br>
	 * <br>
	 * A posi��o � representada por dois inteiros e uma letra separados por
	 * espa�os, correpondendo � coordenada X-Y e � dire��o da sonda. Cada sonda
	 * ser� controlada sequencialmente, o que quer dizer que a segunda sonda s�
	 * ir� se movimentar ap�s que a primeira tenha terminado suas instru��es.
	 * 
	 * @param strInstructions
	 * @return
	 */
	public Mission createMission(List<String> strInstructions);

}
