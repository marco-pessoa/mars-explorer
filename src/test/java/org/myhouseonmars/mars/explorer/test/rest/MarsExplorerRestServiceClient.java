/**
 * 
 */
package org.myhouseonmars.mars.explorer.test.rest;

import java.io.File;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.media.multipart.FormDataMultiPart;
import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.glassfish.jersey.media.multipart.file.FileDataBodyPart;

/**
 * Cliente do servico REST. Envia um arquivo texto com instrucoes da missao
 * exploratoria para o servidor.
 * 
 * @author Marco Pessoa
 *
 */
public class MarsExplorerRestServiceClient {

	private static final String SERVICE_URL = "http://localhost:8080/mars-explorer/api/rest/mission";

	/**
	 * 
	 * @param args
	 *            Caminho do arquivo texto a ser enviado para o servidor.
	 */
	public static void main(String[] args) {
		if (args.length < 1) {
			System.out.println("ERRO: Informe o caminho completo de um arquivo texto valido");
			return;
		}

		Client client = ClientBuilder.newBuilder().register(MultiPartFeature.class).build();
		WebTarget target = client.target(SERVICE_URL);
		Invocation.Builder invocationBuilder = target.request();

		FileDataBodyPart bodyPart = new FileDataBodyPart("mission", new File(args[0]));

		FormDataMultiPart multiPart = new FormDataMultiPart();
		multiPart.bodyPart(bodyPart);

		Response response = invocationBuilder.post(Entity.entity(multiPart, multiPart.getMediaType()));

		System.out.println(response.toString());
		System.out.println("Posicao Final de cada sonda (X Y Direcao):");
		System.out.println(response.readEntity(String.class));

	}

}
