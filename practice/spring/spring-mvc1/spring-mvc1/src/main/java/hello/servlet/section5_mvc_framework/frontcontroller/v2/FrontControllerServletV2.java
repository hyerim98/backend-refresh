package hello.servlet.section5_mvc_framework.frontcontroller.v2;

import hello.servlet.section5_mvc_framework.frontcontroller.MyView;
import hello.servlet.section5_mvc_framework.frontcontroller.v1.ControllerV1;
import hello.servlet.section5_mvc_framework.frontcontroller.v1.MemberFormControllerV1;
import hello.servlet.section5_mvc_framework.frontcontroller.v1.MemberListControllerV1;
import hello.servlet.section5_mvc_framework.frontcontroller.v1.MemberSaveControllerV1;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "frontControllerServletV2", urlPatterns = "/front-controller/v2/*")
public class FrontControllerServletV2 extends HttpServlet {
    private final Map<String, ControllerV2> controllerMap = new HashMap<>();

    public FrontControllerServletV2() {
        controllerMap.put("/front-controller/v2/members/new-form", new MemberFormControllerV2());
        controllerMap.put("/front-controller/v2/members/save", new MemberSaveControllerV2());
        controllerMap.put("/front-controller/v2/members", new MemberListControllerV2());
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("FrontControllerServletV2.service");

        String requestURI = req.getRequestURI();
        ControllerV2 controllerV2 = controllerMap.get(requestURI);
        if(controllerV2 == null) {
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        MyView myView = controllerV2.process(req, resp);
        myView.render(req, resp);
    }
}
