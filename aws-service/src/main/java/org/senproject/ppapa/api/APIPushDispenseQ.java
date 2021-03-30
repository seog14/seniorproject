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
import org.senproject.ppapa.repository.PatientRepository;


import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestStreamHandler;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import com.amazonaws.services.sqs.model.SendMessageRequest;
import com.amazonaws.services.sqs.model.Message;

public class APIPushDispenseQ {
	
	
	public void handleRequest(InputStream input, OutputStream output, Context context) throws IOException {

		

		try {
			
		
	        final AmazonSQS sqs = AmazonSQSClientBuilder.defaultClient();
	       
			SendMessageRequest send_msg_request = new SendMessageRequest()
			        .withQueueUrl("https://sqs.us-east-1.amazonaws.com/245932314163/DispenseQueue")
			        .withMessageBody("1");
	
			
			sqs.sendMessage(send_msg_request);

		

		} catch (Exception pex) {
			context.getLogger().log("exception" + pex);
			
		}

	
	}

	public void handleGetByParam(InputStream inputStream, OutputStream outputStream, Context context)
			throws IOException {

		// implementation
	}

	public void handleLogin(InputStream inputStream, OutputStream outputStream, Context context) throws IOException {

	}
}

