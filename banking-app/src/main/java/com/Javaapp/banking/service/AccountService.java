package com.Javaapp.banking.service;

import java.util.List;

import com.Javaapp.banking.dto.AccountDto;

public interface AccountService {
	AccountDto createAccount(AccountDto accountDto);
	AccountDto getAccountByID(Long id);
	AccountDto deposit(Long id, double amount);
	AccountDto withdraw(Long id, double amount);
	List<AccountDto> getAllAccouunts();
	void deleteAccount(Long id);
	
	

}
