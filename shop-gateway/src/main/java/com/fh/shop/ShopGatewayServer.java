package com.fh.shop;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.cloud.netflix.zuul.filters.Route;
import org.springframework.cloud.netflix.zuul.filters.RouteLocator;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import springfox.documentation.swagger.web.SwaggerResource;
import springfox.documentation.swagger.web.SwaggerResourcesProvider;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@EnableZuulProxy
public class ShopGatewayServer {

    public static void main(String[] args) {
        SpringApplication.run(ShopGatewayServer.class,args);
    }

    @Component
    @Primary
    class SwaggerDocumentationConfig implements SwaggerResourcesProvider{

        private final RouteLocator routeLocator;
        public SwaggerDocumentationConfig (RouteLocator routeLocator){
            this.routeLocator=routeLocator;
        }
        @Override
        public List<SwaggerResource> get() {
            List<SwaggerResource> resources = new ArrayList<>();
            List<Route> routes = routeLocator.getRoutes();
            routes.forEach(route -> {
            //从各个服务器中获取文档
              resources.add(swaggerResource(route.getId(), route.getFullPath().replace("**", "v2/api-docs"), "1.0"));
            });
            return resources;
        }
        private SwaggerResource swaggerResource(String name,String url,String swaggerVersion){
                SwaggerResource swaggerResource = new SwaggerResource();
                swaggerResource.setName(name);
                swaggerResource.setLocation(url);
                swaggerResource.setSwaggerVersion(swaggerVersion);
                return swaggerResource;
        }
    }
}

