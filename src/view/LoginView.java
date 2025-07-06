package view;

import java.awt.event.ActionListener;

public interface LoginView {
    void addLoginListener(ActionListener listener);
    void addLogoutListener(ActionListener listener);
    String getUsername();
    String getPassword();
    void clearFields();
    void setLoginEnabled(boolean enabled);
    void setLogoutEnabled(boolean enabled);
}
