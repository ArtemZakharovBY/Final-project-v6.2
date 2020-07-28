package by.epam.corporate_education.controller.util.impl;

import by.epam.corporate_education.controller.util.api.HttpRequestResponseKeeper;
import lombok.Getter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HttpRequestResponseKeeperImpl implements HttpRequestResponseKeeper {

    @Getter
    private HttpServletRequest request;
    @Getter
    private HttpServletResponse response;

    @Override
    public void setAll(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;
    }
}
