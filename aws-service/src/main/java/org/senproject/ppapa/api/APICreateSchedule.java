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
import org.senproject.ppapa.dto.Response;
import org.senproject.ppapa.dto.Schedule;

import com.amazonaws.client.builder.AwsClientBuilder.EndpointConfiguration;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.cloudwatchevents.AmazonCloudWatchEvents;
import com.amazonaws.services.cloudwatchevents.AmazonCloudWatchEventsClientBuilder;
import com.amazonaws.services.cloudwatchevents.model.PutRuleRequest;
import com.amazonaws.services.cloudwatchevents.model.PutRuleResult;
import com.amazonaws.services.cloudwatchevents.model.RuleState;
import com.amazonaws.services.lambda.runtime.Context;

public class APICreateSchedule {
	
	public void handleRequest(InputStream input, OutputStream output, Context context) throws IOException {

		JSONParser parser = new JSONParser();
		BufferedReader reader = new BufferedReader(new InputStreamReader(input));
		JSONObject responseJson = new JSONObject();
		Response response = new Response();
		try {
			JSONObject responseBody = new JSONObject();
			JSONObject event = (JSONObject) parser.parse(reader);
			context.getLogger().log("CreateSchedule invoked " + event);
			if (event.get("body") != null) {
				Schedule schedule = (Schedule) Schedule.newInstance(Schedule.class, (String) event.get("body"));
				putCWRule(schedule.getUser(), schedule.getMonth(), schedule.getHour(), schedule.getMinute());
				response.setMessage("Success");
				response.setError("No Error");
				response.setStatus(1);
			JSONObject headerJson = new JSONObject();
			headerJson.put("x-custom-header", "my custom header value");

			responseJson.put("statusCode", 200);
			responseJson.put("headers", headerJson);
			responseJson.put("body", response.toString());
			}
		} catch (ParseException pex) {
			response.setMessage("Failure");
			response.setError("Error");
			response.setStatus(0);
			responseJson.put("body", response.toString());
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
	
	public void putCWRule(String name, int month, int hour, int minute ) {
//		try {
//		System.setProperty("aws.accessKeyId", "AKIASYDRTNJ5XI2MNP67");
//		System.setProperty("aws.secretKey", "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");

		AmazonCloudWatchEventsClientBuilder builder = AmazonCloudWatchEventsClientBuilder.standard();
		AmazonCloudWatchEvents cwe = AmazonCloudWatchEventsClientBuilder.standard().withEndpointConfiguration(
				new EndpointConfiguration("https://events.us-east-1.amazonaws.com", Regions.US_EAST_1.getName())).build();		
		
			PutRuleRequest request = new PutRuleRequest()
					.withName(name)
					.withScheduleExpression("cron(" + minute + " " + hour + " * " + month + " ? 2021")
					.withState(RuleState.ENABLED);

			PutRuleResult response = cwe.putRule(request);
//			System.out.printf("Successfully created CloudWatch events rule %s with arn %s", response.toString(),
//					response.getRuleArn());
			// roleArn, response.ruleArn());
//		} catch (CloudWatchException e) {
//			System.err.println(e.awsErrorDetails().errorMessage());
//			System.exit(1);
//		}
	}
}
//	public void addTarget(String name) {
//		AmazonCloudWatchEventsClientBuilder builder = AmazonCloudWatchEventsClientBuilder.standard();
//		AmazonCloudWatchEvents cwe = AmazonCloudWatchEventsClientBuilder.standard().withEndpointConfiguration(
//				new EndpointConfiguration("https://events.us-east-1.amazonaws.com", Regions.US_EAST_1.getName())).build();
//		
//		Target target = new Target(.)
//	}
//}