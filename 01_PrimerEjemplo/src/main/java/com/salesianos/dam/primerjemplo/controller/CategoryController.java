package com.salesianos.dam.primerjemplo.controller;


import com.salesianos.dam.primerjemplo.dto.EditCategoriaDTO;
import com.salesianos.dam.primerjemplo.dto.GetCategoryDetail;
import com.salesianos.dam.primerjemplo.dto.GetCategoryList;
import com.salesianos.dam.primerjemplo.model.Category;
import com.salesianos.dam.primerjemplo.repo.CategoryRepository;
import com.salesianos.dam.primerjemplo.service.CategoryService;
import com.salesianos.dam.primerjemplo.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/category")
public class CategoryController {

private final CategoryRepository categoryRepository;
private final CategoryService categoryService;
    private final ProductService productService;

    @PostMapping
    public ResponseEntity<GetCategoryDetail> addCategory(@RequestBody EditCategoriaDTO category) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(GetCategoryDetail.of(categoryService.addCategory(category)));
    }

    @GetMapping
    public ResponseEntity<List<GetCategoryList>> getAllCategories(){
        List<Category> lista = categoryService.getAllCategories();

        return ResponseEntity.ok(
                lista
                        .stream()
                        .map(GetCategoryList::of)
                        .toList());
    }


    @GetMapping("/{id}")
    public ResponseEntity<GetCategoryDetail> getCategoryById(@PathVariable Long id){
        return ResponseEntity.ok(GetCategoryDetail.of(categoryService.getCategoryById(id)));
    }



    @PutMapping("/{id}")
    public ResponseEntity<GetCategoryDetail> updateCategory(@PathVariable Long id, @RequestBody EditCategoriaDTO c){
            return ResponseEntity.ok(
                    GetCategoryDetail.of(categoryService.updateCategory(id,c)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id){
        categoryService.deleteCategory(id);
        return ResponseEntity.noContent().build();
    }



}
