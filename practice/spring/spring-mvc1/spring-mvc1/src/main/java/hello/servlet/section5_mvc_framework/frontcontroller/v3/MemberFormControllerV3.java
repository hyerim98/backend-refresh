package hello.servlet.section5_mvc_framework.frontcontroller.v3;

import hello.servlet.section5_mvc_framework.frontcontroller.ModelView;

import java.util.Map;

public class MemberFormControllerV3 implements ControllerV3{

    @Override
    public ModelView process(Map<String, String> paramMap) {
        return new ModelView("new-form");
    }
}
