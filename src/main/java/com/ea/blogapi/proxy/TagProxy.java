package com.ea.blogapi.proxy;

import com.ea.blogapi.payload.ProxyRequest;
import com.ea.blogapi.service.impl.CommonProxyImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.net.URISyntaxException;

import static com.ea.blogapi.constant.URIConstants.*;
import static com.ea.blogapi.constant.URIConstants.CONTENT_SERVICE_PORT;

@RestController
@RequestMapping("/api/tags")
public class TagProxy {


    @Autowired
    CommonProxyImpl commonProxy;


    @PostMapping
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity onlyUserAPIs(@RequestBody(required = false) String body, HttpMethod method, HttpServletRequest request) throws URISyntaxException {
        return commonProxy.forward(CONTENT_SERVICE_URL, CONTENT_SERVICE_PORT, new ProxyRequest(body, method, request));

    }

    @PutMapping("/{id}")
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity anyUserAPIs(@RequestBody(required = false) String body, HttpMethod method, HttpServletRequest request) throws URISyntaxException {
        return commonProxy.forward(CONTENT_SERVICE_URL, CONTENT_SERVICE_PORT, new ProxyRequest(body, method, request));

    }


    @RequestMapping("**")
    public ResponseEntity publicAPIs(@RequestBody(required = false) String body, HttpMethod method, HttpServletRequest request) throws URISyntaxException {
        return commonProxy.forward(CONTENT_SERVICE_URL, CONTENT_SERVICE_PORT, new ProxyRequest(body, method, request));

    }

}
