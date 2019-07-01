package com.spring.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.spring.model.company.Company;
import com.spring.model.customer.Customer;

@Repository
@Transactional
public class CompanyDao extends ParentDao{	
	public void saveCompany(Company company) {
		super.entityManager.merge(company);
	}

	public void deleteCompany(Company company) {
		super.entityManager.remove(company);
	}

	public Company findCompanyById(String id) {
		System.out.println("find company by id");
		Company company;
		try {

			String query = "from Company where id = :id";

			company = (Company) super.entityManager.createQuery(query).setParameter("id", id).getSingleResult();			

			return company;
		} catch (Exception e) {
			return null;
		}
	}

	public Company findCompanyByBk(String companyCode) {
		System.out.println("find company by bk");
		Company company;
		try {
			String query = "from Company where companyCode = :companycode";

			company = (Company) super.entityManager.createQuery(query).setParameter("companycode", companyCode)
					.getSingleResult();

			return company;
		} catch (Exception e) {
			return null;
		}
	}

	public boolean isCompanyIdExist(String id) {
		if (findCompanyById(id) == null) {
			return false;
		} else {
			return true;
		}
	}

	public boolean isCompanyBkExist(String companyCode) {
		if (findCompanyByBk(companyCode) == null) {
			return false;
		} else {
			return true;
		}
	}
	
	public List<Company> findAll (){
		try {
			String query = "from Company WHERE status = 'active'";
			
			List<Company> Company = new ArrayList<Company>();
			
			Company = super.entityManager.createQuery(query).getResultList();

			return Company;
		}
		catch (Exception e) {
			return null;
		}
	}
	
	public List<Company> findAllWithStatus(String status){
		try {
			String query = "SELECT * FROM tbl_company WHERE status = :status";
			
			List<Company> companies = new ArrayList<Company>();
			
			companies = super.entityManager.createNativeQuery(query, Company.class)
					.setParameter("status", status)
					.getResultList();

			return companies;
		}catch(Exception e){
			return null;
		}
	}
}
