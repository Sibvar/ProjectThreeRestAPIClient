package by.samuseu.ProjectThreeRestAPIClient;

import org.springframework.http.HttpEntity;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        RestTemplate restTemplate = new RestTemplate();

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a name for the new sensor: ");
        String sensorName = scanner.nextLine();
        Map<String, String> newSensor = new HashMap<>();
        newSensor.put("name", sensorName);

        HttpEntity<Map<String, String>> addSensor = new HttpEntity<>(newSensor);

        String urlAddSensor = "http://localhost:8080/sensors/registration";
        String responseSensor = restTemplate.postForObject(urlAddSensor, addSensor, String.class);
        System.out.println(responseSensor);

        System.out.println("Add measurements");

        Map<String, String> newMeasurements = new HashMap<>();
        HttpEntity<Map<String, String>> addMeasurements = new HttpEntity<>(newMeasurements);
        String urlAddMeasurements = "http://localhost:8080/measurements/add";

        Random random = new Random();

        for (int i = 0; i < 1000; i++) {
            newMeasurements.put("temperature", String.valueOf(Math.ceil(random.nextDouble(50) * Math.pow(10, 1)) / Math.pow(10, 1)));
            newMeasurements.put("raining", String.valueOf(random.nextBoolean()));
            newMeasurements.put("sensor", sensorName);
            restTemplate.postForObject(urlAddMeasurements, addMeasurements, String.class);
        }

        System.out.println("Get values");

        String urlGetValues = "http://localhost:8080/measurements";
        System.out.println(restTemplate.getForObject(urlGetValues, String.class));
    }
}