package apps.ucu.edu;

import com.google.cloud.vision.v1.AnnotateImageRequest;
import com.google.cloud.vision.v1.AnnotateImageResponse;
import com.google.cloud.vision.v1.BatchAnnotateImagesResponse;
import com.google.cloud.vision.v1.Feature;
import com.google.cloud.vision.v1.Feature.Type;
import com.google.cloud.vision.v1.Image;
import com.google.cloud.vision.v1.ImageAnnotatorClient;
import com.google.cloud.vision.v1.ImageSource;
import com.google.cloud.vision.v1.TextAnnotation;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
public class SmartDocument implements Document {
    private String gcsPath;

    public String getGcsPath() {
        return gcsPath;
    }

    public void setGcsPath(String gcsPath) {
        this.gcsPath = gcsPath;
    }

    @SneakyThrows
    public String parse() {
        List<AnnotateImageRequest> requests = new ArrayList<>();

        ImageSource imgSource = ImageSource.newBuilder().
        setGcsImageUri(gcsPath).build();
        Image img = Image.newBuilder().
        setSource(imgSource).build();
        Feature feat = Feature.newBuilder().
        setType(Type.DOCUMENT_TEXT_DETECTION).build();
        AnnotateImageRequest request =
                AnnotateImageRequest.newBuilder().
                addFeatures(feat).setImage(img).build();
        requests.add(request);

        try (ImageAnnotatorClient CLIENT = ImageAnnotatorClient.create()) {
            BatchAnnotateImagesResponse response = CLIENT.
            batchAnnotateImages(requests);
            List<AnnotateImageResponse> responses = response.
            getResponsesList();
            CLIENT.close();

            for (AnnotateImageResponse res : responses) {
                TextAnnotation annotation = res.getFullTextAnnotation();
                return annotation.getText();
            }
        }
        return "";
    }
}