package com.jdcloud.provider.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.jdcloud.base.constant.GlobalConstant;
import com.jdcloud.base.enums.ConstantEnum;
import com.jdcloud.provider.mapper.AuditRecordMapper;
import com.jdcloud.provider.model.dto.UserAuthenticationDto;
import com.jdcloud.provider.pojo.AuditRecord;
import com.jdcloud.provider.pojo.UserAccount;
import com.jdcloud.provider.service.AuditRecordService;
import com.jdcloud.provider.service.UserAccountService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author yongheng
 * @since 2018-12-03
 */
@Service
public class AuditRecordServiceImpl extends ServiceImpl<AuditRecordMapper, AuditRecord> implements AuditRecordService {

	@Autowired
	private UserAccountService userAccountService;

	/**
	 * 保存记录<br>
	 *
	 * @return:
	 * @Author: yongheng
	 * @Date: 2018/12/3 20:41
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	public void saveRecord(UserAuthenticationDto authenticationDto, String remark) {
		// 保存
		AuditRecord auditRecord = new AuditRecord();
		BeanUtils.copyProperties( authenticationDto, auditRecord );
		auditRecord.setAuditStatus( authenticationDto.getFhasRealValidate() );
		auditRecord.setAuditTime( new Date() );
		auditRecord.setAuditType( GlobalConstant.Number.ONE_1 + "" );
		auditRecord.setCreater( authenticationDto.getAuditor() );
		auditRecord.setCreateTime( new Date() );
		auditRecord.setAuditRemark( remark );
		baseMapper.insert( auditRecord );

		UserAccount account = new UserAccount();
		account.setUserId( authenticationDto.getUserId() );
		account = userAccountService.selectOne( new EntityWrapper<>( account ) );
		Integer status = authenticationDto.getFhasRealValidate();
		if (ConstantEnum.AuthenticationConstant.AUDITED.getCode().equals( status.toString() )) {
			account.setRealName( authenticationDto.getRealName() );
		} else {
			status = Integer.valueOf( ConstantEnum.AuthenticationConstant.REFUSE.getCode() );
		}
		account.setFhasRealValidate( status );
		userAccountService.updateAccountById( account );
	}

	/**
	 * 根据userId查询审核记录<br>
	 *
	 * @return:
	 * @Author: yongheng
	 * @Date: 2019/1/9 20:23
	 */
	@Override
	public AuditRecord selectByUserId(Long userId) {
		AuditRecord auditRecord = new AuditRecord();
		auditRecord.setAuditType( GlobalConstant.Number.ONE_1 + "" );
		auditRecord.setUserId( userId );
		return baseMapper.selectByUserId( auditRecord );
	}
}
