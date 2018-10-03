package ru.strict.components;

import ru.strict.validates.ValidateBaseValue;

public class Url {

    private String protocol;
    private String host;
    private String address;
    private String url;

    public Url(String address) {
        this.address = address;
        createUrl();
    }

    public Url(String host, String address) {
        this.host = host;
        this.address = address;
        createUrl();
    }

    public Url(String protocol, String host, String address) {
        this.protocol = protocol;
        this.host = host;
        this.address = address;
        createUrl();
    }

    private void createUrl(){
        if(!ValidateBaseValue.isEmptyOrNull(protocol)
                && !ValidateBaseValue.isEmptyOrNull(host)
                && !ValidateBaseValue.isEmptyOrNull(address)) {
            url = String.format("%s://%s/%s", protocol, host, address);
        } else if(ValidateBaseValue.isEmptyOrNull(protocol)
                && !ValidateBaseValue.isEmptyOrNull(host)
                && !ValidateBaseValue.isEmptyOrNull(address)) {
            url = String.format("%s/%s", host, address);
        } else if(ValidateBaseValue.isEmptyOrNull(protocol)
                && ValidateBaseValue.isEmptyOrNull(host)
                && !ValidateBaseValue.isEmptyOrNull(address)) {
            url = String.format("/%s", address);
        }
    }

    public String getProtocol() {
        return protocol;
    }

    public String getHost() {
        return host;
    }

    public String getAddress() {
        return address;
    }

    public String getUrl() {
        return url;
    }
}
