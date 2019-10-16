package com.tutorialspoint.server;

import java.sql.Connection;
import java.sql.SQLException;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.tutorialspoint.client.CarService;
import com.tutorialspoint.shared.CarBrand;
import com.tutorialspoint.shared.CarModel;
import com.tutorialspoint.shared.SaleInfo;
import com.tutorialspoint.shared.SerialList;

/**
 * The server-side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class CarServiceImpl extends RemoteServiceServlet implements CarService {
	
	@Override
	public Boolean register(SaleInfo saleInfo) {
		Boolean response = null;
		try {
			Connection conn = DatabaseConnection.getDBConnection();
			response = RegisterCarSaleCommand.registerCarSale(conn, saleInfo);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return response;
	}
	
	public SerialList<CarBrand> findCarBrands() {
		SerialList<CarBrand> brandsLst = null;	
		try {
			Connection conn = DatabaseConnection.getDBConnection();
			brandsLst = FindCarBrandsCommand.getCarBrandsFromDatabase(conn);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return brandsLst;
	}
	
	public SerialList<CarModel> findCarModels(Integer brandId) {
		SerialList<CarModel> modelsLst = null;
		try {
			Connection conn = DatabaseConnection.getDBConnection();
			modelsLst = FindCarModelsCommand.getCarModelsFromDatabase(conn, brandId);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return modelsLst;
	}	
}
