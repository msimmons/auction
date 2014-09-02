package net.contrapt.auction.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by msimmons on 8/28/14.
 */
@Controller
@RequestMapping(value = "/api")
public abstract class BaseController {

    protected Log log = LogFactory.getLog(getClass());

    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(Exception.class)
    public Map<String,String> handleException(HttpServletRequest request, Exception e) {
        log.error("Handling exception", e);
        Map<String,String> result = new HashMap<String,String>();
        result.put("exception", e.getMessage());
        return result;
    }

}
