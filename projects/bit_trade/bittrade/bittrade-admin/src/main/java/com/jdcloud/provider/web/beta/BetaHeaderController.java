package com.jdcloud.provider.web.beta;


import com.baomidou.mybatisplus.plugins.Page;
import com.jdcloud.base.enums.AnnotationEnum;
import com.jdcloud.provider.pojo.BetaHeader;
import com.jdcloud.provider.pojo.vo.BetaHeaderVo;
import com.jdcloud.provider.service.BetaHeaderService;
import com.jdcloud.provider.web.base.BaseController;
import com.jdcloud.util.annotation.Log;
import com.jdcloud.util.wrapper.TableDataInfo;
import com.jdcloud.util.wrapper.Wrapper;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * beta狗头像表 前端控制器
 * </p>
 *
 * @author ourblue
 * @since 2019-04-11
 */
@Controller
@RequestMapping("/beta/betaHeader")
public class BetaHeaderController extends BaseController {

    @Autowired
    private BetaHeaderService betaHeaderService;

    private String prefix = "beta/betaHeader";

    @RequiresPermissions("betaHeader:view")
    @GetMapping()
    public String list() {
        return prefix + "/list";
    }

    @GetMapping("/add")
    @RequiresPermissions("betaHeader:add")
    public String add(ModelMap mmap) {
        return prefix + "/add";
    }

    /**
     * 列表
     */
    @RequiresPermissions("betaHeader:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo betaHeaderList(){
        Page<BetaHeader> vo = betaHeaderService.betaHeaderPageList(getPage());
        return new TableDataInfo(vo.getRecords(), vo.getTotal());
    }

    /**
     * 删除头像
     * @param ids
     * @return
     */
    @Log(title = "头像", buinessType = AnnotationEnum.BusinessType.DELETE)
    @RequiresPermissions("betaHeader:delete")
    @PostMapping("/remove")
    @Transactional(rollbackFor = Exception.class)
    @ResponseBody
    public Wrapper<String> comboGroupdelete(String ids) {
        return toAjax( betaHeaderService.deletebetaHeaderByIds( ids ) );
    }

    /**
     * 编辑详情
     * @param id
     * @param mmap
     * @return
     */
    @RequiresPermissions("betaHeader:edit")
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id, ModelMap mmap) {
        BetaHeader header = betaHeaderService.selectById(id);
        mmap.put( "header", header );
        return prefix + "/edit";
    }


    /**
     * 保存或更新<br>
     *
     */
    @Log(title = "保存", buinessType = AnnotationEnum.BusinessType.INSERT)
    @RequiresPermissions("betaHeader:saveOrUpdate")
    @PostMapping("/saveOrUpdate")
    @ResponseBody
    public Wrapper<String> saveOrUpdate(BetaHeaderVo vo) {
        return betaHeaderService.saveOrUpdate(vo);
    }



}

