package ru.strict.components;

import ru.strict.validates.ValidateBaseValue;

public class Url {

    private String protocol;
    private String domain;
    private String address;
    private String url;

    public Url(String address) {
        this.address = address;
        createUrl();
    }

    public Url(String domain, String address) {
        this.domain = domain;
        this.address = address;
        createUrl();
    }

    public Url(String protocol, String domain, String address) {
        this.protocol = protocol;
        this.domain = domain;
        this.address = address;
        createUrl();
    }

    private void createUrl(){
        if(ValidateBaseValue.isNotEmptyOrNull(protocol)
                && ValidateBaseValue.isNotEmptyOrNull(domain)
                && ValidateBaseValue.isNotEmptyOrNull(address)) {
            url = String.format("%s://%s/%s", protocol, domain, address);
        } else if(!ValidateBaseValue.isNotEmptyOrNull(protocol)
                && ValidateBaseValue.isNotEmptyOrNull(domain)
                && ValidateBaseValue.isNotEmptyOrNull(address)) {
            url = String.format("%s/%s", domain, address);
        } else if(!ValidateBaseValue.isNotEmptyOrNull(protocol)
                && !ValidateBaseValue.isNotEmptyOrNull(domain)
                && ValidateBaseValue.isNotEmptyOrNull(address)) {
            url = String.format("/%s", address);
        }
    }

    public String getProtocol() {
        return protocol;
    }

    public String getDomain() {
        return domain;
    }

    public String getAddress() {
        return address;
    }

    public String getUrl() {
        return url;
    }
}
