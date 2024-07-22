package com.scaler.repositories;

import com.scaler.models.DietaryRequirement;
import com.scaler.models.MenuItem;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MenuRepositoryImpl implements MenuRepository{
    List<MenuItem> menuItems = new ArrayList<>();
    @Override
    public MenuItem add(MenuItem menuItem) {
        menuItems.add(menuItem);
        return menuItem;
    }

    @Override
    public List<MenuItem> getAll() {
        return menuItems;
    }

    @Override
    public List<MenuItem> getByDietaryRequirement(DietaryRequirement dietaryRequirement) {
        return menuItems.stream().filter(menuItem -> menuItem.getDietaryRequirement().equals(dietaryRequirement.name())).collect(Collectors.toList());
    }
}
