package com.task5.services;

public class ServiceFactory {
    private static ServiceFactory instance = new ServiceFactory();

    public static ServiceFactory getInstance() {
        return instance;
    }

    private ServiceFactory() {
    }
    
    public UserService getUserService() {
        return UserServiceImpl.getInstance();
    }
}
