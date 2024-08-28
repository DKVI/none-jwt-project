package javaweb.repository;

import java.util.List;

import javaweb.repository.entity.BuildingEntity;

public interface BuildingRepository {
	List<BuildingEntity> findAll(String name, Long districtid);
	BuildingEntity findByName(String name);
}
