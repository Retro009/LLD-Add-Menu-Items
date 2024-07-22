package com.scaler.services;

import com.scaler.exceptions.UnAuthorizedAccess;
import com.scaler.exceptions.UserNotFoundException;
import com.scaler.models.*;
import com.scaler.repositories.MenuRepository;
import com.scaler.repositories.UserRepository;

public class MenuServiceImpl implements MenuService{
    MenuRepository menuRepository;
    UserRepository userRepository;

    public MenuServiceImpl(MenuRepository menuRepository, UserRepository userRepository){
        this.menuRepository=menuRepository;
        this.userRepository=userRepository;
    }
    @Override
    public MenuItem addMenuItem(long userId, String name, double price, String dietaryRequirement, String itemType, String description) throws UserNotFoundException, UnAuthorizedAccess {
        User user = userRepository.findById(userId).orElseThrow(()-> new UserNotFoundException("Invalid User"));
        if(user.getUserType() != UserType.ADMIN)
            throw new UnAuthorizedAccess("ACCESS DENIED");
        MenuItem menuItem = new MenuItem();
        menuItem.setName(name);
        menuItem.setPrice(price);
        menuItem.setDietaryRequirement(DietaryRequirement.valueOf(dietaryRequirement));
        menuItem.setItemType(ItemType.valueOf(itemType));
        menuItem.setDescription(description);

        return menuRepository.add(menuItem);
    }
}
