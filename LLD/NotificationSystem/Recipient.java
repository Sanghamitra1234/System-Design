public class Recipient {
    private String recipientId;
    private String email;
    private String phone;

    public Recipient(String recipientId, String email, String phone) {
        this.recipientId = recipientId;
        this.email = email;
        this.phone = phone;
    }

    public String getRecipientId() {
        return recipientId;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
