    package com.wistronits.tms.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;  
      
    @Controller  
    @RequestMapping("auth")  
    public class SecurityController {  
      
      
        /** 
         * 指向登录页面 
         */  
        @RequestMapping(value = "/login", method = RequestMethod.GET)  
        public String getLoginPage(  
                @RequestParam(value = "error", required = false) boolean error,  
                ModelMap model) {  
            if (error == true) {  
                // Assign an error message  
                model.put("error", "You have entered an invalid username or password!");  
            } else {  
                model.put("error", "");  
            }  
            return "/login/loginpage";  
      
        }  
      
        /** 
         * 指定无访问额权限页面 
         *  
         * @return 
         */  
        @RequestMapping(value = "/denied", method = RequestMethod.GET)  
        public  String getDeniedPage() {  
            return "/login/deniedpage";  
        
      
        }  
    }  