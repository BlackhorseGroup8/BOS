package com.robin.bos.service.base.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.robin.bos.dao.base.AreaRepository;
import com.robin.bos.dao.base.FixedAreaRepository;
import com.robin.bos.dao.base.SubAreaRepository;
import com.robin.bos.domain.base.Area;
import com.robin.bos.domain.base.FixedArea;
import com.robin.bos.domain.base.SubArea;
import com.robin.bos.service.base.SubAreaService;

/**  
 * ClassName:SubAreaServiceImpl <br/>  
 * Function:  <br/>  
 * Date:     2018年3月16日 下午4:31:42 <br/>       
 */
@Component
@Transactional
public class SubAreaServiceImpl implements SubAreaService {

    @Autowired
    private SubAreaRepository subAreaRepository;
    @Autowired
    private AreaRepository areaRepository;
    @Autowired
    private FixedAreaRepository fixedAreaRepository;
    @Override
    public SubArea save(SubArea model) {
        SubArea subArea=null;
        
        if(model.getId()==null){//保存
            subArea =subAreaRepository.save(model);
        }else{//更新
            Area area = areaRepository.findOne(model.getArea().getId());
            subAreaRepository.updateById(model.getStartNum(),model.getEndNum(),model.getSingle(),model.getKeyWords(),model.getAssistKeyWords(),area,model.getId());
            subArea=new SubArea();
        }
        return  subArea;
    }


    @Override
    public Page<SubArea> findAll(Pageable pageable) {
        return subAreaRepository.findAll(pageable);
    }


    @Override
    public List<SubArea> findSubByfixed(Long id) {
          
         FixedArea fixedArea = fixedAreaRepository.findOne(id);
        
        return subAreaRepository.findSubByfixed(fixedArea);
    }

    @Override
    public List<Object[]> subAreaChart() {

        return subAreaRepository.subAreaChart();
    }

}
  
