package com.ea.blogapi.payload;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpMethod;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotBlank;

@Data
public class ProxyRequest {
    private String body;

    @NotBlank
    private HttpMethod method;

    @NotBlank
    private HttpServletRequest request;

    public ProxyRequest(String body, HttpMethod method, HttpServletRequest request) {
        this.body = body;
        this.method = method;
        this.request = request;
    }
}
