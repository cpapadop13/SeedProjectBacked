package com.task.seedproject.employee;

import java.util.HashMap;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class AbstractApi {
   // THIS METHOD CREATE TO MINE CUSTOM RESPONCE FOR FRONT END APPLICATION
	public ResponseEntity<HashMap<String, Object>> getResponseFormat(HttpStatus status, String message, Object data) {

		int responsestatus;
		if (status.equals(HttpStatus.OK) || status.value() == 200) {
			responsestatus = 1;
		} else {
			responsestatus = 0;
		}
		HashMap<String, Object> map = new HashMap<>();
		map.put("responsecode", responsestatus);
		map.put("messages", message);
		map.put("data", data);
		return ResponseEntity.status(status).body(map);
	}

}
