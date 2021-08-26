package org.senproject.ppapa.api;



import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import com.amazonaws.services.sqs.model.SendMessageRequest;


public class APIPushLockQ {
	
	
	public void handleRequest(InputStream input, OutputStream output, Context context) throws IOException {

		

		try {

	        final AmazonSQS sqs = AmazonSQSClientBuilder.defaultClient();
	       
			SendMessageRequest send_msg_request = new SendMessageRequest()
			        .withQueueUrl("https://sqs.us-east-1.amazonaws.com/245932314163/LockQueue")
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

