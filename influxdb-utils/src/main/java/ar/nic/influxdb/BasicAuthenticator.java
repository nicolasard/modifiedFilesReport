package ar.nic.influxdb;

import java.net.Authenticator;
import java.net.PasswordAuthentication;

final class BasicAuthenticator extends Authenticator {

    final String user;

    final String password;

    BasicAuthenticator(String user, String password) {
        this.user = user;
        this.password = password;
    }

    protected PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication(user, password.toCharArray());
    }
}