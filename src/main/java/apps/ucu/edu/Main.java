package apps.ucu.edu;

public class Main {
    public static void main(String[] args) {
        System.out.println("First photo:");
        System.out.println("--------------");
        Document photo1 = new SmartDocument("gs://photos10/0bbfc-education-4.jpg");
        photo1 = new TimedDocument(photo1);
        System.out.println(photo1.parse());
        System.out.println("Second photo:");
        System.out.println("--------------");
        Document photo2 = new SmartDocument("gs://photos10/images.png");
        photo2 = new TimedDocument(photo2);
        System.out.println(photo2.parse());
        
    }
}