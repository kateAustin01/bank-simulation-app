package com.cydeo.controller;

import com.cydeo.service.AccountService;
import com.cydeo.service.TransactionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class TransactionController {
    private final AccountService accountService;
    private final TransactionService transactionService;



    public TransactionController(AccountService accountService, TransactionService transactionService) {
        this.accountService = accountService;
        this.transactionService = transactionService;
    }

    @GetMapping("/make-transfer")
    public String makeTransfer(Model model){
        model.addAttribute("accounts",accountService.listAllAccount());

        return "transaction/make-transfer";
    }
}
