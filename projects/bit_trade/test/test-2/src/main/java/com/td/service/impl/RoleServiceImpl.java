package com.td.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.td.entity.Role;
import com.td.service.RoleService;
import com.td.vo.ResponseVO;
import com.td.vo.RoleVO;

@Service
public class RoleServiceImpl implements RoleService {

	// @Autowired
	// private RoleRepository roleRepository;

	@Override
	public ResponseVO findAllRoleVO() {
		// List<Role> rolePOList = roleRepository.findAll();
		List<Role> rolePOList = new ArrayList<Role>() {
			{

			}
		};
		List<RoleVO> roleVOList = new ArrayList<>();
		rolePOList.forEach( rolePO -> {
			RoleVO roleVO = new RoleVO();
			BeanUtils.copyProperties( rolePO, roleVO );
			roleVOList.add( roleVO );
		} );
		return ResponseVO.success( roleVOList );
	}

	@Override
	public Role findById(Integer id) {
//		return roleRepository.findById( id ).get();
		return new Role();
	}

}
