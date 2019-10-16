package com.tutorialspoint.client;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.tutorialspoint.shared.CarBrand;
import com.tutorialspoint.shared.CarModel;
import com.tutorialspoint.shared.SaleInfo;
import com.tutorialspoint.shared.SerialList;

/**
 * The client-side stub for the RPC service.
 */
@RemoteServiceRelativePath("service")
public interface CarService extends RemoteService {	
	
	Boolean register(SaleInfo saleInfo);
	
	SerialList<CarBrand> findCarBrands();
	
	SerialList<CarModel> findCarModels(Integer brandId);
	
}