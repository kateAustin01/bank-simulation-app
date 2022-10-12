package com.cydeo.controller;

import com.cydeo.model.Account;
import com.cydeo.model.Transaction;
import com.cydeo.service.AccountService;
import com.cydeo.service.TransactionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
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
        //we need all accounts to provide them as sender,receiver
        model.addAttribute("accounts",accountService.listAllAccount());
        //we need empty transaction object to get info from UI
        model.addAttribute("transaction", Transaction.builder().build());
        //we need list of last 10 transactions
        model.addAttribute("lastTransactions",transactionService.lastTransactions());

        return "transaction/make-transfer";
    }

    @PostMapping("/transfer")
    public String makeTransfer(@ModelAttribute("transactions") Transaction transaction){
        //we need all accounts to provide them as sender,receiver

        transactionService.makeTransfer(transaction.)

        return "transaction/make-transfer";
    }
}
