package com.robin.bos.service.system.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.robin.bos.dao.system.MenuRepository;
import com.robin.bos.domain.system.Menu;
import com.robin.bos.domain.system.User;
import com.robin.bos.service.system.MenuService;

/**  
 * ClassName:MenuServiceImpl <br/>  
 * Function:  <br/>  
 * Date:     2018年3月28日 下午9:31:03 <br/>       
 */
@Component
@Transactional
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuRepository menuRepository;
    
    @Override
    public List<Menu> findLevelOne() {
        return menuRepository.findByParentMenuIsNull();
    }

    @Override
    public Menu save(Menu menu) {
        return menuRepository.save(menu);
    }

    @Override
    public Page<Menu> findAll(Pageable pageable) {
        return menuRepository.findAll(pageable);
    }

    @Override
    public Menu findOne(Long id) {
        return menuRepository.findOne(id);
    }

    @Override
    public List<Menu> findbyUser(User user) {
       
        if(user == null){
            System.out.println("未登录!");
            return null;
        }
        //1.如果用户是admin,则直接展示所有菜单
        if(user.getUsername().equals("admin")){
            return menuRepository.findAll();
        }
        else{
            return menuRepository.findbyUser(user.getId());
        }
    }

}
  
