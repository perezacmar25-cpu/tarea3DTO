package com.salesianos.dam.primerjemplo.service;


import com.salesianos.dam.primerjemplo.dto.EditCategoriaDTO;
import com.salesianos.dam.primerjemplo.error.CategoryNotFoundException;
import com.salesianos.dam.primerjemplo.model.Category;
import com.salesianos.dam.primerjemplo.repo.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;



    public List<Category> getAllCategories(){
        List<Category> lista = categoryRepository.findAll();
        if(lista.isEmpty()){
            return null;
        }
        return lista;

    }


    public Category getCategoryById(Long id){
        return categoryRepository.findById(id).orElse(null);

    }

    public Category addCategory(EditCategoriaDTO editCategoriaDTO){
        validateCategory(editCategoriaDTO);
        return categoryRepository.save(editCategoriaDTO.to());
    }

    public void validateCategory(EditCategoriaDTO editCategoriaDTO){
        if(!StringUtils.hasText(editCategoriaDTO.name())){
            throw new CategoryNotFoundException("Categoría inválida");
        }
    }

    public void deleteCategory(Long id){

        categoryRepository.deleteById(id);
    }

    public Category updateCategory(Long id, EditCategoriaDTO editCategoriaDTO) {
        validateCategory(editCategoriaDTO);

        return categoryRepository.findById(id)
                .map(c -> {
                    c.setName(editCategoriaDTO.name());
                    return categoryRepository.save(c);
                })
                .orElseThrow(() -> new CategoryNotFoundException("Categoría no encontrada con id: " + id));
    }
}
