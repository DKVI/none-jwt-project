package javaweb.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javaweb.DTO.BuildingDTO;
import javaweb.repository.BuildingRepository;
import javaweb.repository.entity.BuildingEntity;
import javaweb.service.BuildingService;

@Service
public class BuildingServiceImpl implements BuildingService {
	@Autowired
	private BuildingRepository buildingRepository;
	@Override
	public List<BuildingDTO> findAll(String name, Long districtid) {
		System.out.println("name: " + name);
		List<BuildingEntity> buildingEntities = buildingRepository.findAll(name, districtid);
		List<BuildingDTO> result = new ArrayList<BuildingDTO>();
		for (BuildingEntity item : buildingEntities) {
				BuildingDTO buildingDTO = new BuildingDTO();
				buildingDTO.setName(item.getName());
				buildingDTO.setAddress(item.getStreet() + ", " + item.getWard());
				buildingDTO.setNumberOfBasement(item.getNumberOfBasement());
				result.add(buildingDTO);
		}
		return result;
	}
}
