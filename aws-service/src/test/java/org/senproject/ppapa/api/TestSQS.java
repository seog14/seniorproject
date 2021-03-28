package org.senproject.ppapa.api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.senproject.ppapa.dto.PatientSQS;
import org.senproject.ppapa.dto.Response;
import org.senproject.ppapa.dto.Schedule;
import org.senproject.ppapa.model.Prescription;
import org.senproject.ppapa.repository.PrescriptionRepository;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestStreamHandler;

public class TestSQS implements RequestStreamHandler {

	public void handleRequest(InputStream input, OutputStream output, Context context) throws IOException {

		JSONParser parser = new JSONParser();
		BufferedReader reader = new BufferedReader(new InputStreamReader(input));
		JSONObject responseJson = new JSONObject();
		Response response = new Response();

		try {
			JSONObject responseBody = new JSONObject();
			JSONObject event = (JSONObject) parser.parse(reader);
			context.getLogger().log("APICreatePrescription invoked ");
			context.getLogger().log("APICreatePrescription invoked " + event);

			if (event.get("body") != null) {
					PrescriptionRepository prescriptionR = new PrescriptionRepository(); 
					PatientSQS patientSQS = (PatientSQS) PatientSQS.newInstance(PatientSQS.class, (String) event.get("body"));
					Prescription p = new Prescription(); 
					p.setKey(patientSQS.getUser());
					p.setInformation("asdf");
					prescriptionR.save(p);
					response.setMessage("asdf");
					response.setError("No Error");
					response.setStatus(1);
				
			}

			JSONObject headerJson = new JSONObject();
			headerJson.put("x-custom-header", "my custom header value");

			responseJson.put("statusCode", 200);
			responseJson.put("headers", headerJson);
			responseJson.put("body", response.toString());

		} catch (ParseException pex) {
			responseJson.put("statusCode", 400);
			responseJson.put("exception", pex);
		}

		OutputStreamWriter writer = new OutputStreamWriter(output, "UTF-8");
		writer.write(responseJson.toString());
		writer.close();
	}

	public void handleGetByParam(InputStream inputStream, OutputStream outputStream, Context context)
			throws IOException {

		// implementation
	}

	public void handleLogin(InputStream inputStream, OutputStream outputStream, Context context) throws IOException {

	}
}