package io.ashimjk.sse.domain;

import java.util.List;

public class Service {

    String serviceName;
    List<String> eventType;

}

class Module {

    String moduleName;
    List<Service> services;

}

class Response {

    Module module; // data may be available
    Service service; // data may be available
    boolean isModule;

    // check data
}

class ListOfResponse {

    List<Response> responses;

}
