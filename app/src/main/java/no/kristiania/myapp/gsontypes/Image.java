
package no.kristiania.myapp.gsontypes;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Image {

    private Long id;
    private String servingUrl;
    private String uploadedByUserDisplayName;
    private Long uploadedDate;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();


    public Image() {
    }

    public Image(Long id, String servingUrl, String caption, Long uploadedByUserId, String uploadedByUserDisplayName, Long uploadedDate, List<Long> likes, Long width, Long height) {
        super();
        this.id = id;
        this.servingUrl = servingUrl;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getServingUrl() {
        return servingUrl;
    }

    public void setServingUrl(String servingUrl) {
        this.servingUrl = servingUrl;
    }


    public String getUploadedByUserDisplayName() {
        return uploadedByUserDisplayName;
    }

    public void setUploadedByUserDisplayName(String uploadedByUserDisplayName) {
        this.uploadedByUserDisplayName = uploadedByUserDisplayName;
    }

    public Long getUploadedDate() {
        return uploadedDate;
    }

    public void setUploadedDate(Long uploadedDate) {
        this.uploadedDate = uploadedDate;
    }







    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
