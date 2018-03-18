/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

// aop jar is added because in web xml namespace its required
// webmvc required request mapping and request method
// to support ControllerClassNameHandlerMapping we need spring-web-servlet.jar
// spring 4 added for ControllerClassNameHandler

//spring web jar is requireqired for this 
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/*to send response in json we need these jars:[jackson-core-2.1.3, jackson-annotations-2.1.2, jackson-core-asl-1.9.13, jackson-mapper-asl-1.9.13], and the object class should have public getters and setters and 
    You might be wondering how the response is sent as JSON string, and the Content-Type header in 
    response confirms that. Glad you asked. This is due to the fact that we have included Jackson library 
    in our project.
*/

import org.springframework.http.ResponseEntity;
import java.util.*;
import org.springframework.http.HttpStatus;
@RestController
//@Controller
@RequestMapping(value="dictionary")
public class DictionaryController {
    DictionaryController(){
        System.out.println("DC created");
    }
    
//    @ResponseBody
    @RequestMapping(value="word", method = RequestMethod.GET)
    public Object getWords(HttpServletRequest request){
        User u = new User();
        u.age = 30;
        u.name = "Subho";
        return u;
    }
    
  @RequestMapping(value = "/user/", method = RequestMethod.GET)
    public ResponseEntity<List<User>> listAllUsers() {
        User u = new User();
        u.age = 30;
        u.name = "Subho";
        List users = new ArrayList();
        users.add(u);
        if(users.isEmpty()){ 
            return new ResponseEntity<List<User>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<List<User>>(users, HttpStatus.OK);
    }
} 


class User{
    String name;
    int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}

/*
NOTE:http://websystique.com/springmvc/spring-mvc-4-restful-web-services-crud-example-resttemplate/
@RestController : First of all, we are using Spring 4′s new @RestController annotation. This annotation eliminates the need of annotating each method with @ResponseBody. Under the hood, @RestController is itself annotated with @ResponseBody, and can be considered as combination of @Controller and @ResponseBody.

@RequestBody : If a method parameter is annotated with @RequestBody, Spring will bind the incoming HTTP request body(for the URL mentioned in @RequestMapping for that method) to that parameter. While doing that, Spring will [behind the scenes] use HTTP Message converters to convert the HTTP request body into domain object [deserialize request body to domain object], based on ACCEPT or Content-Type header present in request.

@ResponseBody : If a method is annotated with @ResponseBody, Spring will bind the return value to outgoing HTTP response body. While doing that, Spring will [behind the scenes] use HTTP Message converters to convert the return value to HTTP response body [serialize the object to response body], based on Content-Type present in request HTTP header. As already mentioned, in Spring 4, you may stop using this annotation.

ResponseEntity is a real deal. It represents the entire HTTP response. Good thing about it is that you can control anything that goes into it. You can specify status code, headers, and body. It comes with several constructors to carry the information you want to sent in HTTP Response.

@PathVariable This annotation indicates that a method parameter should be bound to a URI template variable [the one in '{}'].

Basically, @RestController , @RequestBody, ResponseEntity & @PathVariable are all you need to know to implement a REST API in Spring 4. Additionally, spring provides several support classes to help you implement something customized.

MediaType : With @RequestMapping annotation, you can additionally, specify the MediaType to be produced or consumed (using produces or consumes attributes) by that particular controller method, to further narrow down the mapping.
*/