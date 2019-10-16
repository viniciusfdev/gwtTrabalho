package com.tutorialspoint.server;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import com.tutorialspoint.shared.SaleInfo;

public class RegisterCarSaleCommand {
	
	public static Boolean registerCarSale(Connection conn, SaleInfo saleInfo) {
		
		StringBuilder str = new StringBuilder();
		str.append("INSERT INTO sales");
		str.append("(`client_name`,");
		str.append("`brand_id`,");
		str.append("`model_id`,");
		str.append("`date_time`,");
		str.append("`sale_value`) ");
		str.append("VALUES ('" + saleInfo.getClientName() + "',");
		str.append("'" + saleInfo.getCarBrandId() + "',");
		str.append("'" + saleInfo.getCarModelId() + "',");
		str.append("'" + saleInfo.getSaleDateTime() + "',");
		str.append("'" + saleInfo.getSaleValue() + "')");
		
		String sql = str.toString();
		System.out.println("RegisterCarSaleCommand SQL: " + sql);
		Statement stmt;
		int numberOfAffectedRows = 0;
		
		try {
			stmt = conn.createStatement();
			numberOfAffectedRows = stmt.executeUpdate(sql);
			
			if (numberOfAffectedRows > 0) {
				System.out.println("Venda registrada com sucesso na tabela sales!!!");
				return new Boolean(true);
			} else {
				System.out.println("Nao foi possivel salvar na tabela sales!!!");
				return new Boolean(false);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return new Boolean(true);
	}
}
