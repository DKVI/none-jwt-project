package javaweb.api;

import java.io.Console;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.validation.Validation;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javaweb.DTO.BuildingDTO;
import javaweb.customexception.FieldRequireException;
import javaweb.customresponse.CustomResponse;
import javaweb.customresponse.ErrorResponse;
import javaweb.models.Building;

@RestController
public class BuildingAPI {
	private ArrayList<Building> listBuildings = new ArrayList<Building>();

	public BuildingAPI() {
		listBuildings.add(new Building(1, "The Golden Tower", "HongKong"));
		listBuildings.add(new Building(2, "Van Hanh Mall", "HCM city"));
	}

	@GetMapping(value = "/api/building")
	public ResponseEntity<Object> getBuilding(@RequestParam(name = "id", required = false) String idParam) {

		if (idParam == null || idParam == "") {
			CustomResponse response = new CustomResponse();
			response.setCode("OK");
			Map<String, ArrayList<Building>> data = new HashMap<String, ArrayList<Building>>();
			data.put("building", listBuildings);
			response.setData(data);
			return new ResponseEntity<Object>(response, HttpStatus.OK);
		} else {
			try {
				int id = Integer.parseInt(idParam);
				Building result = null;

				for (Building building : listBuildings) {
					if (building.getId() == id) {
						result = building;
					}
				}
				if (result != null) {
					CustomResponse response = new CustomResponse();
					response.setCode("OK");
					Map<String, Building> data = new HashMap<String, Building>();
					data.put("building", result);
					response.setData(data);
					return new ResponseEntity<Object>(response, HttpStatus.OK);
				} else {
					ErrorResponse response = new ErrorResponse();
					response.setCode("Not Found");
					response.setDetail("Can't find any building have id = " + id);
					return new ResponseEntity<Object>(response, HttpStatus.NOT_FOUND);
				}
			} catch (NumberFormatException e) {
				System.out.println("error");
				ErrorResponse errorResponse = new ErrorResponse();
				errorResponse.setCode("Bad Request");
				errorResponse.setDetail("id must be a number!");
				return new ResponseEntity<Object>(errorResponse, HttpStatus.BAD_REQUEST);
			} catch (Exception e) {
				System.out.println("error");
				ErrorResponse errorResponse = new ErrorResponse();
				errorResponse.setCode("Internal Server error");
				errorResponse.setDetail("Server have an error, please try again!");
				return new ResponseEntity<Object>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
	}

	public void validate(BuildingDTO buildingDTO) {
		if (buildingDTO.getName() == "" || buildingDTO.getName() == null || buildingDTO.getId() == null
				|| buildingDTO.getAddress() == "" || buildingDTO.getAddress() == null) {
			throw new FieldRequireException("name or id or address can't null!");
		}
	}

	@PostMapping(value = "/api/building")

	public ResponseEntity<Object> addBuilding(@RequestBody BuildingDTO buildingDTO) {
		validate(buildingDTO);
		return new ResponseEntity<Object>(buildingDTO,HttpStatus.OK);
	}
}
