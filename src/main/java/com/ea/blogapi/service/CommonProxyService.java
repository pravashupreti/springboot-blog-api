package com.ea.blogapi.service;

import com.ea.blogapi.payload.ProxyRequest;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletRequest;
import java.net.URISyntaxException;

public interface CommonProxyService {

    ResponseEntity forward(String proxyServer, Integer proxyPort,ProxyRequest proxyRequest) throws URISyntaxException;

}
