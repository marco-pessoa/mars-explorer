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
	 * A primeira linha da entrada de dados é a coordenada do ponto
	 * superior-direito da malha do planalto. Lembrando que a inferior esquerda
	 * sempre será (0,0). <br>
	 * <br>
	 * O resto da entrada será informação das sondas que foram implantadas. Cada
	 * sonda é representada por duas linhas. A primeira indica sua posição
	 * inicial e a segunda uma série de instruções indicando para a sonda como
	 * ela deverá explorar o planalto. <br>
	 * <br>
	 * A posição é representada por dois inteiros e uma letra separados por
	 * espaços, correpondendo à coordenada X-Y e à direção da sonda. Cada sonda
	 * será controlada sequencialmente, o que quer dizer que a segunda sonda só
	 * irá se movimentar após que a primeira tenha terminado suas instruções.
	 * 
	 * @param strInstructions
	 * @return
	 */
	public Mission createMission(List<String> strInstructions);

}
