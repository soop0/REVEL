package error;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

// 에러페이지로 이동 
@Controller
@RequestMapping("/error/")
public class ErrorBean 
{
	@RequestMapping("error400.rv")
	public String error400() throws Exception
	{	return "/error/error400";	}
	
	@RequestMapping("error403.rv")
	public String error403() throws Exception
	{	return "/error/error403";	}
	
	@RequestMapping("error404.rv")
	public String error404() throws Exception
	{	return "/error/error404";	}
	
	@RequestMapping("error405.rv")
	public String error405() throws Exception
	{	return "/error/error405";	}
	
	@RequestMapping("error500.rv")
	public String error500() throws Exception
	{	return "/error/error500";	}
	
	@RequestMapping("error503.rv")
	public String error503() throws Exception
	{	return "/error/error503";	}
}
