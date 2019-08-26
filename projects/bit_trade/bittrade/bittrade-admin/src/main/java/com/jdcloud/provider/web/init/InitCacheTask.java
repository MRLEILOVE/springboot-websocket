package com.jdcloud.provider.web.init;

import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import com.jdcloud.base.enums.DictDataEnum;
import com.jdcloud.base.enums.DictTypeEnum;
import com.jdcloud.core.redis.RedisKeyUtil;
import com.jdcloud.core.redis.RedisTemplate;
import com.jdcloud.core.utils.SysDictCacheUtil;
import com.jdcloud.provider.pojo.AccountConfig;
import com.jdcloud.provider.pojo.ParameterConfig;
import com.jdcloud.provider.pojo.SysDictData;
import com.jdcloud.provider.pojo.SysDictType;
import com.jdcloud.provider.service.AccountConfigService;
import com.jdcloud.provider.service.ParameterConfigService;
import com.jdcloud.provider.service.SysDictDataService;
import com.jdcloud.provider.service.SysDictTypeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author leigq
 * 初始化缓存任务，系统启动仅执行一次
 */
@Component
@Slf4j
public class InitCacheTask implements ApplicationRunner {

    private final SysDictTypeService dictTypeService;
    private final RedisTemplate redisTemplate;
    private final SysDictDataService sysDictDataService;
    private final ParameterConfigService parameterConfigService;
    private final AccountConfigService accountConfigService;

    @Autowired
    private SysDictCacheUtil sysDictCacheUtil;

    @Autowired
    public InitCacheTask(SysDictTypeService dictTypeService, RedisTemplate redisTemplate, SysDictDataService sysDictDataService,
                         ParameterConfigService parameterConfigService,AccountConfigService accountConfigService) {
        this.dictTypeService = dictTypeService;
        this.redisTemplate = redisTemplate;
        this.sysDictDataService = sysDictDataService;
        this.parameterConfigService = parameterConfigService;
        this.accountConfigService=accountConfigService;
    }

    @Override
    public void run(ApplicationArguments applicationArguments) {
        initDictData();
        initParameterConfigData();
        initAccountConfigDtoData();
//        System.out.println(sysDictCacheUtil.getCacheDictValue(DictTypeEnum.APP_TYPE, DictDataEnum.APP_TYPE_H5));
    }

    private void initDictData() {
        log.warn("字典数据开始同步至缓存......");
        /*
         * 思路：
         * 1、查询出所有字典类型
         * 2、根据字典类型查询对应字典数据
         * 3、根据字典类型将字典数据进行分组缓存 key格式：dict:字典类型:字典编码，like : dict:sys_user_sex:1
         * */
        List<SysDictType> sysDictTypes = dictTypeService.selectDictTypeAll();

        sysDictTypes.forEach(sysDictType -> {
            // 字典类型
            String dictType = sysDictType.getDictType();
            // 查询出所有该字典类型的字典数据
            List<SysDictData> sysDictDataList = sysDictDataService.selectDictDataByType(dictType);
            sysDictDataList.forEach(sysDictData -> {
                redisTemplate.set(RedisKeyUtil.DICT_DATA_CACHE_PREF + dictType + ":" + sysDictData.getDictCode(),
                        sysDictData.getDictValue());
            });
        });
        log.warn("字典数据已全部同步至缓存......");
    }

    private void initParameterConfigData() {
        log.warn("参数设置初始化至缓存start......");
        redisTemplate.delete(redisTemplate.keys(RedisKeyUtil.PARAMETER_CONFIG_PREF + "*"));

        List<ParameterConfig> parameterConfigs = parameterConfigService.selectList(null);
        parameterConfigs.forEach(parameterConfig -> {
            redisTemplate.set(RedisKeyUtil.PARAMETER_CONFIG_PREF + parameterConfig.getDictionaryKey(), parameterConfig.getDictionaryValue());
        });
        log.warn("参数设置初始化至缓存end......");
    }

    /**
     * @Description: 初始化币种配置至缓存
     * @Author: zjun
     * @Date: 2019/7/19 15:32
     */
    private void initAccountConfigDtoData() {
        log.warn("币种配置初始化至缓存start......");
        redisTemplate.delete(redisTemplate.keys(RedisKeyUtil.ACCOUNT_CONFIG_ID + "*"));
        redisTemplate.delete(redisTemplate.keys(RedisKeyUtil.ACCOUNT_CONFIG_TYPE + "*"));
        List<AccountConfig> accountConfigList =accountConfigService.selectList(null);
        JSONArray jsonArray = new JSONArray();
        accountConfigList.forEach(accountConfig -> {
            JSONObject json = new JSONObject(accountConfig);
            jsonArray.add(json);
            // 保存单个
            redisTemplate.set(RedisKeyUtil.ACCOUNT_CONFIG_ID + accountConfig.getId(), json);
            redisTemplate.set(RedisKeyUtil.ACCOUNT_CONFIG_TYPE + accountConfig.getAccountType(), json);
        });
        // 保存所有
        redisTemplate.set(RedisKeyUtil.ACCOUNT_CONFIG_ID + "all", jsonArray);
        log.warn("币种配置初始化至缓存end......");
    }
}
