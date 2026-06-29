import java.util.UUID;

public class Tag {
    private String tagId;
    private String name;

    public Tag() {
    }

    public Tag(String tagId, String name) {
        this.tagId = generateTagId();
        this.name = name;
    }

    private String generateTagId() {
        return "tag-" + UUID.randomUUID().toString();
    }

    public String getTagId() {
        return tagId;
    }

    public void setTagId(String tagId) {
        this.tagId = tagId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Tag{" +
                "tagId='" + tagId + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
