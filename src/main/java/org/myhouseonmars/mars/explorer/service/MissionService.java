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
	 * A primeira linha da entrada de dados e a coordenada do ponto
	 * superior-direito da malha do planalto. Lembrando que a inferior esquerda
	 * sempre sera (0,0). <br>
	 * <br>
	 * O resto da entrada sera informacao das sondas que foram implantadas. Cada
	 * sonda e representada por duas linhas. A primeira indica sua posicao
	 * inicial e a segunda uma serie de instrucoes indicando para a sonda como
	 * ela devera explorar o planalto. <br>
	 * <br>
	 * A posicao e representada por dois inteiros e uma letra separados por
	 * espacos, correpondendo e coordenada X-Y e a direcao da sonda. Cada sonda
	 * sera controlada sequencialmente, o que quer dizer que a segunda sonda so
	 * ira se movimentar apos que a primeira tenha terminado suas instrucoes.
	 * 
	 * @param strInstructions
	 * @return
	 */
	public Mission createMission(List<String> strInstructions);

}
