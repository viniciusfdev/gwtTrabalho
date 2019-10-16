package com.tutorialspoint.client;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.tutorialspoint.shared.CarBrand;
import com.tutorialspoint.shared.CarModel;
import com.tutorialspoint.shared.SaleInfo;
import com.tutorialspoint.shared.SerialList;

/**
 * The async counterpart of <code>CarService</code>.
 */
public interface CarServiceAsync {	
	
	void register(SaleInfo saleInfo, AsyncCallback<Boolean> asyncCallback);
	
	void findCarBrands(AsyncCallback<SerialList<CarBrand>> asyncCallback);
	
	void findCarModels(Integer brandId, AsyncCallback<SerialList<CarModel>> asyncCallback);
	
}