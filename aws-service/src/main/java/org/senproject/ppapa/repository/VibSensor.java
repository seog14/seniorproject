package org.senproject.ppapa.repository;

import org.senproject.ppapa.model.ROLE;
import org.senproject.ppapa.model.User;

import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.PrimaryKey;
import com.amazonaws.services.dynamodbv2.document.PutItemOutcome;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.document.spec.DeleteItemSpec;
import com.amazonaws.services.dynamodbv2.document.spec.PutItemSpec;
import com.amazonaws.services.dynamodbv2.model.ConditionalCheckFailedException;

public class VibSensor {

	private DynamoDB dynamoDb;
	private String DYNAMODB_TABLE_NAME = "VibSensor";
	public static Regions REGION = Regions.US_EAST_1;

	public VibSensor() {
		initDynamoDbClient();
	}

	public PutItemOutcome save() throws ConditionalCheckFailedException {
		Item item = new Item();

		item.withString("status", "1");
		return this.dynamoDb.getTable(DYNAMODB_TABLE_NAME).putItem(new PutItemSpec().withItem(item));

	}

	public boolean exists() {
		
		Table table = this.dynamoDb.getTable(DYNAMODB_TABLE_NAME);
		
		// GetItemSpec spec = new GetItemSpec().withPrimaryKey("userId",
		// prescription.getPuser());
		Item dummy = table.getItem("status", "1");
		// Item dummy = table.getItem(spec);
		// spec.withProjectionExpression("role");
		if (dummy != null)// table.getItem(spec).toString() !=
																			// "PHARMACIST")
			return true;
		else
			return false;
	}
	
	public void delete() {
		DeleteItemSpec deleteItemSpec = new DeleteItemSpec()
				.withPrimaryKey(new PrimaryKey("status", "1"));
		this.dynamoDb.getTable(DYNAMODB_TABLE_NAME).deleteItem(deleteItemSpec);
	}

	/*
	 * public User findByUserId(String userId) {
	 * dynamoDb.getTable(DYNAMODB_TABLE_NAME).get return null; }
//	 *///public String getPrescription(PrescriptionKey prescriptionKey) {
//		Item dummy = this.dynamoDb.getTable(DYNAMODB_TABLE_NAME).getItem("key", prescriptionKey.getKey());
//		return dummy.getString("information");
//
//	}

	private void initDynamoDbClient() {

		AmazonDynamoDBClient client = new AmazonDynamoDBClient();
		client.setRegion(Region.getRegion(REGION));
		this.dynamoDb = new DynamoDB(client);
	}
}
