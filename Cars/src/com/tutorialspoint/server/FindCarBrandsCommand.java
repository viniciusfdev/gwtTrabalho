package com.tutorialspoint.server;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.tutorialspoint.shared.CarBrand;
import com.tutorialspoint.shared.SerialList;

public class FindCarBrandsCommand {
	
	private static SerialList<CarBrand> brandsLst = new SerialList<CarBrand>();
	
	public static SerialList<CarBrand> getCarBrandsFromDatabase(Connection conn) {
		
		String sql = "SELECT brand_id, brand FROM car_brands";		
		System.out.println("FindCarBrandsCommand SQL: " + sql);
		
		Statement stmt;
		ResultSet rs;
		Integer brandId;
		String brand;
		CarBrand carBrand;
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			
			if (rs != null) {
				while (rs.next()) {
					brandId = rs.getInt(1);
					brand = rs.getString(2);
					carBrand = new CarBrand();
					carBrand.setBrandId(brandId);
					carBrand.setBrand(brand);
					
					brandsLst.add(carBrand);	
				}
			} else {
				System.out.println("Nao foi possivel consultar a tabela car_brands!!!");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return brandsLst;
	}
}
