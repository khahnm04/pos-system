package com.khahnm04.service.impl;

import com.khahnm04.domain.UserRole;
import com.khahnm04.exception.UserException;
import com.khahnm04.mapper.CategoryMapper;
import com.khahnm04.model.Category;
import com.khahnm04.model.Store;
import com.khahnm04.model.User;
import com.khahnm04.payload.dto.CategoryDTO;
import com.khahnm04.repository.CategoryRepository;
import com.khahnm04.repository.StoreRepository;
import com.khahnm04.service.CategoryService;
import com.khahnm04.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final UserService userService;
    private final StoreRepository storeRepository;

    @Override
    public CategoryDTO createCategory(CategoryDTO categoryDTO) throws Exception {
        User user = userService.getCurrentUser();
        Store store = storeRepository.findById(categoryDTO.getStoreId())
                .orElseThrow(() -> new Exception("Store not found"));
        Category category = Category.builder()
                .store(store)
                .name(categoryDTO.getName())
                .build();
        checkAuthority(user, category.getStore());
        return CategoryMapper.toDTO(categoryRepository.save(category));
    }

    @Override
    public List<CategoryDTO> getCategoryByStore(Long storeId) {
        List<Category> categories = categoryRepository.findByStoreId(storeId);
        return categories.stream()
                .map(CategoryMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public CategoryDTO updateCategory(Long id, CategoryDTO categoryDTO) throws Exception {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new Exception("category not found"));
        User user = userService.getCurrentUser();
        category.setName(categoryDTO.getName());
        checkAuthority(user, category.getStore());
        return CategoryMapper.toDTO(categoryRepository.save(category));
    }

    @Override
    public void deleteCategory(Long id) throws Exception {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new Exception("category not found"));
        User user = userService.getCurrentUser();
        checkAuthority(user, category.getStore());
        categoryRepository.delete(category);
    }

    private void checkAuthority(User user, Store store) throws Exception {
        boolean isAdmin = user.getRole().equals(UserRole.ROLE_STORE_ADMIN);
        boolean isManager = user.getRole().equals(UserRole.ROLE_STORE_MANAGER);
        boolean isSameStore =  user.equals(store.getStoreAdmin());
        if (!(isAdmin && isSameStore) && !isManager) {
            throw new Exception("you don't have permission to manage this category");
        }
    }

}
