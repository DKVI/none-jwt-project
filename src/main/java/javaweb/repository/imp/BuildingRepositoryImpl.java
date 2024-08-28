package javaweb.repository.imp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.util.StringBuilders;
import org.springframework.stereotype.Repository;

import javaweb.repository.BuildingRepository;
import javaweb.repository.entity.BuildingEntity;
@Repository
public class BuildingRepositoryImpl implements BuildingRepository {
	private static final String DB_URL = "jdbc:mysql://localhost:3306/estatebasic?verifyServerCertificate=false&useSSL=true";
	private static final String USER = "root";
	private static final String PASS = "mysql";

	@Override
	
	public List<BuildingEntity> findAll(String name, Long districtid) {
		StringBuilder sql = new StringBuilder("SELECT * FROM building WHERE 1 = 1");	
		List<BuildingEntity> result = new ArrayList<BuildingEntity>();
		try {
			System.out.println(districtid);
			Connection connection = DriverManager.getConnection(DB_URL, USER, PASS);
			PreparedStatement statement = null;
			if (name != null) {
				sql.append(" AND " + "name = " + "? ");
				System.out.println(sql.toString());
				statement = connection.prepareStatement(sql.toString());
				statement.setString(1, name);
			}
			else {
				statement = connection.prepareStatement(sql.toString());
			}
			ResultSet rs = statement.executeQuery();
			
			while (rs.next()) {
				BuildingEntity building = new BuildingEntity();
				building.setName(rs.getString("name"));
				building.setNumberOfBasement(rs.getInt("numberofbasement"));
				building.setStreet(rs.getString("street"));
				building.setWard(rs.getString("ward"));
				result.add(building);
			}
			System.out.println(result);
			return result;
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public BuildingEntity findByName(String name) {
		String sql = "SELECT * FROM building WHERE name = ?";	
		try {
			Connection connection = DriverManager.getConnection(DB_URL, USER, PASS);
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1,name);
			ResultSet rs = statement.executeQuery();
			BuildingEntity building = new BuildingEntity(); 
			while (rs.next()) {
				building.setName(rs.getString("name"));
				building.setNumberOfBasement(rs.getInt("numberofbasement"));
				building.setStreet(rs.getString("street"));
				building.setWard(rs.getString("ward"));
			}
			return building;
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}


}
