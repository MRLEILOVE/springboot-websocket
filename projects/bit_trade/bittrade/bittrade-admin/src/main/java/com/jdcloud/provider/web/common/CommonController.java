package com.jdcloud.provider.web.common;

import com.jdcloud.provider.dto.AgntDto;
import com.jdcloud.provider.dto.IndexDataDto;
import com.jdcloud.provider.pojo.UserAccount;
import com.jdcloud.provider.service.AgntUserService;
import com.jdcloud.provider.service.FinanceSettingService;
import com.jdcloud.provider.service.StatisticsService;
import com.jdcloud.provider.utils.FileUtils;
import com.jdcloud.util.wrapper.WrapMapper;
import com.jdcloud.util.wrapper.Wrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

/**
 * 通用请求处理
 * 
 * @author ruoyi
 */
@Controller
public class CommonController {
	private static final Logger		log	= LoggerFactory.getLogger( CommonController.class );

	@Autowired
	private FinanceSettingService	financeSettingService;

	@Autowired
	private AgntUserService			agntUserService;

	@Autowired
	private StatisticsService		statisticsService;

	@RequestMapping("common/download")
	public void fileDownload(String fileName, Boolean delete, HttpServletResponse response, HttpServletRequest request) {
		String realFileName = System.currentTimeMillis() + fileName.substring( fileName.indexOf( "_" ) + 1 );
		try {
			String filePath = System.getProperty( "java.io.tmpdir" ) + fileName;

			response.setCharacterEncoding( "utf-8" );
			response.setContentType( "multipart/form-data" );
			response.setHeader( "Content-Disposition", "attachment;fileName=" + setFileDownloadHeader( request, realFileName ) );
			FileUtils.writeBytes( filePath, response.getOutputStream() );
			if (delete) {
				FileUtils.deleteFile( filePath );
			}
		} catch (Exception e) {
			log.error( "下载文件失败", e );
		}
	}

	public String setFileDownloadHeader(HttpServletRequest request, String fileName) throws UnsupportedEncodingException {
		final String agent = request.getHeader( "USER-AGENT" );
		String filename = fileName;
		if (agent.contains( "MSIE" )) {
			// IE浏览器
			filename = URLEncoder.encode( filename, "utf-8" );
			filename = filename.replace( "+", " " );
		} else if (agent.contains( "Firefox" )) {
			// 火狐浏览器
			filename = new String( fileName.getBytes(), "ISO8859-1" );
		} else if (agent.contains( "Chrome" )) {
			// google浏览器
			filename = URLEncoder.encode( filename, "utf-8" );
		} else {
			// 其它浏览器
			filename = URLEncoder.encode( filename, "utf-8" );
		}
		return filename;
	}

	/**
	 * 查询用户<br>
	 *
	 * @return:
	 * @Author: yongheng
	 * @Date: 2018/11/24 15:34
	 */
	@PostMapping(value = "common/user/query", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public Wrapper<List<UserAccount>> queryUser(String keyword) {
		return financeSettingService.queryUser( keyword );
	}

	/**
	 * 查询上级代理<br>
	 *
	 * @return:
	 * @Author: yongheng
	 * @Date: 2018/11/24 15:34
	 */
	@PostMapping(value = "common/agnt/query", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public Wrapper<List<AgntDto>> queryAgnt(AgntDto agntDto) {
		return agntUserService.queryAgnt( agntDto );
	}

	/**
	 * 主业数据<br>
	 *
	 * @return:
	 * @Author: yongheng
	 * @Date: 2018/12/19 15:01
	 */
	@PostMapping(value = "common/indexDataInit", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public Wrapper<IndexDataDto> indexDataInit() {
		return WrapMapper.ok( statisticsService.indexDataInit() );
	}
}
