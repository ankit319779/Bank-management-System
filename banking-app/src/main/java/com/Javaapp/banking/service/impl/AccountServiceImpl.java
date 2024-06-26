package com.Javaapp.banking.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.Javaapp.banking.dto.AccountDto;
import com.Javaapp.banking.entity.Account;
import com.Javaapp.banking.mapper.AccountMapper;
import com.Javaapp.banking.repository.AccountRepository;
import com.Javaapp.banking.service.AccountService;
@Service
public class AccountServiceImpl implements AccountService{
	
	public AccountServiceImpl(AccountRepository accountRepository) {
		super();
		this.accountRepository = accountRepository;
	}


	private AccountRepository accountRepository;
	
	
	@Override
	public AccountDto createAccount(AccountDto accountDto) {
		Account account = AccountMapper.mapToAccount(accountDto);
		Account savedAccount = accountRepository.save(account);
		return AccountMapper.mapToAccountDto(savedAccount);
	}


	@Override
	public AccountDto getAccountByID(Long id) {
		Account account =  accountRepository.findById(id).orElseThrow(() -> new RuntimeException("Account does not exist"));
		return AccountMapper.mapToAccountDto(account);
	}


	@Override
	public AccountDto deposit(Long id, double amount) {
		Account account =  accountRepository.findById(id).orElseThrow(() -> new RuntimeException("Account does not exist"));
		double total = account.getBalance() + amount;
		account.setBalance(total);
		Account savedAccount = accountRepository.save(account);
		return AccountMapper.mapToAccountDto(savedAccount);
	}


	@Override
	public AccountDto withdraw(Long id, double amount) {
		Account account =  accountRepository.findById(id).orElseThrow(() -> new RuntimeException("Account does not exist"));
		if(account.getBalance() < amount) {
			throw new RuntimeException("Insufficient amount");
		}
		
		double total = account.getBalance() - amount;
		account.setBalance(total);
		Account savedAccount = accountRepository.save(account);
		
		return AccountMapper.mapToAccountDto(savedAccount);
	}


	@Override
	public List<AccountDto> getAllAccouunts() {
		List<Account> accounts = accountRepository.findAll();
		return accounts.stream().map((account) -> AccountMapper.mapToAccountDto(account)).collect(Collectors.toList());
	}


	@Override
	public void deleteAccount(Long id) {
		Account account =  accountRepository.findById(id).orElseThrow(() -> new RuntimeException("Account does not exist"));
		accountRepository.deleteById(id);

		
		
	}

}
