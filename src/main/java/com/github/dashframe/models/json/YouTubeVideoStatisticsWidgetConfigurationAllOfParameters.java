package com.github.dashframe.models.json;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import java.net.URI;
import java.time.OffsetDateTime;
import java.util.*;
import java.util.Objects;
import javax.annotation.Generated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * YouTubeVideoStatisticsWidgetConfigurationAllOfParameters
 */

@JsonTypeName("YouTubeVideoStatisticsWidgetConfiguration_allOf_parameters")
@Generated(
    value = "org.openapitools.codegen.languages.SpringCodegen",
    date = "2022-11-08T16:03:51.937911500+01:00[Europe/Paris]"
)
public class YouTubeVideoStatisticsWidgetConfigurationAllOfParameters extends WidgetConfigurationParameters {

    @JsonProperty("video")
    private String video;

    public YouTubeVideoStatisticsWidgetConfigurationAllOfParameters video(String video) {
        this.video = video;
        return this;
    }

    /**
     * Get video
     * @return video
     */
    @NotNull
    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        YouTubeVideoStatisticsWidgetConfigurationAllOfParameters youTubeVideoStatisticsWidgetConfigurationAllOfParameters = (YouTubeVideoStatisticsWidgetConfigurationAllOfParameters) o;
        return Objects.equals(this.video, youTubeVideoStatisticsWidgetConfigurationAllOfParameters.video);
    }

    @Override
    public int hashCode() {
        return Objects.hash(video);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class YouTubeVideoStatisticsWidgetConfigurationAllOfParameters {\n");
        sb.append("    video: ").append(toIndentedString(video)).append("\n");
        sb.append("}");
        return sb.toString();
    }

    /**
     * Convert the given object to string with each line indented by 4 spaces
     * (except the first line).
     */
    private String toIndentedString(Object o) {
        if (o == null) {
            return "null";
        }
        return o.toString().replace("\n", "\n    ");
    }

    @Override
    public List<WidgetParameterDescription> describeParameters() {
        return List.of(describeParameter("video", WidgetParameterDescription.TypeEnum.STRING));
    }
}
