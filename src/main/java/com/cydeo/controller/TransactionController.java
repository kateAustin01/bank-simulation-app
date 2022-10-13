package com.cydeo.controller;

import com.cydeo.model.Account;
import com.cydeo.model.Transaction;
import com.cydeo.service.AccountService;
import com.cydeo.service.TransactionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.UUID;

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
    public String postMakeTransfer(@ModelAttribute("transaction") Transaction transaction){
        //we need all accounts to provide them as sender,receiver

        //I have UUID but I need to provide Account to make transfer method.
        Account sender = accountService.retrieveById(transaction.getSender());
        Account receiver = accountService.retrieveById(transaction.getReceiver());
        transactionService.makeTransfer(sender,receiver,transaction.getAmount(),new Date(),transaction.getMessage());

        return "redirect:/make-transfer";
    }

    @GetMapping("/transactions/{id}")
    public String getTransactionList(@PathVariable("id") UUID id,Model model){
//write a method, that gets the id from index.html and print on the console.
        //(work on index.html here)
        model.addAttribute("transactions", transactionService.findTransactionListById(id));

        return "transaction/transactions";
    }



}
