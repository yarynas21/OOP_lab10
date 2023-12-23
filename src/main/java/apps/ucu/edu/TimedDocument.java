package apps.ucu.edu;

public class TimedDocument implements Document {
    Document document;
    public TimedDocument(Document document) {
        this.document = document;
    }

    @Override
    public String parse() {
        long start = System.currentTimeMillis();
        String res = document.parse();
        long elapsedTime = System.currentTimeMillis() - start;
        System.out.println("Parse Time: " + elapsedTime);
        return res;
    }
}