package com.wing.yjyw.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wing.yjyw.common.R;
import com.wing.yjyw.entity.OneZeroThree;
import com.wing.yjyw.entity.bo.OneZeroThreeBo;
import com.wing.yjyw.service.OneZeroThreeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Calendar;
import java.util.Date;

/**
 * @Description: 基础控制器
 */
@Slf4j
@CrossOrigin
@RestController
@RequestMapping("/base")
public class BaseController {

    @Autowired
    private OneZeroThreeService baseService;

    @GetMapping("/addData")
    public R addData(OneZeroThreeBo bo) {
        OneZeroThree oneZeroThree = new OneZeroThree();
        oneZeroThree.setTemp(bo.getTemp());
        oneZeroThree.setHumi(bo.getHumi());
        oneZeroThree.setLight(String.valueOf(Integer.parseInt(bo.getLight()) * 50000 / 4095));
        oneZeroThree.setSoil(String.valueOf(Integer.parseInt(bo.getSoil()) * 100 / 4095));
        // 获取当前时间
        Calendar calendar = Calendar.getInstance();
        // 获取当前时间的毫秒值 减去16个小时
        long newTimeMillis = calendar.getTimeInMillis() - (16 * 60 * 60 * 1000);
        // 将新的毫秒值设置为Calendar的时间
        calendar.setTimeInMillis(newTimeMillis);
        oneZeroThree.setCreateTime(calendar.getTime());
        baseService.save(oneZeroThree);
        return R.success("success");
    }

    @GetMapping("/getNewData")
    public R getNewData() {
        LambdaQueryWrapper<OneZeroThree> wrapper = new LambdaQueryWrapper<>();
        wrapper.orderByDesc(OneZeroThree::getCreateTime);
        wrapper.last("limit 1");
        OneZeroThree one = baseService.getOne(wrapper);
        Date currentDate = new Date();
        // 计算时间差（毫秒）
        long diffInMilliSeconds = currentDate.getTime() - one.getCreateTime().getTime();
        // 将时间差转换为秒
        long diffInSeconds = diffInMilliSeconds / 1000;
        String res;
        if(diffInSeconds  < 360){
            res = "温度:" + one.getTemp() + "℃  空气湿度:" + one.getHumi() + "%RH  光照强度:" + one.getLight() + "Lux  土壤湿度:" + one.getSoil() + "%RH"; //
        }else{
            res = "OFF-LINE";
        }
        return R.success(res);
    }

    @GetMapping("/getPage")
    public R getPage(OneZeroThreeBo bo) {
        Page<OneZeroThree> page = new Page<>(bo.getPageNum(), bo.getPageSize());
        LambdaQueryWrapper<OneZeroThree> wrapper = new LambdaQueryWrapper<>();
        wrapper.orderByDesc(OneZeroThree::getCreateTime);
        baseService.page(page, wrapper);
        return R.success(page);
    }

    @GetMapping("/getList")
    public R getPage() {
        Page<OneZeroThree> page = new Page<>(1, 20);
        LambdaQueryWrapper<OneZeroThree> wrapper = new LambdaQueryWrapper<>();
        wrapper.orderByDesc(OneZeroThree::getCreateTime);
        baseService.page(page, wrapper);
        return R.success(page);
    }
}
