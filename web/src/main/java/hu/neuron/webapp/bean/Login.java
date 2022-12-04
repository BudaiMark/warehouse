package hu.neuron.webapp.bean;

import hu.neuron.webapp.SessionManager;
import lombok.Getter;
import lombok.Setter;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;


@ManagedBean
@SessionScoped
@Getter
@Setter
public class Login {
    private String userName;
    private String password;


    public String validateUsernamePassword() {
        if ("admin".equals(userName) && "password".equals(password)) {
                HttpSession session = SessionManager.getSession();
                session.setAttribute("userName", userName);
                session.setAttribute("password", password);
                return "dashboard";
            } else {
                FacesContext.getCurrentInstance().addMessage(
                        null,
                        new FacesMessage(FacesMessage.SEVERITY_WARN,
                                "Incorrect Username and Password",
                                "Please enter correct username and Password"));
                return "login";
            }

        }
}