package com.bittrade.admin.controller.c2c;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bittrade.admin.model.vo.c2c.AdvertInfoScreeningParameterVo;
import com.bittrade.admin.service.c2c.ITAdvertInfoService;
import com.bittrade.pojo.dto.TAdvertInfoDTO;
import com.bittrade.pojo.model.TAdvertInfo;
import com.bittrade.pojo.vo.TAdvertInfoVO;
import com.core.common.DTO.PageDTO;
import com.core.framework.base.controller.BaseController;

/**
 * @author Administrator
 */
@Controller
@ResponseBody
@RequestMapping(value = {"/tAdvertInfo"}, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class TAdvertInfoController extends BaseController<TAdvertInfo, TAdvertInfoDTO, TAdvertInfoVO, ITAdvertInfoService> {

    @Autowired
    private ITAdvertInfoService advertInfoService;

    /**
     * 显示tab
     */
    @GetMapping("displayPage")
    private String displayPage() {
        return "";
    }

    @ResponseBody
    @PostMapping("/findList")
    private PageDTO<TAdvertInfo> findList(AdvertInfoScreeningParameterVo parameterVo, @RequestBody PageDTO<TAdvertInfo> pageDTO) {
        return null;
    }

}
