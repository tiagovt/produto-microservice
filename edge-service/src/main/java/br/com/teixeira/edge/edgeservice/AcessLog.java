package br.com.teixeira.edge.edgeservice;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StreamUtils;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

public class AcessLog extends ZuulFilter {

	private static Logger log = LoggerFactory.getLogger(AcessLog.class);

	@Override
	public boolean shouldFilter() {
		return true;
	}

	@Override
	public Object run() {
		RequestContext ctx = RequestContext.getCurrentContext();
		HttpServletRequest request = ctx.getRequest();
		log.info(String.format("%s request to %s", request.getMethod(), request.getRequestURL().toString()));
		try {
			InputStream in = request.getInputStream();
			StringBuilder stringBuilderHeader = new StringBuilder();
			Enumeration<String> headerNames = request.getHeaderNames();
			if (headerNames != null) {
				while (headerNames.hasMoreElements()) {
					String headerName = headerNames.nextElement().toString();
					stringBuilderHeader.append(headerName +": " + request.getHeader(headerName) + "\n");
				}
			}
			log.info("Headers: " + stringBuilderHeader.toString());
			StringBuilder stringBuilderParameter = new StringBuilder();
			Enumeration<String> parameterNames = request.getParameterNames();
			if (parameterNames != null) {
				while (parameterNames.hasMoreElements()) {
					String parameterName = parameterNames.nextElement().toString();
					stringBuilderParameter.append(String.format("%s : %s \n", parameterName, request.getParameter(parameterName)));
				}
			}
			log.info("Parameters: " + stringBuilderParameter.toString());
			String originBody = StreamUtils.copyToString(in, Charset.forName("UTF-8"));
			log.info(String.format("Body %s", originBody));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public String filterType() {
		return "pre";
	}

	@Override
	public int filterOrder() {
		return 1;
	}

}
