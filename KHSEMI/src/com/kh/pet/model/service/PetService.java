package com.kh.pet.model.service;

import java.sql.Connection;

import com.kh.common.JDBCTemplate;
import com.kh.pet.model.dao.PetDao;
import com.kh.pet.model.vo.Pet;

public class PetService {
	
	
	public Pet updatePet(Pet p) {
		
		Connection conn = JDBCTemplate.getConnection();
		
		int result = new PetDao().updatePet(conn, p);
		
		Pet updatePet = null;
		
		if(result > 0) {
			JDBCTemplate.commit(conn);
			
			updatePet = new PetDao().selectPet(conn, p.getUserNo());
			
		} else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		
		return updatePet;
	}

}
