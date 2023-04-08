package com.kosuri.stores.model.request;

import jakarta.validation.constraints.NotNull;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;

import java.net.URI;
import java.util.Date;

public class GenerateSaleReportRequest extends GenerateReportRequest {
    public GenerateSaleReportRequest(HttpMethod method, URI url) {
        super(method, url);
    }
}
