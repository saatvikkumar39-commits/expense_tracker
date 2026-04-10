package com.saatvik.expense_tracker.service;

import com.saatvik.expense_tracker.dto.ExpenseRequest;
import com.saatvik.expense_tracker.dto.ExpenseResponse;
import com.saatvik.expense_tracker.model.Category;
import com.saatvik.expense_tracker.model.Expense;
import com.saatvik.expense_tracker.model.User;
import com.saatvik.expense_tracker.repository.CategoryRepository;
import com.saatvik.expense_tracker.repository.ExpenseRepository;
import com.saatvik.expense_tracker.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ExpenseService {
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;
    private final ExpenseRepository expenseRepository;

    public ExpenseResponse toResponse(Expense expense){
        ExpenseResponse response=new ExpenseResponse();
        response.setId(expense.getId());
        response.setTitle(expense.getTitle());
        response.setNote(expense.getNote());
        response.setAmount(expense.getAmount());
        response.setCreatedOn(expense.getCreatedOn());
        response.setCategory(expense.getCategory().getName());
        return response;
    }

    public ExpenseResponse create(Long userId, ExpenseRequest request){
        User user=userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        Category category=categoryRepository.findById(request.getCategoryId()).orElseThrow(()->new RuntimeException("Category in Request invalid"));
        Expense expense=new Expense();
        expense.setAmount(request.getAmount());
        expense.setTitle(request.getTitle());
        expense.setNote(request.getNote());
        expense.setCreatedOn(request.getCreatedOn());
        expense.setCategory(category);
        expense.setUser(user);
        return toResponse(expense);
    }

    public List<ExpenseResponse> getAllByUser(Long userId){
        return expenseRepository.findByUserId((userId)).stream().map(this::toResponse).toList();
    }

    public ExpenseResponse getById(Long id){
        return toResponse(expenseRepository.findById(id).orElseThrow(()->new RuntimeException("Expense Id does not Exist")));
    }

    public ExpenseResponse update(Long id,ExpenseRequest request){
        Expense expense=expenseRepository.findById(id).orElseThrow(()->new RuntimeException("Expense with given Id does not exist"));
        expense.setTitle(request.getTitle());
        expense.setAmount(request.getAmount());
        expense.setCreatedOn(request.getCreatedOn());
        expense.setNote(request.getNote());
        expense.setCategory(categoryRepository.findById(request.getCategoryId()).orElseThrow(()->new RuntimeException("Category with gien ID does not exist")));
        return toResponse(expense);
    }

    public void delete(Long id) {
        if (!expenseRepository.existsById(id)) {
            throw new RuntimeException("Expense not found with id: " + id);
        }
        expenseRepository.deleteById(id);
    }
}
