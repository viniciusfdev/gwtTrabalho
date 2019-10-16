package com.tutorialspoint.server;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.tutorialspoint.shared.CarModel;
import com.tutorialspoint.shared.SerialList;

public class FindCarModelsCommand {
	
	private static SerialList<CarModel> modelsLst = new SerialList<CarModel>();
	
	public static SerialList<CarModel> getCarModelsFromDatabase(Connection conn, Integer brandId) {
		
		StringBuilder str = new StringBuilder();
		
		str.append("SELECT cm.model_id, cm.model, cm.brand_id, cb.brand, cm.model_description ");
		str.append("FROM car_models cm ");
		str.append("INNER JOIN car_brands cb ON cb.brand_id = cm.brand_id ");
		str.append("WHERE cm.brand_id = " + brandId);
		
		String sql = str.toString();
		System.out.println("FindCarModelsCommand SQL: " + sql);
		Statement stmt;
		ResultSet rs;
		
		Integer modelId;
		String model;
		Integer brandID;
		String brand;
		String modelDescription;
		CarModel carModel;
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			
			if (rs != null) {
				while (rs.next()) {
					
					// get values from database
					modelId = rs.getInt(1);
					model = rs.getString(2);
					brandID = rs.getInt(3);
					brand = rs.getString(4);
					modelDescription = rs.getString(5);
					
					// create CarModel Bean
					carModel = new CarModel();
					carModel.setModelId(modelId);
					carModel.setModel(model);
					carModel.setBrandId(brandID);
					carModel.setBrand(brand);
					carModel.setModelDescription(modelDescription);
					
					// add CarModelBean to the list
					modelsLst.add(carModel);	
				}
			} else {
				System.out.println("Nao foi possivel consultar a tabela car_models!!!");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return modelsLst;
	}
}
