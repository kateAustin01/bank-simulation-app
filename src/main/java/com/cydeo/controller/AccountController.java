package com.cydeo.controller;

import com.cydeo.enums.AccountStatus;
import com.cydeo.enums.AccountType;
import com.cydeo.model.Account;
import com.cydeo.service.AccountService;
import com.cydeo.service.TransactionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.UUID;

@Controller
@RequestMapping
public class AccountController {

    private final AccountService accountService;
    private final TransactionService transactionService;

    public AccountController(AccountService accountService,TransactionService transactionService) {
        this.accountService = accountService;
        this.transactionService = transactionService;
    }


    @GetMapping("/index")
    public String getIndex(Model model){
        model.addAttribute("accountList",accountService.listAllAccount());

        return "account/index";
    }

    @GetMapping("/create-form")
    public String getCreateForm(Model model){

        model.addAttribute("account", Account.builder().build());
        model.addAttribute("accountTypes", AccountType.values());

        return "account/create-account";
    }

    //create a /create post method that creates account in the service
    //then return the index page
    @PostMapping("/create")
    public String createAccount(@ModelAttribute("account") Account account){

        accountService.createNewAccount(account.getBalance(),new Date(),
                account.getAccountType(),account.getUserId());

        return "redirect:/index";
    }

    @GetMapping  ("/delete/{id}")
    public String getDeleteAccount(@PathVariable("id") UUID id) {


        accountService.deleteAccount(id);

        System.out.println(id);
        return "redirect:/index";
    }



}
