package com.tutorialspoint.shared;

import java.io.Serializable;

public class CarModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer modelId;
	private String model;
	private Integer brandId;
	private String brand;
	private String modelDescription;
	
	public CarModel() {}
	
	public CarModel(Integer modelId, String model, Integer brandId, String brand, String modelDescription) {
		super();
		this.modelId = modelId;
		this.model = model;
		this.brandId = brandId;
		this.brand = brand;
		this.modelDescription = modelDescription;
	}

	public Integer getModelId() {
		return modelId;
	}

	public void setModelId(Integer modelId) {
		this.modelId = modelId;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public Integer getBrandId() {
		return brandId;
	}

	public void setBrandId(Integer brandId) {
		this.brandId = brandId;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getModelDescription() {
		return modelDescription;
	}

	public void setModelDescription(String modelDescription) {
		this.modelDescription = modelDescription;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((brand == null) ? 0 : brand.hashCode());
		result = prime * result + ((brandId == null) ? 0 : brandId.hashCode());
		result = prime * result + ((model == null) ? 0 : model.hashCode());
		result = prime * result + ((modelDescription == null) ? 0 : modelDescription.hashCode());
		result = prime * result + ((modelId == null) ? 0 : modelId.hashCode());
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
		CarModel other = (CarModel) obj;
		if (brand == null) {
			if (other.brand != null)
				return false;
		} else if (!brand.equals(other.brand))
			return false;
		if (brandId == null) {
			if (other.brandId != null)
				return false;
		} else if (!brandId.equals(other.brandId))
			return false;
		if (model == null) {
			if (other.model != null)
				return false;
		} else if (!model.equals(other.model))
			return false;
		if (modelDescription == null) {
			if (other.modelDescription != null)
				return false;
		} else if (!modelDescription.equals(other.modelDescription))
			return false;
		if (modelId == null) {
			if (other.modelId != null)
				return false;
		} else if (!modelId.equals(other.modelId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "CarModel [modelId=" + modelId + ", model=" + model + ", brandId=" + brandId + ", brand=" + brand
				+ ", modelDescription=" + modelDescription + "]";
	}
}
