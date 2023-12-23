package apps.ucu.edu;

public class Main {
    public static void main(String[] args) {
        System.out.println("First photo:");
        System.out.println("--------------");
        Document photo = 
        new SmartDocument("gs://photos10/0bbfc-education-4.jpg");
        photo = new TimedDocument(photo);
        System.out.println(photo.parse());
        System.out.println("Second photo:");
        System.out.println("--------------");
        Document photoSE = new SmartDocument("gs://photos10/images.png");
        photoSE = new TimedDocument(photoSE);
        System.out.println(photoSE.parse());
        
    }
}