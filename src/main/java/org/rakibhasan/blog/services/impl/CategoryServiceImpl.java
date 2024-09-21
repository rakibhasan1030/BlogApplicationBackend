package org.rakibhasan.blog.services.impl;

import org.modelmapper.ModelMapper;
import org.rakibhasan.blog.entities.Category;
import org.rakibhasan.blog.exceptions.ResourceNotFoundException;
import org.rakibhasan.blog.payloads.CategoryDto;
import org.rakibhasan.blog.repositories.CategoryRepository;
import org.rakibhasan.blog.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class CategoryServiceImpl implements CategoryService {

    CategoryRepository categoryRepository;
    private ModelMapper modelMapper;

    public CategoryServiceImpl(CategoryRepository categoryRepository, ModelMapper modelMapper) {
        this.categoryRepository = categoryRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public CategoryDto createCategory(CategoryDto categoryDto) {
        Category mappedCategory = this.modelMapper.map(categoryDto, Category.class);
        Category addedCategory = this.categoryRepository.save(mappedCategory);
        return this.modelMapper.map(addedCategory, CategoryDto.class);
    }

    @Override
    public CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId) {
        Category cat = this.categoryRepository.findById(categoryId).orElseThrow((
                () -> new ResourceNotFoundException(categoryDto.getCategoryTitle(), categoryId)
        ));
        cat.setCategoryTitle(categoryDto.getCategoryTitle());
        cat.setCategoryDescription(categoryDto.getCategoryDescription());
        Category updatedCategory = this.categoryRepository.save(cat);
        return this.modelMapper.map(updatedCategory, CategoryDto.class);
    }

    @Override
    public CategoryDto getCategory(Integer categoryId) {
        Category cat = this.categoryRepository.findById(categoryId).orElseThrow((
                () -> new ResourceNotFoundException(categoryId)
        ));
        return this.modelMapper.map(cat, CategoryDto.class);
    }

    @Override
    public List<CategoryDto> getAllCategories() {
        List<Category> categories = this.categoryRepository.findAll();
        return categories.stream().map(category -> this.modelMapper.map(category, CategoryDto.class)).collect(Collectors.toList());
    }

    @Override
    public void deleteCategory(Integer categoryId) {
        Category cat = this.categoryRepository.findById(categoryId).orElseThrow((
                () -> new ResourceNotFoundException(categoryId)
        ));
        this.categoryRepository.delete(cat);
    }

    @Override
    public void deleteAllCategories() {

    }

}
