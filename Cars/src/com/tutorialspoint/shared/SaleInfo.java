package com.tutorialspoint.shared;

import java.io.Serializable;

public class SaleInfo implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private String clientName;
	private Integer carBrandId;
	private Integer carModelId;
	private String saleDate;
	private String saleValue;
	
	public SaleInfo(){}

	public SaleInfo(String clientName, Integer carBrandId, Integer carModelId, String saleDate, String saleValue) {
		super();
		this.clientName = clientName;
		this.carBrandId = carBrandId;
		this.carModelId = carModelId;
		this.saleDate = saleDate;
		this.saleValue = saleValue;
	}

	public String getClientName() {
		return clientName;
	}
	
	public void setClientName(String clientName) {
		this.clientName = clientName;
	}
	
	public Integer getCarBrandId() {
		return carBrandId;
	}
	
	public void setCarBrandId(Integer carBrandId) {
		this.carBrandId = carBrandId;
	}
	
	public Integer getCarModelId() {
		return carModelId;
	}
	
	public void setCarModelId(Integer carModelId) {
		this.carModelId = carModelId;
	}
	
	public String getSaleDateTime() {
		return saleDate;
	}
	
	public void setSaleDateTime(String saleDate) {
		this.saleDate = saleDate;
	}
	
	public String getSaleValue() {
		return saleValue;
	}

	public void setSaleValue(String saleValue) {
		this.saleValue = saleValue;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((carBrandId == null) ? 0 : carBrandId.hashCode());
		result = prime * result + ((carModelId == null) ? 0 : carModelId.hashCode());
		result = prime * result + ((clientName == null) ? 0 : clientName.hashCode());
		result = prime * result + ((saleDate == null) ? 0 : saleDate.hashCode());
		result = prime * result + ((saleValue == null) ? 0 : saleValue.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SaleInfo other = (SaleInfo) obj;
		if (carBrandId == null) {
			if (other.carBrandId != null)
				return false;
		} else if (!carBrandId.equals(other.carBrandId))
			return false;
		if (carModelId == null) {
			if (other.carModelId != null)
				return false;
		} else if (!carModelId.equals(other.carModelId))
			return false;
		if (clientName == null) {
			if (other.clientName != null)
				return false;
		} else if (!clientName.equals(other.clientName))
			return false;
		if (saleDate == null) {
			if (other.saleDate != null)
				return false;
		} else if (!saleDate.equals(other.saleDate))
			return false;
		if (saleValue == null) {
			if (other.saleValue != null)
				return false;
		} else if (!saleValue.equals(other.saleValue))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "SaleInfo [clientName=" + clientName + ", carBrandId=" + carBrandId + ", carModelId=" + carModelId
				+ ", saleDate=" + saleDate + ", saleValue=" + saleValue + "]";
	}	
}
