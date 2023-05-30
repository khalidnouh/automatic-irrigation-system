package digtalfactory.irrigation.system.filter;

import digtalfactory.irrigation.system.contract.AuditEventLogContract;
import digtalfactory.irrigation.system.dto.AuditEventLogDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Date;

/**
 * logging requests and responses into db
 */
@Component
public class RequestResponseStoringFilter extends OncePerRequestFilter {
    @Autowired
    private AuditEventLogContract auditEventLogContract;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        ContentCachingRequestWrapper requestWrapper = new ContentCachingRequestWrapper(request);
        //caches the response body by reading it from response output stream.
        ContentCachingResponseWrapper responseWrapper = new ContentCachingResponseWrapper(response);

        //to continue the execution
        filterChain.doFilter(requestWrapper, responseWrapper);

        //get request and response body , convert it to string to be saved in db
        byte[] requestArray = requestWrapper.getContentAsByteArray();
        String requestBody = new String(requestArray, requestWrapper.getCharacterEncoding());
        byte[] responseArray = responseWrapper.getContentAsByteArray();
        String responseBody = new String(responseArray, responseWrapper.getCharacterEncoding());

        //call helper function to log in db
        logReqAndRes(request, response, requestBody, responseBody);

        // the stream becomes empty, To write response back to the output stream  should be used.
        responseWrapper.copyBodyToResponse();
    }

    void logReqAndRes(HttpServletRequest request, HttpServletResponse response, String requestBody, String responseBody) {
        //save request and response into db
        AuditEventLogDTO requestLog = new AuditEventLogDTO();
        //requestLog.setApiType(request.);
        requestLog.setHttpMethod(HttpMethod.valueOf(request.getMethod()));
        requestLog.setPath(request.getRequestURI());
        requestLog.setQueryParams(request.getQueryString());
        if (!requestBody.trim().isEmpty())
            requestLog.setRequest(requestBody);
        if (!responseBody.trim().isEmpty())
            requestLog.setResponse(responseBody);
        requestLog.setHttpStatus(HttpStatus.valueOf(response.getStatus()));
        requestLog.setTime(new Date());
        auditEventLogContract.save(requestLog);
    }

}