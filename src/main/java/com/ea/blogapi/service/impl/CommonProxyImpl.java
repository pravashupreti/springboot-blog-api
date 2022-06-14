package com.ea.blogapi.service.impl;

import com.ea.blogapi.exception.ProxyException;
import com.ea.blogapi.payload.ProxyRequest;
import com.ea.blogapi.service.CommonProxyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Enumeration;

@Service
public class CommonProxyImpl implements CommonProxyService {

    @Autowired
    RestTemplate restTemplate;


    public ResponseEntity forward(String proxyServer, Integer proxyPort,ProxyRequest proxyRequest) throws URISyntaxException {


        String requestUrl = proxyRequest.getRequest().getRequestURI();

        URI uri = new URI("http", null, proxyServer, proxyPort, null, null, null);
        uri = UriComponentsBuilder.fromUri(uri)
                .path(requestUrl)
                .query(proxyRequest.getRequest().getQueryString())
                .build(true).toUri();

        HttpHeaders headers = new HttpHeaders();
        Enumeration<String> headerNames = proxyRequest.getRequest().getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String headerName = headerNames.nextElement();
            headers.set(headerName, proxyRequest.getRequest().getHeader(headerName));
        }

        HttpEntity<String> httpEntity = new HttpEntity<>(proxyRequest.getBody(), headers);
        RestTemplate restTemplate = new RestTemplate();
        try {
            return restTemplate.exchange(uri, proxyRequest.getMethod(), httpEntity, String.class);
        } catch(HttpStatusCodeException e) {

            throw new ProxyException( "Error from service "+ proxyServer + ":" + proxyPort + e.getResponseBodyAsString() );
//                    e.getResponseHeaders() +  e.getResponseBodyAsString());

        }
    }
}
