package org.senproject.ppapa.api;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;

import org.senproject.ppapa.model.Prescription;
import org.senproject.ppapa.model.User;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class TestRestCall {
	
//	public void test() throws URISyntaxException {
//        final String uri = "https://gv4j3htte5.execute-api.us-east-1.amazonaws.com/test/APICreatePrescription";
//        RestTemplate restTemplate = new RestTemplate();
//
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_JSON);
//        //headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
////        headers.set("X-COM-PERSIST", "NO");
////        headers.set("X-COM-LOCATION", "USA");
//        String body = "{\"userId\":\"garsss\",\"password\":\"sdfsd\",\"role\":\"PATIENT\"}";
//        Prescription prescription = new Prescription();
//       // prescription.setPuser("gar");
//        //prescription.setKey("sadfadsf);
//        prescription.setInformation("gagag hjdfdfhihjiwfijijifw");
//        System.out.println(prescription.toString());
//        HttpEntity<String> entity = new HttpEntity<String>(prescription.toString(), headers);
//        URI uriobj = new URI(uri);
////        restTemplate.postForEntity(uriobj, entity, String.class);
//
//        ResponseEntity<String> response = restTemplate.exchange(uri, HttpMethod.PUT, entity, String.class);
//        System.out.println(response.getStatusCodeValue() + ", body = " + response.getBody());
//        
//
//	}
	
	public static void main(String[] args) {
		int minute = 1; 
		int hour = 1; 
		int month = 1; 
		String a = "cron(" + minute + " " + hour + " * " + month + " ? 2021)";
		System.out.println(a); 
		
	}
//		TestRestCall call = new TestRestCall();
//		try {
//			call.test();
//		} catch (URISyntaxException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}

}
