package org.senproject.ppapa.api;

import javax.jms.JMSException;

import com.amazon.sqs.javamessaging.AmazonSQSMessagingClientWrapper;
import com.amazon.sqs.javamessaging.ProviderConfiguration;
import com.amazon.sqs.javamessaging.SQSConnection;
import com.amazon.sqs.javamessaging.SQSConnectionFactory;
import com.amazonaws.client.builder.AwsClientBuilder.EndpointConfiguration;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;

public class RecievePQ {
	
	public static void main(String[] a) {
		SQSConnectionFactory connectionFactory = new SQSConnectionFactory(
		        new ProviderConfiguration(),
		        AmazonSQSClientBuilder.standard().
		        withRegion(Regions.US_EAST_1));
		SQSConnection connection = null;
		try {
			connection = connectionFactory.createConnection();
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		AmazonSQSMessagingClientWrapper client = connection.getWrappedAmazonSQSClient();
		try {
			if(client.queueExists("DoctorPatientQueue")){
				System.out.println("true");
			}
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
