package com.d1.auth.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

public class AccountClaim {

	public static Claims createClaims(Account account) {
		Claims claims = Jwts.claims().setSubject(String.valueOf(account.getId()));
	    claims.put("empId", account.getId());
	    claims.put("companyCd", account.getCompanyCd());
	    claims.put("empNo", account.getEmpNo());
	    claims.put("empNm", account.getEmpNm());
	    claims.put("email", account.getEmail());
	    claims.put("hireDate", account.getHireDate());
	    claims.put("retireDate", account.getRetireDate());
	    
		return claims;
	}
}
