package javaweb.service;

import java.util.List;

import javaweb.DTO.BuildingDTO;

public interface BuildingService {
	List<BuildingDTO> findAll (String name, Long districtid);
}
