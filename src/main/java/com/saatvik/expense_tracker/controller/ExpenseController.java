package com.saatvik.expense_tracker.controller;

import com.saatvik.expense_tracker.dto.ExpenseRequest;
import com.saatvik.expense_tracker.dto.ExpenseResponse;
import com.saatvik.expense_tracker.service.ExpenseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/expenses")
@RequiredArgsConstructor
public class ExpenseController {
    private ExpenseService expenseService;

    @PostMapping("/user/{userId}")
    public ResponseEntity<ExpenseResponse> create(@PathVariable Long userId, ExpenseRequest request){
        return ResponseEntity.status(HttpStatus.CREATED).body(expenseService.create(userId,request));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<ExpenseResponse>> getAllByUserId(@PathVariable Long userId){
        return ResponseEntity.ok(expenseService.getAllByUser(userId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ExpenseResponse> getById(@PathVariable Long id){
        return ResponseEntity.ok(expenseService.getById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ExpenseResponse> update(@PathVariable Long id,@Valid @RequestBody ExpenseRequest request){
        return ResponseEntity.ok(expenseService.update(id,request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        expenseService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
