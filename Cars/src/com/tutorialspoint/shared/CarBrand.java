package com.tutorialspoint.shared;

import java.io.Serializable;

public class CarBrand implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer brandId;
	private String brand;
	
	public CarBrand(){}
	
	public CarBrand(Integer brandId, String brand) {
		super();
		this.brandId = brandId;
		this.brand = brand;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((brand == null) ? 0 : brand.hashCode());
		result = prime * result + ((brandId == null) ? 0 : brandId.hashCode());
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
		CarBrand other = (CarBrand) obj;
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
		return true;
	}

	@Override
	public String toString() {
		return "CarBrand [brandId=" + brandId + ", brand=" + brand + "]";
	}
}
