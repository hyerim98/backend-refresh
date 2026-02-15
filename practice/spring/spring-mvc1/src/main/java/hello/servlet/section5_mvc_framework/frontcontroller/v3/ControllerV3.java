package hello.servlet.section5_mvc_framework.frontcontroller.v3;

import hello.servlet.section5_mvc_framework.frontcontroller.ModelView;

import java.util.Map;

public interface ControllerV3 {
    ModelView process(Map<String, String> paramMap);
}
