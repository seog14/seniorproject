package org.senproject.ppapa.api;

import com.amazonaws.client.builder.AwsClientBuilder.EndpointConfiguration;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.cloudwatchevents.AmazonCloudWatchEvents;
import com.amazonaws.services.cloudwatchevents.AmazonCloudWatchEventsClientBuilder;
import com.amazonaws.services.cloudwatchevents.model.ListRulesRequest;
import com.amazonaws.services.cloudwatchevents.model.PutRuleRequest;
import com.amazonaws.services.cloudwatchevents.model.PutRuleResult;
import com.amazonaws.services.cloudwatchevents.model.RuleState;

public class TestCloud {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

//	       final String USAGE = "\n" +
//	                "Usage:\n" +
//	                "  PutRule <ruleName> roleArn> \n\n" +
//	                "Where:\n" +
//	                "  ruleName - a rule name (for example, myrule).\n" +
//	                "  roleArn - a role ARN value (for example, arn:aws:iam::xxxxxx047983:user/MyUser).\n" ;

//	        if (args.length != 2) {
//	            System.out.println(USAGE);
//	            System.exit(1);
//	        }

		String ruleName = "idk-garrettsssss";
		String roleArn = "arn:aws:events:us-east-1:189216811643";
		
//		AmazonCloudWatchEventsClient cwe = new AmazonCloudWatchEventsClient(); 
//		AmazonCloudWatchEvents cwe =
//				AmazonCloudWatchEventsClientBuilder.defaultClient();
		AmazonCloudWatchEvents cwe = AmazonCloudWatchEventsClientBuilder.standard().withEndpointConfiguration(
				new EndpointConfiguration("https://events.us-east-2.amazonaws.com", Regions.US_EAST_2.getName())).build();		
//		cwe.setRegion(Region.getRegion(Regions.US_EAST_1));
		cwe.listRules(new ListRulesRequest());
		putCWRule(cwe, ruleName, roleArn);
		
	}

	// snippet-start:[cloudwatch.java2.put_rule.main]
	public static void putCWRule(AmazonCloudWatchEvents cwe, String ruleName, String roleArn) {

		try {
			PutRuleRequest request = new PutRuleRequest()
					.withName(ruleName)
					//.withRoleArn("arn:aws:iam::189216811643:role/service-role/APILoginCheck-role-x4ksfdn8")
					.withScheduleExpression("rate(20 minutes)")
					.withState(RuleState.ENABLED);

			PutRuleResult response = cwe.putRule(request);
			System.out.printf("Successfully created CloudWatch events rule %s with arn %s", response.toString(),
					response.getRuleArn());
			// roleArn, response.ruleArn());
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
	}
}
