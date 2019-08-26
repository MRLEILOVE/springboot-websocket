package com.jdcloud.provider.web.personalPartner;


import com.baomidou.mybatisplus.plugins.Page;
import com.jdcloud.provider.dto.PersonalPartnerDto;
import com.jdcloud.provider.pojo.vo.PersonalPartnerVo;
import com.jdcloud.provider.service.PersonalPartnerService;
import com.jdcloud.provider.web.base.BaseController;
import com.jdcloud.util.wrapper.TableDataInfo;
import com.jdcloud.util.wrapper.Wrapper;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * <p>
 *  节点 前端控制器
 * </p>
 *
 * @author ourblue
 * @since 2019-04-09
 */
@Controller
@RequestMapping("/node/personalPartner")
public class PersonalPartnerController extends BaseController {

    @Autowired
    private PersonalPartnerService personalPartnerService;

    private String prefix = "node/personalPartner";

    @RequiresPermissions("personalPartner:view")
    @GetMapping()
    public String orderUser() {
        return prefix + "/list";
    }

    /**
     * 查询用户节点列表
     * @param personalPartnerDto
     * @return
     */
    @RequiresPermissions("personalPartner:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo personalPartnerList(PersonalPartnerDto personalPartnerDto) {
        Page<PersonalPartnerVo> page = personalPartnerService.personalPartnerList(getPage(), personalPartnerDto);
        return new TableDataInfo(page.getRecords(), page.getTotal());
    }

    /**
     * 删除
     */
    @RequiresPermissions("personalPartner:delete")
    @PostMapping("/remove")
    @Transactional(rollbackFor = Exception.class)
    @ResponseBody
    public Wrapper<String> personalPartnerDelete(String ids) {
        return toAjax( personalPartnerService.personalPartnerDeleteByIds(ids) );
    }


}

