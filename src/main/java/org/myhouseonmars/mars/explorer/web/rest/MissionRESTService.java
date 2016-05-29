/**
 * 
 */
package org.myhouseonmars.mars.explorer.web.rest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.glassfish.jersey.media.multipart.BodyPart;
import org.glassfish.jersey.media.multipart.BodyPartEntity;
import org.glassfish.jersey.media.multipart.FormDataBodyPart;
import org.glassfish.jersey.media.multipart.FormDataMultiPart;
import org.myhouseonmars.mars.explorer.domain.Mission;
import org.myhouseonmars.mars.explorer.service.MissionService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * REST Web Service API.
 * 
 * @author Marco Pessoa
 *
 */
@Path("/rest")
public class MissionRESTService {

	@Autowired
	private MissionService missionService;

	/**
	 * Recebe um arquivo texto com instrucoes para implementar e executar uma
	 * missao exploratoria
	 * 
	 * @param multiPart
	 *            Arquivo texto com instrucoes para uma missao.
	 * @return Texto com a localizacao final de cada sonda.
	 */
	@POST
	@Path("/mission")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Produces(MediaType.TEXT_PLAIN)
	public Response explore(FormDataMultiPart multiPart) {

		BodyPart bodyPart = multiPart.getBodyParts().get(0);
		if (!MediaType.TEXT_PLAIN_TYPE.equals(bodyPart.getMediaType()))
			return Response.status(Response.Status.BAD_REQUEST).entity("Mission file must be of type text/plain")
					.build();

		FormDataBodyPart fileDataBodyPart = (FormDataBodyPart) bodyPart;
		InputStream inputStream = ((BodyPartEntity) fileDataBodyPart.getEntity()).getInputStream();

		try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {

			List<String> strInstructions = new ArrayList<>();

			String line;
			while ((line = reader.readLine()) != null) {
				strInstructions.add(line);
			}

			Mission mission = missionService.createMission(strInstructions);
			List<String> missionReport = missionService.executeMission(mission);

			StringBuilder stringBuilder = new StringBuilder();
			for (String finalPosition : missionReport) {
				stringBuilder.append(finalPosition).append(" \n");
			}

			String finalPosition = stringBuilder.toString();
			return Response.status(Status.OK).entity(finalPosition).build();

		} catch (IOException e) {
			return Response.status(Response.Status.BAD_REQUEST).entity(e).build();
		}
	}

	/**
	 * Usado para verificar se este servico REST esta operacional
	 * 
	 * @return Status do servico.
	 */
	@GET
	@Path("/check")
	@Produces(MediaType.TEXT_PLAIN)
	public Response verifyRESTService() {
		String result = "Explorando Marte: REST Service operacional";

		return Response.status(200).entity(result).build();
	}

}
