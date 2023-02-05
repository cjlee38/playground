package com.example.httpplayground.connection

import javax.servlet.Filter
import javax.servlet.FilterChain
import javax.servlet.ServletRequest
import javax.servlet.ServletResponse
import javax.servlet.http.HttpServletResponse

class KeepAliveFilter : Filter {

    override fun doFilter(request: ServletRequest, response: ServletResponse, chain: FilterChain) {
        val httpResponse: HttpServletResponse = response as HttpServletResponse;
        httpResponse.setHeader("Connection", "close");
        chain.doFilter(request, response);
    }
}