package io.ashimjk;

public class StoredDto {

    private final String storedName;
    private final String storedAddress;
    private final String storedContact;

    public StoredDto(String storedName, String storedAddress, String storedContact) {
        this.storedName = storedName;
        this.storedAddress = storedAddress;
        this.storedContact = storedContact;
    }

    public String getStoredName() {
        return storedName;
    }

    public String getStoredAddress() {
        return storedAddress;
    }

    public String getStoredContact() {
        return storedContact;
    }

}
