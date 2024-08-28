package javaweb.api;

import java.io.Console;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.naming.spi.DirStateFactory.Result;
import javax.swing.plaf.synth.SynthOptionPaneUI;
import javax.validation.Validation;
import javax.validation.Path.ReturnValueNode;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mysql.jdbc.PreparedStatement;

import java.sql.*;

import javaweb.DTO.BuildingDTO;
import javaweb.customexception.FieldRequireException;
import javaweb.customexception.NoBodyException;
import javaweb.customresponse.CustomResponse;
import javaweb.customresponse.ErrorResponse;
import javaweb.models.Building;
import javaweb.repository.entity.BuildingEntity;
import javaweb.repository.imp.BuildingRepositoryImpl;
import javaweb.service.BuildingService;

@RestController
public class BuildingAPI {
	@Autowired
	private BuildingService buildingService;
	@GetMapping(value = "/api/building")
	public ResponseEntity<Object> getBuilding(@RequestParam(value = "name", required = false) String name,
											@RequestParam (value = "districtid", required = false) Long districtid) {
		try {
				List<BuildingDTO> list = buildingService.findAll(name, districtid);
				CustomResponse response = new CustomResponse();
				response.setCode("OK");
				Map<String, Object> mapData = new HashMap<String, Object>();
				mapData.put("building", list);
				response.setData(mapData);
				return new ResponseEntity<Object>(response, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<Object>(HttpStatus.NOT_FOUND);
	}

	public void validate(Building building) {
		if (building.getName() == "" || building.getName() == null || building.getName() == null
				|| building.getAddress() == "" || building.getAddress() == null) {
			throw new FieldRequireException("name or id or address can't null!");
		}
	}

	public void requireBody(Building building) {
		if (building == null) {
			throw new HttpMessageNotReadableException("Request body is required!");
		}
	}
}
