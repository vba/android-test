package com.waracle.androidtest.tasks.tools;

import com.waracle.androidtest.dto.Response;

public interface Client {
    Response get(String resource);
}
